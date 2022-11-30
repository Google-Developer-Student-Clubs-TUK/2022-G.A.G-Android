package com.example.toy_proejct.data.product.list

@kotlinx.serialization.Serializable
data class ProductListDto(
    val imageUrl: String,
    val minimumPrice: Int,
    val title: String,
    val url: String
)