package app.canone.infrastructure.repositories

import app.canone.domain.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByCode(code: String?): Optional<Role?>?
}
