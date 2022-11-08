package pl.gispartner.app.service

import org.springframework.stereotype.Service
import pl.gispartner.app.exceptions.UserAuthorityMissingException
import pl.gispartner.app.exceptions.UserNotFoundException
import pl.gispartner.app.model.UserDto
import pl.gispartner.app.model.UserEntity
import pl.gispartner.app.model.UserMapper
import pl.gispartner.app.model.UserType
import pl.gispartner.app.persistence.UserRepository
import java.util.*

@Service
open class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {

    fun getUser(userId: Long): UserDto {
        val userEntity: UserEntity = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User is not found for id = $userId") }
        return userMapper.mapToDto(userEntity)
    }

    fun saveUser(userDto: UserDto): Long {
        val userEntity: UserEntity = userRepository.save(userMapper.mapToEntity(userDto))
        return userEntity.id
    }

    fun deleteUser(userId: Long, id: Long): String {
        if (!isUserValid(userId, id)) {
            throw UserAuthorityMissingException("This operation cannot be performed - lack of authority")
        }
        userRepository.deleteById(userId)
        return "Changes have been successfully saved "
    }

    fun updateUserName(userId: Long, newUserName: String, id: Long): String {
        if (!isUserValid(userId, id)) {
            throw UserAuthorityMissingException("This operation cannot be performed - lack of authority")
        }
        val userEntity: UserEntity = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User is not found for id = $userId") }
        userEntity.name = newUserName
        userEntity.modifiedDate = Date()
        userRepository.save(userEntity)
        return "Changes have been successfully saved"
    }

    private fun isUserValid(userId: Long, id: Long): Boolean {
        if (!userRepository.existsById(userId)) {
            throw UserNotFoundException("User is not found for id = $userId")
        }
        val userEntity: UserEntity = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("User is not found for id = $id") }
        return userId == id || userEntity.userType == UserType.SUPER_USER
    }
}