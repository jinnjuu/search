package blog.search.model

import jakarta.persistence.*

@Entity
class Query (
    val query: String
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var count: Long = 1

    fun updateCount(): Long {
        this.count += 1
        return this.count
    }

}