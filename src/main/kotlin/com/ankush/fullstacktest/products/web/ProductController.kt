package com.ankush.fullstacktest.products.web

import com.ankush.fullstacktest.products.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/search")
    fun search(): String {
        return "products/search"
    }

    @GetMapping
    fun listProduct(model: Model, @RequestParam(required = false) search: String?): String {
        val products = if (search.isNullOrBlank())
            productService.findAll()
        else
            productService.search(search)
        model.addAttribute("products", products)
        return "products/list"
    }

    @PostMapping
    fun createProduct(request: CreateProductRequest, model: Model): String {
        productService.createProduct(request)
        return listProduct(model, null)
    }
}