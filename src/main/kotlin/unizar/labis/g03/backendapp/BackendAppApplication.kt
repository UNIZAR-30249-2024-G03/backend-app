package unizar.labis.g03.backendapp

import org.springframework.amqp.core.Queue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class BackendAppApplication{
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
}

fun main(args: Array<String>) {
	runApplication<BackendAppApplication>(*args)
}
