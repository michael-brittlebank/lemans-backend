package lemans.models

import javax.persistence.*

@Entity
@Table(name = "product")
class Product() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    var productId: Int = 0
    @Column(name = "product_name")
    lateinit var productName: String
    @Column(name = "category_name")
    lateinit var categoryName: String

    constructor(productId: Int, productName: String, categoryName: String): this() {
        this.productId = productId
        this.productName = productName
        this.categoryName = categoryName
    }
}