package pl.gispartner.app.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "resources")
data class ResourceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var name: String = "",

    @Enumerated(EnumType.STRING)
    var resourceType: ResourceType = ResourceType.FILE,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    override var createdDate: Date = Date(),
    override var modifiedDate: Date = Date()
) : AuditableBase(createdDate, modifiedDate)


