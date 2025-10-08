package com.ankush.fullstacktest.products

import com.ankush.fullstacktest.products.data.Product
import com.ankush.fullstacktest.products.data.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun findAll() = productRepository.findAll()
    fun saveAll(productsToSave: List<Product>) {
        productRepository.saveAll(productsToSave)
    }
}