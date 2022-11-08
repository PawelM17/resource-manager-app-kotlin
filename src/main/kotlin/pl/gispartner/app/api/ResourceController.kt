package pl.gispartner.app.api

import org.springframework.web.bind.annotation.*
import pl.gispartner.app.model.ResourceDto
import pl.gispartner.app.service.ResourceService

@RestController
@RequestMapping("/api/resource")
class ResourceController(
    private val resourceService: ResourceService
) {

    @GetMapping("/{resourceId}")
    fun getResource(
        @PathVariable("resourceId") resourceId: Long
    ): ResourceDto = resourceService.getResource(resourceId)

    @GetMapping
    fun getAllUserResources(
        @RequestHeader("userId") userId: Long
    ): List<ResourceDto> = resourceService.getAllUserResources(userId)

    @PostMapping
    fun saveResource(
        @RequestBody resourceDto: ResourceDto
    ): Long = resourceService.saveResource(resourceDto)

    @DeleteMapping("/{resourceId}")
    fun deleteResource(
        @PathVariable("resourceId") resourceId: Long,
        @RequestHeader userId: Long
    ): String = resourceService.deleteResource(resourceId, userId)

    @PutMapping("/{resourceId}/name")
    fun updateUserName(
        @PathVariable("resourceId") resourceId: Long,
        @RequestBody newResourceName: String,
        @RequestHeader userId: Long
    ): String = resourceService.updateResourceName(resourceId, newResourceName, userId)

}
