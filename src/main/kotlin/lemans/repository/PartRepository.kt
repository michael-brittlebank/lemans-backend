package lemans.repository

import lemans.models.Part
import org.springframework.data.repository.CrudRepository

interface PartRepository: CrudRepository<Part, Long>