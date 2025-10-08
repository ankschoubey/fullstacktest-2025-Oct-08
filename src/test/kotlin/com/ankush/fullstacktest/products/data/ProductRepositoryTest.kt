package com.ankush.fullstacktest.products.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class ProductRepositoryTest(
    @Autowired private val productRepository: ProductRepository
) {

    @Test
    fun saveAndRetrieveProductTest(){
        // arrange
        var product = Product(UUID.randomUUID(), "iPhone", "/iphone+"+UUID.randomUUID())

        assertThat<Product>(productRepository.findById(product.id)).isEmpty
        // act
        productRepository.save(product)

        // assert
        assertThat<Product>(productRepository.findById(product.id)).isPresent
    }
}