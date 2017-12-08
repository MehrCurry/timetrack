package hello

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters


@EntityScan(basePackageClasses = arrayOf(Application::class, Jsr310JpaConverters::class))
@SpringBootApplication
open class Application(val repository: CustomerRepository, val projectRepository: ProjectRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        repository.save(Customer("Jack", "Bauer"))
        repository.save(Customer("Chloe", "O'Brian"))
        repository.save(Customer("Kim", "Bauer"))
        repository.save(Customer("David", "Palmer"))
        repository.save(Customer("Michelle", "Dessler"))

        projectRepository.save(Project("Hermes"));
    }

    companion object {

        private val log = LoggerFactory.getLogger(Application::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java)
        }
    }

}