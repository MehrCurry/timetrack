package hello

@javax.persistence.Entity
data class Project(var name:String?) : AbstractEntity() {
    constructor() : this(null)
}
