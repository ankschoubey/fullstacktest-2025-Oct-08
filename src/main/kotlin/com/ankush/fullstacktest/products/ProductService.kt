package com.ankush.fullstacktest.products

import com.ankush.fullstacktest.products.data.Product
import com.ankush.fullstacktest.products.data.ProductRepository
import com.ankush.fullstacktest.products.web.CreateProductRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun findAll() = productRepository.findAll()
    fun search(searchTerm: String) = productRepository.findByNameContainingIgnoreCase(searchTerm)
    fun findById(id: UUID) = productRepository.findById(id)

    fun saveAll(productsToSave: List<Product>) {
        productRepository.saveAll(productsToSave)
    }

    fun createProduct(request: CreateProductRequest) {
        val product = Product(UUID.randomUUID(), request.name, request.slug)
        productRepository.save(product)
    }

    fun updateProduct(id: UUID, name: String, slug: String) {
        val product = productRepository.findById(id).orElseThrow()
        product.name = name
        product.slug = slug
        productRepository.update(product)
    }

    fun deleteProduct(id: UUID) {
        productRepository.deleteById(id)
    }
}