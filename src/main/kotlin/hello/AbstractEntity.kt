package hello

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue
    var id: Long? = null
        protected set
}
