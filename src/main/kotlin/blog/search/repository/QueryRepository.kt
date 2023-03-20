package blog.search.repository

import blog.search.dto.QueryResponseDto
import blog.search.model.Query
import org.springframework.data.jpa.repository.JpaRepository

interface QueryRepository: JpaRepository<Query, String> {

    fun findByQuery(query: String): Query?

    fun findTop10ByOrderByCountDesc(): List<QueryResponseDto>?

}