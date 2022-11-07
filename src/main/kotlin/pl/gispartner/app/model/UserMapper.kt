package pl.gispartner.app.model

import org.springframework.stereotype.Component

@Component
class UserMapper : Mapper<UserDto, UserEntity> {
    override fun mapToDto(entity: UserEntity): UserDto = UserDto(
        id = entity.id,
        name = entity.name,
        firstName = entity.firstName,
        lastName = entity.lastName,
        userType = entity.userType
    )

    override fun mapToEntity(dto: UserDto): UserEntity = UserEntity(
        id = dto.id,
        name = dto.name,
        firstName = dto.firstName,
        lastName = dto.lastName,
        userType = dto.userType
    )
}