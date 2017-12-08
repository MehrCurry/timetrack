package hello

import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {

    fun findByLastNameStartsWithIgnoreCase(lastName: String): List<Customer>
}