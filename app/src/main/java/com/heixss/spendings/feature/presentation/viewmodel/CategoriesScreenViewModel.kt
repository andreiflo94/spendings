package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.heixss.spendings.feature.presentation.ui.screen.CategoriesScreenState
import com.heixss.spendings.feature.domain.usecases.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    var month: Int = 0
    var year: Int = 0

    val spendings: Flow<CategoriesScreenState> = flow {
        val result = categoriesUseCase(month, year)
        emit(result)
    }.flowOn(Dispatchers.IO)
}