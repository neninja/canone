package app.canone.infrastructure.repositories

import app.canone.domain.entities.Permission
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PermissionRepository : JpaRepository<Permission, Long> {
    fun findByCode(code: String?): Optional<Permission?>?
}
