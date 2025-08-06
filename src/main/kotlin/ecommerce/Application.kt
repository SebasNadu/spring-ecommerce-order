package ecommerce

import ecommerce.config.security.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
