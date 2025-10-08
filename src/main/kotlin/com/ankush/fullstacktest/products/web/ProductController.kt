package com.ankush.fullstacktest.products.web

import com.ankush.fullstacktest.products.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun listProduct(model: Model): String {
        model.addAttribute("products", productService.findAll())
        return "products/list"
    }

    @PostMapping
    fun createProduct(request: CreateProductRequest, model: Model): String {
        productService.createProduct(request)
        return listProduct(model)
    }
}