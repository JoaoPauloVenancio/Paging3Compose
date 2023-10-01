package com.example.pagingwithcompose.data.mappers

import com.example.pagingwithcompose.data.local.BeerEntity
import com.example.pagingwithcompose.data.remote.BeerDto
import com.example.pagingwithcompose.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}