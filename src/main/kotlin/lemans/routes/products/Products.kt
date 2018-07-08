package lemans.routes.products

import lemans.models.Product

data class SearchProducts(val entities: List<Product>)
data class LoadProducts(val content: String)
