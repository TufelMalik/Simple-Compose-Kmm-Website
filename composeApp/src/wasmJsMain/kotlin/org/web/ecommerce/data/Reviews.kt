package org.web.ecommerce.data

import kotlinx.serialization.Serializable

@Serializable
data class Reviews(
    val comment: String? = "",
    val date: String? = "",
    val rating: Int? = 0,
    val reviewerEmail: String? = "",
    val reviewerName: String? = ""
)