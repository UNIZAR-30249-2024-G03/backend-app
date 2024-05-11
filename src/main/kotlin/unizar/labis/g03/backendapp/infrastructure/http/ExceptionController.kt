package unizar.labis.g03.backendapp.infrastructure.http

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import unizar.labis.g03.backendapp.application.exceptions.*

@ControllerAdvice
class ExceptionController () : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value =
        [UsuarioNoEncontradoException::class,
        EspacioYaReservadoException::class,
        EspacioNoEncontradoException::class,
        ReservaNoValidaException::class,
        UsuarioSinPermisosException::class])
    fun handleError(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.badRequest().body((ex.message))
    }
}