package com.ankush.fullstacktest.products.data

import java.time.LocalDateTime
import java.util.UUID

class Product(
    var id: UUID,
    var name: String,
    var slug: String,
    var updatedAt: LocalDateTime? = null
)