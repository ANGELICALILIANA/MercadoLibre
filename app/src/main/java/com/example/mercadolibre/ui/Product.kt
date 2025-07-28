package com.mercadolibre.ui

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val stock: Int,
    val seller: String
)

val sampleProducts = listOf(
    Product("1", "Smartphone X", 299.99, "Teléfono de última generación", "Electrónicos", 10, "TecnoShop"),
    Product("2", "Laptop Pro", 899.99, "Laptop potente para trabajo", "Computación", 5, "CompuMundo"),
    Product("3", "Auriculares Bluetooth", 49.99, "Sonido de alta calidad", "Audio", 20, "AudioStore"),
    Product("4", "Smart TV 55\"", 599.99, "TV con resolución 4K", "Televisores", 8, "ElectroHome"),
    Product("5", "Cámara DSLR", 399.99, "Cámara profesional 24MP", "Fotografía", 3, "PhotoGear")
)