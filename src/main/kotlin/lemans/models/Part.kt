package lemans.models

data class Part(
    val punctuatedPartNumber: String,
    val partDescr: String,
    val productId: Long,
    val originalRetailPrice: Double,
    val brandName: String,
    val image: String)