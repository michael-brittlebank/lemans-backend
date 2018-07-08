package lemans.routes.products

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsController {

    @GetMapping("/products")
    fun getProducts(
            @RequestParam(value = "minimumPrice", required = false, defaultValue = "0.1") minimumPrice: String,
            @RequestParam(value = "maximumPrice", required = false, defaultValue = "0.2") maximumPrice: String) =
            Products("Hello, $minimumPrice", "you want $maximumPrice")

}