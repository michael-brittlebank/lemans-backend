package lemans.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Part(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val partId: Long = 0,
        @get: NotBlank
        val punctuatedPartNumber: String = "",
        @get: NotBlank
        val partDescr: String = "",
        @get: NotBlank
        val productId: Long = 0,
        @get: NotBlank
        val originalRetailPrice: Double = 0.0,
        @get: NotBlank
        val brandName: String = "",
        @get: NotBlank
        val image: String = "")