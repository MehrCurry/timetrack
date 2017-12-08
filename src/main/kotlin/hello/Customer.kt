package hello

import javax.persistence.Entity

@Entity
class Customer : AbstractEntity {

    var firstName: String? = null

    var lastName: String? = null

    protected constructor() {}

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }

    override fun toString(): String {
        return "Customer[id=$id, firstName='$firstName', lastName='$lastName']"
    }

}