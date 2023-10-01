package com.example.pagingwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pagingwithcompose.presentation.BeerScreen
import com.example.pagingwithcompose.presentation.BeerViewModel
import com.example.pagingwithcompose.ui.theme.PagingWithComposeTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = koinViewModel<BeerViewModel>()
                    val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                    BeerScreen(
                        beers = beers
                    )
                }
            }
        }
    }
}