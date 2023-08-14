package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.SaveCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.usecase.character.GetOurCharacterListUseCase
import com.bonobono.domain.usecase.character.GetUserCharacterListUseCase
import com.bonobono.domain.usecase.character.PatchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getUserCharacterListUseCase: GetUserCharacterListUseCase,
    private val getOurCharacterListUseCase: GetOurCharacterListUseCase,
    private val patchCharacterUseCase: PatchCharacterUseCase
) : ViewModel() {
    private val _userCharacterList = MutableStateFlow<List<UserCharacter>>(listOf())
    val userCharacterList: StateFlow<List<UserCharacter>> = _userCharacterList

    private val _ourCharacterList = MutableStateFlow<List<OurCharacter>>(listOf())
    val ourCharacterList: StateFlow<List<OurCharacter>> = _ourCharacterList

    private val _saveSuccess = MutableStateFlow<Boolean>(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    fun getUserCharacterList() = viewModelScope.launch {
        _userCharacterList.emit(getUserCharacterListUseCase.invoke(1))
    }

    fun getOurCharacterList() = viewModelScope.launch {
        _ourCharacterList.emit(getOurCharacterListUseCase.invoke(1))
    }

    fun patchCharacter(character: SaveCharacter) = viewModelScope.launch {
        _saveSuccess.emit(patchCharacterUseCase.invoke(character = character))
    }
}