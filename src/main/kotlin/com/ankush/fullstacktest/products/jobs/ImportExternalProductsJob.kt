package com.ankush.fullstacktest.products.jobs

import com.ankush.fullstacktest.external.FammeClient
import com.ankush.fullstacktest.products.ProductService
import com.ankush.fullstacktest.products.data.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class ImportExternalProductsJob(
    private val fammeClient: FammeClient,
    private val productService: ProductService,
    private val logger: Logger = LoggerFactory.getLogger(FammeClient::class.java)
) {

    @Scheduled(initialDelay = 0)
    fun importExternalProducts() {
        val externalProducts = fammeClient.loadProducts()
        logger.info("Fetched ${externalProducts.size} products")
        val productsToSave = externalProducts
            .map { Product(UUID.randomUUID(), it.title, it.handle) }

        productService.saveAll(productsToSave)
        logger.info("Saved ${productsToSave.size} products")
    }
}