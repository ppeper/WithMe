package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.usecase.character.GetUserCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    val getUserCharacterListUseCase: GetUserCharacterListUseCase
) : ViewModel() {
    private val _userCharacterList = MutableStateFlow<List<UserCharacter>>(listOf())
    val userCharacterList: StateFlow<List<UserCharacter>> = _userCharacterList

    suspend fun getUserCharacterList() {
        _userCharacterList.emit(getUserCharacterListUseCase.invoke(1))
    }
}