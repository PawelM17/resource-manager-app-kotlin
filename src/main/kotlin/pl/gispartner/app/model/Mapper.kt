package pl.gispartner.app.model

interface Mapper<Dto, Entity> {
    fun mapToDto(entity: Entity): Dto

    fun mapToEntity(dto: Dto): Entity
}