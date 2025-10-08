package com.ankush.fullstacktest.external

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FammeClientTest(
    @Autowired
    private val fammeClient: FammeClient
) {

    @Test
    fun loadProductsTest() {
        // act
        var response = fammeClient.loadProducts();
        // assert
        assertThat<ExternalProduct>(response)
            .isNotEmpty
            .first()
            .hasNoNullFieldsOrProperties()
    }
}