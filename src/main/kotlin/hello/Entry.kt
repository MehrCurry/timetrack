package hello

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class Entry(var start:LocalDateTime) : AbstractEntity() {
    var end:LocalDateTime? = null

    @ManyToOne
    @NotNull
    var project:Project? = null
}