package lemans.routes.products

import lemans.Application
import lemans.models.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import com.opencsv.CSVReader
import lemans.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping(path=["/products"])
class ProductsController {

    @Autowired lateinit var productRepo: ProductRepository

    companion object {
        val LOG = Logger.getLogger(Application::class.java.name)
    }

    @GetMapping("/search")
    fun searchProducts(
            @RequestParam(value = "minimumPrice", required = false, defaultValue = "0.0") minimumPrice: String,
            @RequestParam(value = "maximumPrice", required = false, defaultValue = "0.0") maximumPrice: String): SearchProducts {
        val products: List<Product> = listOf(Product(1,"first product", "cat1 $minimumPrice $maximumPrice"))
        LOG.warning(products.toString())
        return SearchProducts(products)
    }

    @GetMapping("/load")
    fun loadProducts(): LoadProducts {
        var fileReader: BufferedReader? = null
        var csvReader: CSVReader? = null
        try {
            // load products
            fileReader = BufferedReader(FileReader("src/main/kotlin/lemans/data/products.csv"))
            csvReader = CSVReader(fileReader)
            var record: Array<String>?
            csvReader.readNext() // skip Header
            record = csvReader.readNext()
            while (record != null) {
                println(record[0] + " | " + record[1] + " | " + record[2])
                val tempProduct = Product(record[0].toInt(), record[1], record[2])
                productRepo.save(tempProduct)
                record = csvReader.readNext()
            }
            csvReader.close()
        } catch (e: Exception) {
            println("Reading CSV Error!")
            e.printStackTrace()
        } finally {
            try {
                fileReader!!.close()
                csvReader!!.close()
            } catch (e: IOException) {
                println("Closing fileReader/csvParser Error!")
                e.printStackTrace()
            }
            return LoadProducts("Successfully loaded products")
        }
    }
}