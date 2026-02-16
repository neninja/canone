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
@Table(name = "roles", uniqueConstraints = [UniqueConstraint(name = "uk_roles_code", columnNames = arrayOf("code"))])
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    /**
     * Ex.: ADMIN, OPERATOR
     */
    @Column(name = "code", nullable = false, length = 120)
    private var code: String? = null

    @Column(name = "description", length = 255)
    private var description: String? = null

    @Column(name = "created_at", nullable = false)
    private var createdAt: OffsetDateTime? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "roles_permissions",
        joinColumns = [JoinColumn(name = "role_id", foreignKey = ForeignKey(name = "fk_roles_permissions_role"))],
        inverseJoinColumns = [JoinColumn(
            name = "permission_id",
            foreignKey = ForeignKey(name = "fk_roles_permissions_permission")
        )],
        uniqueConstraints = [UniqueConstraint(
            name = "uk_roles_permissions",
            columnNames = ["role_id", "permission_id"]
        )]
    )
    @Builder.Default
    private val permissions: MutableSet<Permission?> = HashSet<Permission?>()

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Builder.Default
    private val users: MutableSet<User> = HashSet()

    @PrePersist
    fun prePersist() {
        if (createdAt == null) createdAt = OffsetDateTime.now()
    }
}