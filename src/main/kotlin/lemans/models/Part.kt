package lemans.models

import javax.persistence.*

@Entity
@Table(name = "part")
class Part() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "part_id")
    var partId: Int = 0
    @Column(name = "punctuated_part_number")
    lateinit var punctuatedPartNumber: String
    @Column(name = "part_description")
    lateinit var partDescr: String
    @Column(name = "product_id")
    var productId: Long = 0
    @Column(name = "original_retail_price")
    var originalRetailPrice: Double = 0.0
    @Column(name = "brand_name")
    lateinit var brandName: String
    @Column(name = "image")
    lateinit var image: String

    constructor(punctuatedPartNumber: String, partDescr: String, productId: Long,
                originalRetailPrice: Double, brandName: String, image: String) : this() {
        this.punctuatedPartNumber = punctuatedPartNumber
        this.partDescr = partDescr
        this.productId = productId
        this.originalRetailPrice = originalRetailPrice
        this.brandName = brandName
        this.image = image
    }
}