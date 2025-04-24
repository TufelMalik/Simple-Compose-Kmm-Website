package org.web.ecommerce.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.web.ecommerce.data.Product
import org.web.ecommerce.data.ProductResponse

const val BASE_URL = "https://dummyjson.com/"
const val PRODUCT = "products"
val client = HttpClient(Js) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

object ProductApi {
    suspend fun getProducts(): ProductResponse {
        return client.get("$BASE_URL$PRODUCT").body()
    }

    suspend fun getProductDetails(productId: Int): Product {
        return client.get("$BASE_URL$PRODUCT/$productId").body()
    }
}
