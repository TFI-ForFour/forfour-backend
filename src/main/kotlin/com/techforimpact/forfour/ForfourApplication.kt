package com.techforimpact.forfour

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class ForfourApplication

fun main(args: Array<String>) {
	runApplication<ForfourApplication>(*args)
}
