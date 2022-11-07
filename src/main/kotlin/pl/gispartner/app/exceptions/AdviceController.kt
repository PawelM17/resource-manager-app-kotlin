package pl.gispartner.app.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AdviceController {

    @ExceptionHandler
    fun handleUserNotFoundException(userNotFoundException: UserNotFoundException): ResponseEntity<ErrorMessage> {
        val errorMessage =
            ErrorMessage(HttpStatus.NOT_FOUND.value(), userNotFoundException.message!!, System.currentTimeMillis())
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleResourceNotFoundException(resourceNotFoundException: ResourceNotFoundException): ResponseEntity<ErrorMessage> {
        val errorMessage =
            ErrorMessage(HttpStatus.NOT_FOUND.value(), resourceNotFoundException.message!!, System.currentTimeMillis())
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleUserAuthorityMissingException(userAuthorityMissingException: UserAuthorityMissingException): ResponseEntity<ErrorMessage> {
        val errorMessage =
            ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                userAuthorityMissingException.message!!,
                System.currentTimeMillis()
            )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

}