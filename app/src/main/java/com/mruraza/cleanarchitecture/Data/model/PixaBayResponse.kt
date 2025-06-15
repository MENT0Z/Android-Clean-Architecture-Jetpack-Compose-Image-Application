package com.mruraza.cleanarchitecture.Data.model

data class PixaBayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)
