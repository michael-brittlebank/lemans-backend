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
import lemans.models.Part
import lemans.repository.PartRepository
import lemans.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping(path=["/products"])
class ProductsController {

    @Autowired lateinit var productRepo: ProductRepository
    @Autowired lateinit var partRepo: PartRepository

    companion object {
        val LOG = Logger.getLogger(Application::class.java.name)
    }

    @GetMapping("/search")
    fun searchProducts(
            @RequestParam(value = "minimumPrice", required = false, defaultValue = "0.0") minimumPrice: String,
            @RequestParam(value = "maximumPrice", required = false, defaultValue = "0.0") maximumPrice: String): SearchProducts {
        LOG.info("params: $minimumPrice, $maximumPrice")
        // todo, need to return subarray of parts matching product id
        val products: Iterable<Product> = productRepo.findAll()
        // todo, filter results by minimum and maximum prices
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
                val tempProduct = Product(record[0].toInt(), record[1], record[2])
                // defaults to update instead of create if record with primary key already exists
                try {
                    productRepo.save(tempProduct)
                } catch (e: Exception) {
                    // print error but keep importing lines
                    println("--- save line error ----")
                    e.printStackTrace()
                }
                record = csvReader.readNext()
            }
            // load parts
            // todo, relational tables needed for many to many parts to product id relationship
            fileReader = BufferedReader(FileReader("src/main/kotlin/lemans/data/parts.csv"))
            csvReader = CSVReader(fileReader)
            csvReader.readNext() // skip Header
            record = csvReader.readNext()
            while (record != null) {
                val tempPart = Part(record[0], record[1], record[2].toInt(), record[3].toDouble(), record[4], record[5])
                // defaults to update instead of create if record with primary key already exists
                try {
                    partRepo.save(tempPart)
                } catch (e: Exception) {
                    // print error but keep importing lines
                    println("--- save line error ----")
                    e.printStackTrace()
                }
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