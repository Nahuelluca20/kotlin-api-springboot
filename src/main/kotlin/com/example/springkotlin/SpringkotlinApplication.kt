package com.example.springkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.UUID.*

@SpringBootApplication
class SpringkotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringkotlinApplication>(*args)
}

/*Data classes in Kotlin are primarily used to hold data. For each data class,
the compiler automatically generates additional member functions that allow
you to print an instance to readable output, compare instances, copy instances,
and more.*//*mutable, using the var keyword
read-only, using the val keyword*/
data class Message(val id: String?, val text: String)

@RestController
class MessageController(val service: MessageService) {
    @GetMapping("/")
    fun index(): List<Message> = service.findMessages()

    @PostMapping("/")
    fun post(@RequestBody message: Message) {
        service.save(message)
    }
}

@Service
class MessageService(val db: JdbcTemplate) {
    /*
     According to the Kotlin convention, if the last parameter of
     a function is a function, then a lambda expression passed as the
     corresponding argument can be placed outside the parentheses.
     db.query("...") { ... }
    */
    fun findMessages(): List<Message> = db.query("select * from messages") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun save(message: Message) {
        val id = message.id ?: randomUUID().toString()
        db.update(
            "insert into messages values ( ?, ? )", id, message.text
        )
    }
}









