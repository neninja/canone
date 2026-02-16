package app.canone.domain.entities;

import jakarta.persistence.*
import lombok.*
import java.time.OffsetDateTime


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions", uniqueConstraints = [UniqueConstraint(name = "uk_permissions_code", columnNames = arrayOf("code"))])
class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @Column(name = "code", nullable = false, length = 120)
    private var code: String? = null

    @Column(name = "description", length = 255)
    private var description: String? = null

    @Column(name = "created_at", nullable = false)
    private var createdAt: OffsetDateTime? = null

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @Builder.Default
    private val roles: MutableSet<Role?> = HashSet<Role?>()

    @PrePersist
    fun prePersist() {
        if (createdAt == null) createdAt = OffsetDateTime.now()
    }
}
