package org.web.ecommerce.screens

import org.web.ecommerce.api.ProductApi
import org.web.ecommerce.data.Product
import org.web.ecommerce.data.ProductResponse

class ProductRepository {
    suspend fun getProducts(): ProductResponse {
        return ProductApi.getProducts()
    }

    suspend fun getProductDetails(productId: Int): Product {
        return ProductApi.getProductDetails(productId)
    }
}