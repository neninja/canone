package app.canone.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_users_email", columnNames = ["email"])
    ]
)
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false, length = 150)
    var nome: String,

    @Column(name = "email", nullable = false, length = 254)
    var email: String
) {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_roles",
        joinColumns = [
            JoinColumn(
                name = "user_id",
                referencedColumnName = "id",
                foreignKey = ForeignKey(name = "fk_users_roles_user")
            )
        ],
        inverseJoinColumns = [
            JoinColumn(
                name = "role_id",
                referencedColumnName = "id",
                foreignKey = ForeignKey(name = "fk_users_roles_role")
            )
        ],
        uniqueConstraints = [
            UniqueConstraint(name = "uk_users_roles", columnNames = ["user_id", "role_id"])
        ]
    )
    var roles: MutableSet<Role> = linkedSetOf()
}