package unizar.labis.g03.backendapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendAppApplication

fun main(args: Array<String>) {
	runApplication<BackendAppApplication>(*args)
}
