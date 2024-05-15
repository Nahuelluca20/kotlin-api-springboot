package com.example.springkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
class MessageController {
    @GetMapping("/")
    fun index() = listOf(
        Message("1", "Hello!"),
        Message("2", "Bonjour!"),
        Message("3", "Privet!"),
    )
}
