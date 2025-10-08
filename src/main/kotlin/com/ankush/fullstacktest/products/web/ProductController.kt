package com.ankush.fullstacktest.products.web

import com.ankush.fullstacktest.products.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun listProduct(model: Model, @RequestParam(required = false) search: String?): String {
        val products = if (search.isNullOrBlank())
            productService.findAll()
        else
            productService.search(search)
        model.addAttribute("products", products)
        return "products/list"
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: UUID, model: Model): String {
        val product = productService.findById(id).orElseThrow()
        model.addAttribute("product", product)
        return "products/edit"
    }

    @GetMapping("/search")
    fun search(): String {
        return "products/search"
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: UUID, request: CreateProductRequest, model: Model): String {
        productService.updateProduct(id, request.name, request.slug)
        return "redirect:/"
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteProduct(@PathVariable id: UUID): String {
        productService.deleteProduct(id)
        return ""
    }

    @PostMapping
    fun createProduct(request: CreateProductRequest, model: Model): String {
        productService.createProduct(request)
        return listProduct(model, null)
    }
}