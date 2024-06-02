package com.example.springkotlin

import org.springframework.jdbc.core.query
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.PathVariable
import java.util.*
import java.util.UUID.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

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
@Table("MESSAGES")
data class Message(@Id var id: String?, val text: String)

interface MessageRepository : CrudRepository<Message, String>


@RestController
class MessageController(val service: MessageService) {
    @GetMapping("/")
    fun index(): List<Message> = service.findMessages()


    @GetMapping("/{id}")
    fun index(@PathVariable id: String): List<Message> =
        service.findMessageById(id)

    @PostMapping("/")
    fun post(@RequestBody message: Message) {
        service.save(message)
    }
}


@Service
class MessageService(val db: MessageRepository) {
    /*
     According to the Kotlin convention, if the last parameter of
     a function is a function, then a lambda expression passed as the
     corresponding argument can be placed outside the parentheses.
     db.query("...") { ... }
    */
    fun findMessages(): List<Message> = db.findAll().toList()

    fun findMessageById(id: String): List<Message> = db.findById(id).toList()

    fun save(message: Message) {
        /*
        If the expression to the left of ?: is not null,
        the Elvis operator returns it; otherwise, it returns
        the expression to the right.
        */
        db.save(message)
    }

    fun <T : Any> Optional<out T>.toList(): List<T> =
        if (isPresent) listOf(get()) else emptyList()
}









