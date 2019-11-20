package com.example.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Persistence

@SpringBootApplication
class TriplanBackendApplication

fun main(args: Array<String>) {
	runApplication<TriplanBackendApplication>(*args)
}