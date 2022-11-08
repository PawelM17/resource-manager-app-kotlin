package pl.gispartner.app.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.gispartner.app.model.ResourceEntity

@Repository
interface ResourceRepository : JpaRepository<ResourceEntity, Long>
