package app.canone.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "documents")
class Document(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false, length = 200)
    var nome: String,

    @Column(name = "description", length = 1000)
    var descricao: String? = null,

    /**
     * Caminho/chave do objeto no storage (ex.: MinIO).
     * Ex.: "documents/2026/02/<id-ou-uuid>-contrato.pdf"
     */
    @Column(name = "path", nullable = false, length = 1024)
    var path: String
)
