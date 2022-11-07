package pl.gispartner.app.service

import org.springframework.stereotype.Service
import pl.gispartner.app.model.*
import pl.gispartner.app.persistence.ResourceRepository
import pl.gispartner.app.persistence.UserRepository
import java.util.*

@Service
open class ResourceService(
    private val resourceRepository: ResourceRepository,
    private val resourceMapper: ResourceMapper,
    private val userRepository: UserRepository
) {

    fun getResource(resourceId: Long): ResourceDto {
        val resourceEntity: ResourceEntity = resourceRepository.findById(resourceId)
            .orElseThrow { RuntimeException("Resource is not found for id = $resourceId") }
        return resourceMapper.mapToDto(resourceEntity)
    }

    fun getAllUserResources(userId: Long): List<ResourceDto> {
        val userEntity: UserEntity = userRepository.findById(userId)
            .orElseThrow { RuntimeException("User is not found for id = $userId") }
        return userEntity.resources.stream()
            .map(resourceMapper::mapToDto)
            .toList()
    }

    fun saveResource(resourceDto: ResourceDto): Long {
        val resourceEntity: ResourceEntity = resourceRepository.save(resourceMapper.mapToEntity(resourceDto))
        return resourceEntity.id
    }

    fun deleteResource(resourceId: Long, userId: Long): String {
        if (!isUserValid(resourceId, userId)) {
            throw RuntimeException("This operation cannot be performed - lack of authority")
        }
        resourceRepository.deleteById(resourceId)
        return "Changes have been successfully saved "
    }

    fun updateResourceName(resourceId: Long, newResourceName: String, userId: Long): String {
        if (!isUserValid(resourceId, userId)) {
            throw RuntimeException("This operation cannot be performed - lack of authority")
        }
        val resourceEntity: ResourceEntity = resourceRepository.findById(resourceId)
            .orElseThrow { RuntimeException("Resource is not found for id = $resourceId") }
        resourceEntity.name = newResourceName
        resourceEntity.modifiedDate = Date()
        resourceRepository.save(resourceEntity)
        return "Changes have been successfully saved"
    }

    private fun isUserValid(resourceId: Long, userId: Long): Boolean {
        val resourceEntity: ResourceEntity = resourceRepository.findById(resourceId)
            .orElseThrow { RuntimeException("Resource is not found for id = $resourceId") }
        val userEntity: UserEntity = userRepository.findById(userId)
            .orElseThrow { RuntimeException("User is not found for id = $userId") }
        return userId == resourceEntity.user!!.id || userEntity.userType == UserType.SUPER_USER
    }
}
