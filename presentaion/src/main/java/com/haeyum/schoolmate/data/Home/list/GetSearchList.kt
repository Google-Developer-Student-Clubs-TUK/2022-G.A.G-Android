package com.example.toy_proejct.data.product.list

@kotlinx.serialization.Serializable
data class GetSearchList(
    val productListDtoList: List<ProductListDto>,
    val totalNumber: Int
)