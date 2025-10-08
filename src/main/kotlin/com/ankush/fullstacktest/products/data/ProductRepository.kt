package com.ankush.fullstacktest.products.data

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductRepository(
    private val jdbcClient: JdbcClient
) {

    fun save(product: Product) {
        jdbcClient.sql("INSERT INTO products(id, name, slug) VALUES (:id, :name, :slug)")
            .param("id", product.id)
            .param("name", product.name)
            .param("slug", product.slug)
            .update();
    }

    fun findAll(): List<Product>? {
        return jdbcClient.sql("SELECT id, name, slug FROM products")
            .query(Product::class.java)
            .list()
    }

    fun findById(id: UUID): Optional<Product> {
        return jdbcClient.sql("SELECT id, name, slug FROM products WHERE id = :id")
            .param("id", id)
            .query(Product::class.java)
            .optional()
    }

    fun saveAll(productsToSave: List<Product>) {
        productsToSave.forEach { save(it) }
    }
}