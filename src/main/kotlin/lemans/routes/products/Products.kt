package lemans.routes.products

import lemans.models.Product

data class SearchProducts(val entities: Iterable<Product>)
data class LoadProducts(val content: String)
