package unizar.labis.g03.backendapp.infrastructure.messaging

import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.util.MimeTypeUtils
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.entities.Reserva
import unizar.labis.g03.backendapp.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva
import unizar.labis.g03.backendapp.model.valueObjects.Rol
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio
import java.util.Hashtable


@Component
class RabbitListener (val rabbitTemplate: RabbitTemplate) {
    val logger: Logger = LoggerFactory.getLogger(RabbitListener::class.java)
    val messageConverter = object : Jackson2JsonMessageConverter(){
        override fun fromMessage(message: Message): Any {
            message.messageProperties.contentType = "application/json"
            return super.fromMessage(message)
        }
        /*
        override fun createMessage(objectToConvert: Any, messageProperties: MessageProperties): Message {
            messageProperties.contentType = "application/json"
            return super.createMessage(objectToConvert, messageProperties)
        }
        */
    }

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
    fun eliminarReservaAdapter (message: Message): Int{
        val prueba = messageConverter.fromMessage(message) as LinkedHashMap<*, *>
        val idPersona: String = prueba["idPersona"].toString()
        val idReserva: String = prueba["idReserva"].toString()

        println(idPersona)
        println(idReserva)


        // val prueba = Jackson2JsonMessageConverter().fromMessage(message) as String
        //val requestEliminarReserva: RequestEliminarReserva = RequestEliminarReserva(message.body)
        //logger.info("eliminarReserva: $requestEliminarReserva")
        return 1
    }

    @RabbitListener(queues = ["cambiarCaracteristicasPersonal"])
    fun cambiarCaracteristicasPersonalAdapter (message: Message): Int{
        //val replyTo = message.messageProperties.replyTo
        //println(message.messageProperties.)
        //rabbitTemplate.convertAndSend("amq.direct", replyTo, "a")

        val prueba = messageConverter.fromMessage(message) as LinkedHashMap<*, *>
        val idPersona: String = prueba["idPersona"].toString()
        val rol: String = prueba["rol"].toString()
        val rolSecundario: String = prueba["rolSecundario"].toString()
        val departamento : String = prueba["departamento"].toString()
        println(idPersona + rol + rolSecundario + departamento)
        logger.info("cambiarCaracteristicasPersonal")
        //val response : MutableMap<*,*> = new java.util.HashMap()
        val response = HashMap<Any,Any>()
        response.put("nombre", "Paco")

        return 1
        //return response
        //return Persona("Paco", "Paquito", "paco@gmail.com", HashSet<Rol>(),Departamento.Informatica_e_Ingenieria_de_sistemas)
    }

    @RabbitListener(queues = ["cambiarCaracteristicasEspacio"])
    fun cambiarCaracteristicasEspacioAdapter (requestCambiarEspacio: RequestCambiarCaracteristicasEspacio): Espacio{
        logger.info("cambiarCaracteristicasEspacio: $requestCambiarEspacio")
        return Espacio("idEspacio", 10f, TipoEspacio.Aula, TipoEspacio.Aula, 5, 1, true, 100)
    }

    @RabbitListener(queues = ["duplicar"])
    public fun receive(value : Int): Int{
        return value * 2
    }

    /*

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }

    @Bean
    fun jsonRabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val template = RabbitTemplate(connectionFactory)
        return template
    }
     */

    /*
    @Bean
    fun jsonConverter(): MessageConverter {
        val messageConverter = object : Jackson2JsonMessageConverter(){

            override fun fromMessage(message: Message): Any {
                message.messageProperties.contentType = "application/json"
                return super.fromMessage(message)
            }
            /*
            override fun createMessage(objectToConvert: Any, messageProperties: MessageProperties): Message {
                messageProperties.contentType = "application/json"
                return super.createMessage(objectToConvert, messageProperties)
            }
             */
        }
        val utf16 = "application/json; charset=utf-16"

        messageConverter.setSupportedContentType(MimeTypeUtils.parseMimeType(utf16))
        return messageConverter
    }
     */


}