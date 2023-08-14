package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.usecase.character.GetOurCharacterListUseCase
import com.bonobono.domain.usecase.character.GetUserCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getUserCharacterListUseCase: GetUserCharacterListUseCase,
    private val getOurCharacterListUseCase: GetOurCharacterListUseCase
) : ViewModel() {
    private val _userCharacterList = MutableStateFlow<List<UserCharacter>>(listOf())
    val userCharacterList: StateFlow<List<UserCharacter>> = _userCharacterList

    private val _ourCharacterList = MutableStateFlow<List<OurCharacter>>(listOf())
    val ourCharacterList: StateFlow<List<OurCharacter>> = _ourCharacterList

    suspend fun getUserCharacterList() {
        _userCharacterList.emit(getUserCharacterListUseCase.invoke(1))
    }

    suspend fun getOurCharacterList() {
        _ourCharacterList.emit(getOurCharacterListUseCase.invoke(1))
    }
}