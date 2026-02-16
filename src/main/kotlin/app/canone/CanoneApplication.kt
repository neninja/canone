package app.canone

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CanoneApplication

fun main(args: Array<String>) {
	runApplication<CanoneApplication>(*args)
}
