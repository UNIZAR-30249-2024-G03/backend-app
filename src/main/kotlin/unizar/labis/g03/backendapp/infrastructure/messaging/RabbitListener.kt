package unizar.labis.g03.backendapp.infrastructure.messaging

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.entities.Reserva
import unizar.labis.g03.backendapp.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva
import unizar.labis.g03.backendapp.model.valueObjects.Rol
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio


@Component
class RabbitListener {
    val logger: Logger = LoggerFactory.getLogger(RabbitListener::class.java)

    @RabbitListener(queues = ["buscarEspacios"])
    fun buscarEspaciosAdapter (filtro: FiltroEspacios): List<Espacio>{
        logger.info("buscarEspacios: $filtro")
        return emptyList()
    }

    @RabbitListener(queues = ["consultarReservas"])
    fun consultarReservasAdapter (idPersona: String): List<Reserva>{
        logger.info("consultarReservas: $idPersona")
        return emptyList()
    }

    @RabbitListener(queues = ["reservarEspacio"])
    fun reservarEspacioAdapter (requestReserva: RequestReservaEspacio): Reserva{
        logger.info("reservarEspacio: $requestReserva")
        return Reserva(
            "idReserva",
            Persona("Paco", "Paquito", "paco@gmail.com", HashSet<Rol>(),Departamento.Informatica_e_Ingenieria_de_sistemas),
            Espacio("idEspacio", 10f, TipoEspacio.Aula, TipoEspacio.Aula, 5, 1, true, 100),
            InfoReserva()
        )
    }

    @RabbitListener(queues = ["eliminarReserva"])
    fun eliminarReservaAdapter (requestEliminarReserva: RequestEliminarReserva): Boolean{
        logger.info("eliminarReserva: $requestEliminarReserva")
        return true
    }

    @RabbitListener(queues = ["cambiarCaracteristicasPersonal"])
    fun cambiarCaracteristicasPersonalAdapter (requestCambiarPersonal: RequestCambiarCaracteristicasPersonal): Persona{
        logger.info("cambiarCaracteristicasPersonal: $requestCambiarPersonal")
        return Persona("Paco", "Paquito", "paco@gmail.com", HashSet<Rol>(),Departamento.Informatica_e_Ingenieria_de_sistemas)
    }

    @RabbitListener(queues = ["cambiarCaracteristicasEspacio"])
    fun cambiarCaracteristicasEspacioAdapter (requestCambiarEspacio: RequestCambiarCaracteristicasEspacio): Espacio{
        logger.info("cambiarCaracteristicasEspacio: $requestCambiarEspacio")
        return Espacio("idEspacio", 10f, TipoEspacio.Aula, TipoEspacio.Aula, 5, 1, true, 100)
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
        val messageConverter = object : Jackson2JsonMessageConverter(){
            override fun fromMessage(message: Message): Any {
                message.messageProperties.contentType = "application/json"
                return super.fromMessage(message)
            }
        }
        return messageConverter
    }
}