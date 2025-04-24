package org.web.ecommerce.data

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int? = 0,
    val title: String? = "",
    val description: String? = "",
    val category: String? = "",
    val discountPercentage: Double? = 0.0,
    val price: Double? = 0.0,
    val thumbnail: String? = "",
    val warrantyInformation: String? = "",
    val shippingInformation: String? = "",
    val availabilityStatus: String? = "",
    val reviews: List<Reviews>? = emptyList(),
    val brand: String? = ""
)