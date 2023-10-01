package com.example.pagingwithcompose.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.pagingwithcompose.data.local.BeerDatabase
import com.example.pagingwithcompose.data.remote.BeerApi
import com.example.pagingwithcompose.data.remote.BeerRemoteMediator
import com.example.pagingwithcompose.presentation.BeerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalPagingApi::class)
val appModule = module {

    single  {
        Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }

    single(named("pagingConfig")) {
        PagingConfig(pageSize = 20)
    }

    single {
        Pager(
            get(named("pagingConfig")),
            remoteMediator = BeerRemoteMediator(
                beerDb = get(),
                beerApi = get()
            ),
            pagingSourceFactory = {
                get<BeerDatabase>().dao.pagingSource()
            }
        )
    }

    viewModel {
        BeerViewModel(pager = get())
    }
}