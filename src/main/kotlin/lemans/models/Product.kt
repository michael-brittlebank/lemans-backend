package lemans.models

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val productId: Long = 0,
        @get: NotBlank
        val productName: String = "",
        @get: NotBlank
        val categoryName: String = ""
)