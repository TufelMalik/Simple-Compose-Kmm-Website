package org.web.ecommerce.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.web.ecommerce.data.Product

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    var products = MutableStateFlow<List<Product>>(emptyList())
        private set

    var product = MutableStateFlow<Product?>(null)
        private set

    var isLoading = MutableStateFlow(false)
        private set

    fun getProducts() {
        viewModelScope.launch {
            try {
                if (products.value.isNotEmpty()) return@launch
                isLoading.value = true
                val response = repository.getProducts()
                products.value = response.products
                isLoading.value = false
                println("ProductList -> ${products.value.size}")
            } catch (e: Exception) {
                isLoading.value = false
                println("ProductList-> error -> ${e.message}")
                e.printStackTrace()
            }
        }
    }


    fun getProductDetails(productId: Int) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                product.value = repository.getProductDetails(productId)
                isLoading.value = false
                println("ProductDetails -> ${product.value?.title ?: "null data"}")
            } catch (e: Exception) {
                println("ProductDetails-> error -> ${e.message}")
                isLoading.value = false
                e.printStackTrace()
            }
        }
    }
}