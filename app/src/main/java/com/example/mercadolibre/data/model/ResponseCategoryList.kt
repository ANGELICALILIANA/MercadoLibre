package com.example.mercadolibre.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCategoryList(
    @SerialName("domain_id")
    var domain_id: String? = null,
    @SerialName("domain_name")
    var domain_name: String? = null,
    @SerialName("category_id")
    var category_id: String? = null,
    @SerialName("category_name")
    var category_name: String? = null,
    @SerialName("attributes")
    var attributes: List<Attributes?>,
)

@Serializable
data class Attributes(
    @SerialName("id")
    var id: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("value_id")
    var value_id: String? = null,
    @SerialName("value_name")
    var value_name: String? = null,
)
