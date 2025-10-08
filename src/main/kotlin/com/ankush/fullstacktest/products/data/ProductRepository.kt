package com.ankush.fullstacktest.products.data

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductRepository(
    private val jdbcClient: JdbcClient
) {

    fun save(product: Product) {
        jdbcClient.sql("INSERT INTO products(id, name, slug, updated_at) VALUES (:id, :name, :slug, :updatedAt)")
            .param("id", product.id)
            .param("name", product.name)
            .param("slug", product.slug)
            .param("updatedAt", product.updatedAt)
            .update();
    }

    fun update(product: Product) {
        jdbcClient.sql("UPDATE products SET name = :name, slug = :slug, updated_at = :updated_at WHERE id = :id")
            .param("id", product.id)
            .param("name", product.name)
            .param("slug", product.slug)
            .param("updated_at", product.updatedAt)
            .update();
    }

    fun findAll(): List<Product>? {
        return jdbcClient.sql("SELECT id, name, slug, updated_at FROM products ORDER BY updated_at DESC NULLS LAST")
            .query(Product::class.java)
            .list()
    }

    fun findById(id: UUID): Optional<Product> {
        return jdbcClient.sql("SELECT id, name, slug, updated_at FROM products WHERE id = :id")
            .param("id", id)
            .query(Product::class.java)
            .optional()
    }

    fun findByNameContainingIgnoreCase(name: String): List<Product> {
        return jdbcClient.sql("SELECT id, name, slug, updated_at FROM products WHERE name ILIKE :name ORDER BY updated_at DESC NULLS LAST")
            .param("name", "%$name%")
            .query(Product::class.java)
            .list()
    }

    fun deleteById(id: UUID) {
        jdbcClient.sql("DELETE FROM products WHERE id = :id")
            .param("id", id)
            .update()
    }

    fun saveAll(productsToSave: List<Product>) {
        productsToSave.forEach { save(it) }
    }
}