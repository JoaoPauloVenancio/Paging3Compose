package com.example.pagingwithcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pagingwithcompose.data.local.BeerEntity
import com.example.pagingwithcompose.data.mappers.toBeer
import kotlinx.coroutines.flow.map

class BeerViewModel (
    pager: Pager<Int, BeerEntity>
) : ViewModel() {

    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { beerEntity ->
                beerEntity.toBeer()
            }
        }
        .cachedIn(viewModelScope)
}