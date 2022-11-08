package pl.gispartner.app


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories
open class ResourceManagerApp

fun main(args: Array<String>) {
    runApplication<ResourceManagerApp>(*args)
}







