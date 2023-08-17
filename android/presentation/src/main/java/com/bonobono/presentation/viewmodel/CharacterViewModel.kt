package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.SaveCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.usecase.character.GetCharacterUseCase
import com.bonobono.domain.usecase.character.GetOurCharacterListUseCase
import com.bonobono.domain.usecase.character.GetUserCharacterListUseCase
import com.bonobono.domain.usecase.character.PatchCharacterUseCase
import com.bonobono.domain.usecase.character.PatchMainCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getUserCharacterListUseCase: GetUserCharacterListUseCase,
    private val getOurCharacterListUseCase: GetOurCharacterListUseCase,
    private val patchCharacterUseCase: PatchCharacterUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val patchMainCharacterUseCase: PatchMainCharacterUseCase
) : ViewModel() {
    private val _userCharacterList = MutableStateFlow<List<UserCharacter>>(listOf())
    val userCharacterList: StateFlow<List<UserCharacter>> = _userCharacterList

    private val _ourCharacterList = MutableStateFlow<List<OurCharacter>>(listOf())
    val ourCharacterList: StateFlow<List<OurCharacter>> = _ourCharacterList

    private val _character = MutableStateFlow<UserCharacter>(UserCharacter())
    val character: StateFlow<UserCharacter> = _character

    private val _saveSuccess = MutableStateFlow<Boolean>(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    private val _changeMainCharacter = MutableStateFlow<Boolean>(false)
    val changeMainCharacter: StateFlow<Boolean> = _changeMainCharacter

    fun getUserCharacterList() = viewModelScope.launch {
        _userCharacterList.emit(getUserCharacterListUseCase.invoke(1))
    }

    fun getOurCharacterList() = viewModelScope.launch {
        _ourCharacterList.emit(getOurCharacterListUseCase.invoke(1))
    }

    fun patchCharacter(character: SaveCharacter) = viewModelScope.launch {
        _saveSuccess.emit(patchCharacterUseCase.invoke(character = character))
    }

    fun getMainCharacter() = viewModelScope.launch {
        _userCharacterList.collect { userCharacters ->
            val mainCharacter = userCharacters.find { it.is_main }
            mainCharacter?.let {
                _character.value = it
            }
        }
    }

    fun patchMainCharacter(characterId: Int) = viewModelScope.launch {
        _changeMainCharacter.emit(patchMainCharacterUseCase.invoke(characterId))
    }
}