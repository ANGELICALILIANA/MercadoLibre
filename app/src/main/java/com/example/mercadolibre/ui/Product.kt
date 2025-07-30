package com.example.mercadolibre.ui

import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.data.model.ResponseCategoryList

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val stock: Int,
    val seller: String,
    val imageUrl: String? = null
)

fun ResponseCategoryList.toProduct(): Product {
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

fun ResponseCategoryItem.toProduct(): Product {

    val matchingPathItem = this.path_from_root?.firstOrNull { it?.id == this.id }
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

val sampleProducts = listOf(
    Product("1", "Smartphone X", 299.99, "Teléfono de última generación", "Electrónicos", 10, "TecnoShop"),
    Product("2", "Laptop Pro", 899.99, "Laptop potente para trabajo", "Computación", 5, "CompuMundo"),
    Product("3", "Auriculares Bluetooth", 49.99, "Sonido de alta calidad", "Audio", 20, "AudioStore"),
    Product("4", "Smart TV 55\"", 599.99, "TV con resolución 4K", "Televisores", 8, "ElectroHome"),
    Product("5", "Cámara DSLR", 399.99, "Cámara profesional 24MP", "Fotografía", 3, "PhotoGear")
)