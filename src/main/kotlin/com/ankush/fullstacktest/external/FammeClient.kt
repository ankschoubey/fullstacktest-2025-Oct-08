package com.ankush.fullstacktest.external

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class FammeClient(
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun loadProducts(): List<ExternalProduct> {
        return restTemplate.getForEntity("https://famme.no/products.json", Root::class.java)
            .body!!
            .products
    }
}


data class Root(
    val products: List<ExternalProduct>,
)

data class ExternalProduct(
    val id: Long,
    val title: String,
    val handle: String,
    @JsonProperty("body_html")
    val bodyHtml: String,
    @JsonProperty("published_at")
    val publishedAt: String,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
    val vendor: String,
    @JsonProperty("product_type")
    val productType: String,
    val tags: List<String>,
    val variants: List<Variant>,
    val images: List<Image>,
    val options: List<Option>,
)

data class Variant(
    val id: Long,
    val title: String,
    val option1: String,
    val option2: String?,
    val option3: String?,
    val sku: String,
    @JsonProperty("requires_shipping")
    val requiresShipping: Boolean,
    val taxable: Boolean,
    @JsonProperty("featured_image")
    val featuredImage: FeaturedImage?,
    val available: Boolean,
    val price: String,
    val grams: Long,
    @JsonProperty("compare_at_price")
    val compareAtPrice: String?,
    val position: Long,
    @JsonProperty("product_id")
    val productId: Long,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
)

data class FeaturedImage(
    val id: Long,
    @JsonProperty("product_id")
    val productId: Long,
    val position: Long,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
    val alt: String,
    val width: Long,
    val height: Long,
    val src: String,
    @JsonProperty("variant_ids")
    val variantIds: List<Long>,
)

data class Image(
    val id: Long,
    @JsonProperty("created_at")
    val createdAt: String,
    val position: Long,
    @JsonProperty("updated_at")
    val updatedAt: String,
    @JsonProperty("product_id")
    val productId: Long,
    @JsonProperty("variant_ids")
    val variantIds: List<Long>,
    val src: String,
    val width: Long,
    val height: Long,
)

data class Option(
    val name: String,
    val position: Long,
    val values: List<String>,
)
