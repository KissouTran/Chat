package serversample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import java.awt.EventQueue

@SpringBootApplication
class ServerSampleApplication : SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder {


        return builder!!.sources(ServerSampleApplication::class.java)
    }
}

fun main(args: Array<String>) {

    runApplication<ServerSampleApplication>(*args)
}


