package unizar.labis.g03.backendapp.infrastructure.messaging

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class RabbitListener {
    @RabbitListener(queues = ["buscarEspacios"])
    fun buscarEspaciosAdapter (filtro: FiltroEspacios){
        println("buscarEspacios")
        println(filtro)

    }

    @RabbitListener(queues = ["consultarReservas"])
    fun consultarReservasAdapter (idPersona: String){
        println("consultarReservas")
        println(idPersona)
    }

    @RabbitListener(queues = ["reservarEspacio"])
    fun reservarEspacioAdapter (requestReserva: RequestReservaEspacio){
        println("reservarEspacio")
        println(requestReserva)
    }

    @RabbitListener(queues = ["eliminarReserva"])
    fun eliminarReservaAdapter (requestEliminarReserva: RequestEliminarReserva){
        println("eliminarReserva")
        println(requestEliminarReserva)
    }

    @RabbitListener(queues = ["cambiarCaracteristicasPersonal"])
    fun cambiarCaracteristicasPersonalAdapter (requestCambiarPersonal: RequestCambiarCaracteristicasPersonal){
        println("cambiarCaracteristicasPersonal")
        println(requestCambiarPersonal)
    }

    @RabbitListener(queues = ["cambiarCaracteristicasEspacio"])
    fun cambiarCaracteristicasEspacioAdapter (requestCambiarEspacio: RequestCambiarCaracteristicasEspacio){
        println("cambiarCaracteristicasEspacio")
        println(requestCambiarEspacio)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }

    @Bean
    fun jsonRabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val template = RabbitTemplate(connectionFactory)
        template.messageConverter = jsonConverter()
        return template
    }

    @Bean
    fun jsonConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }
}