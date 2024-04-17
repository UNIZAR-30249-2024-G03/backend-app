package unizar.labis.g03.backendapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages  = ["unizar.labis.g03.backendapp.repositories"])
class BackendAppApplication{
	@Scheduled(fixedRate = 10000) // Ejecutar cada 5 segundos
	fun ejecutarTarea() {
		println("¡La tarea se está ejecutando!")
	}
}


fun main(args: Array<String>) {
	runApplication<BackendAppApplication>(*args)
}
