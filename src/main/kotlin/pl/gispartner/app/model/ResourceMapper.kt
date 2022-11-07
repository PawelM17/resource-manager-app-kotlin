package pl.gispartner.app.model

import org.springframework.stereotype.Component

@Component
class ResourceMapper : Mapper<ResourceDto, ResourceEntity> {
    override fun mapToDto(entity: ResourceEntity): ResourceDto = ResourceDto(
        id = entity.id,
        name = entity.name,
        resourceType = entity.resourceType,
        ownerId = entity.user!!.id
    )

    override fun mapToEntity(dto: ResourceDto): ResourceEntity = ResourceEntity(
        id = dto.id,
        name = dto.name,
        resourceType = dto.resourceType,
        user = UserEntity(dto.ownerId)
    )
}
