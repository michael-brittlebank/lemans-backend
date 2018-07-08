package lemans.models

data class Product(
    val productId: Long,
    val productName: String,
    val categoryName: String,
    val parts: List<Part>)