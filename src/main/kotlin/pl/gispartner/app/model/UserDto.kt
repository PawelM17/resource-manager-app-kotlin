package pl.gispartner.app.model

data class UserDto(
    val id: Long = 0,
    val name: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val userType: UserType = UserType.DEFAULT
)