package pl.gispartner.app.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.Temporal
import java.util.*
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.TemporalType

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AuditableBase(
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    open var createdDate: Date,
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    open var modifiedDate: Date
)



