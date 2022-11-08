package pl.gispartner.app.model

import java.util.*
import java.util.Collections.emptyList
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",

    @Enumerated(EnumType.STRING)
    var userType: UserType = UserType.DEFAULT,

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = [CascadeType.ALL])
    var resources: MutableList<ResourceEntity> = emptyList(),
    override var createdDate: Date = Date(),
    override var modifiedDate: Date = Date()
) : AuditableBase(createdDate, modifiedDate)






