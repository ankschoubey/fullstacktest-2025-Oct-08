package com.ankush.fullstacktest.products

import com.ankush.fullstacktest.products.data.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun findAll() = productRepository.findAll()
}