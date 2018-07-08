package lemans.routes.products

import lemans.Application
import lemans.models.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping(path=arrayOf("/products"))
class ProductsController {

    companion object {
        val LOG = Logger.getLogger(Application::class.java.name)
    }

    @GetMapping("/search")
    fun searchProducts(
            @RequestParam(value = "minimumPrice", required = false, defaultValue = "0.0") minimumPrice: String,
            @RequestParam(value = "maximumPrice", required = false, defaultValue = "0.0") maximumPrice: String): SearchProducts {
        val products: List<Product> = listOf(Product(1,"first product", "cat1 $minimumPrice $maximumPrice", listOf()))
        LOG.warning(products.toString())
        return SearchProducts(products)
    }

    @GetMapping("/load")
    fun loadProducts() =
            LoadProducts("Hello World")
}