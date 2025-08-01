package com.example.mercadolibre.data.model.mapper

import com.example.mercadolibre.data.model.Product
import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.data.model.ResponseCategoryList

fun ResponseCategoryList.toProductList(): Product {
    return Product(
        id = this.category_id ?: generateRandomId(),
        title = this.category_name ?: "Unknown Product",
        price = 0.0,
        description = "Category: ${this.category_name}, \nDomain: ${this.domain_name}",
        category = "Category Name: ${this.category_id}",
        stock = 0,
        seller = "Unknown"
    )
}

private fun generateRandomId(): String {
    return "prod_${java.util.UUID.randomUUID().toString().substring(0, 8)}"
}

fun ResponseCategoryItem.toProductItem(): Product {

    val matchingPathItem = this.path_from_root.firstOrNull { it?.id == this.id }
    val finalTitle = when {
        this.name != null -> this.name
        matchingPathItem?.name != null -> matchingPathItem.name
        else -> this.path_from_root
            ?.mapNotNull { it?.name }
            ?.firstOrNull()
            ?: "Unknown Product"
    }

    return Product(
        id = this.id,
        title = finalTitle,
        price = this.settings?.minimum_price ?: 0.0,
        description = this.path_from_root.mapNotNull { it?.name }.joinToString(" > "),
        category = this.settings?.catalog_domain ?: "Unknown",
        stock = this.total_items_in_this_category?.toInt() ?: 0,
        seller = this.settings?.sellerContact ?: "Unknown",
        imageUrl = this.picture
    )
}