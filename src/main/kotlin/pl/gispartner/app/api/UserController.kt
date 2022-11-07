package pl.gispartner.app.api

import org.springframework.web.bind.annotation.*
import pl.gispartner.app.model.UserDto
import pl.gispartner.app.service.UserService

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{userId}")
    fun getUser(
        @PathVariable("userId") userId: Long
    ): UserDto = userService.getUser(userId)

    @PostMapping
    fun saveUser(
        @RequestBody userDto: UserDto
    ): Long = userService.saveUser(userDto)

    @DeleteMapping("/{userId}")
    fun deleteUser(
        @PathVariable("userId") userId: Long,
        @RequestHeader id: Long
    ): String = userService.deleteUser(userId, id)

    @PutMapping("/{userId}/name")
    fun updateUserName(
        @PathVariable("userId") userId: Long,
        @RequestBody newUserName: String,
        @RequestHeader id: Long
    ): String = userService.updateUserName(userId, newUserName, id)
}