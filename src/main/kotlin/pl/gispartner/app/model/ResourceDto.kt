package pl.gispartner.app.model

data class ResourceDto(
    val id: Long = 0,
    val name: String = "",
    val resourceType: ResourceType = ResourceType.FILE,
    val ownerId: Long = 0
)


