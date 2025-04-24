package org.web.ecommerce.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(val products: List<Product>)