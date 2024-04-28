package unizar.labis.g03.backendapp

import org.springframework.amqp.core.Queue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

//@ComponentScan(basePackages  = ["unizar.labis.g03.backendapp.repositories"])
@EnableScheduling
@SpringBootApplication
class BackendAppApplication{
	/*
	@Bean
	public fun buscarEspaciosQueue (): Queue {
		return Queue("buscarEspacios")
	}

	@Bean
	public fun consultarReservasQueue (): Queue {
		return Queue("consultarReservas")
	}

	@Bean
	public fun reservarEspacioQueue (): Queue {
		return Queue("reservarEspacio")
	}

	@Bean
	public fun eliminarReservaQueue (): Queue {
		return Queue("eliminarReserva")
	}

	@Bean
	public fun cambiarCaracteristicasPersonalQueue (): Queue {
		return Queue("cambiarCaracteristicasPersonal")
	}

	@Bean
	public fun cambiarCaracteristicasEspacioQueue (): Queue {
		return Queue("cambiarCaracteristicasEspacio")
	}

	@Bean
	public fun duplicarQueue (): Queue {
		return Queue("duplicar")
	}

	 */

	@Scheduled(fixedRate = 10000) // Ejecutar cada 5 segundos
	fun ejecutarTarea() {
		// println("¡La tarea se está ejecutando!")
	}
}

fun main(args: Array<String>) {
	runApplication<BackendAppApplication>(*args)
}
