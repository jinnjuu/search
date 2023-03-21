package blog.search.service

import blog.search.dto.QueryResponseDto
import blog.search.model.Query
import blog.search.repository.QueryRepository
import org.springframework.stereotype.Service

@Service
class QueryService(
    private val queryRepository: QueryRepository
) {

    fun searchQuery(query: String): Long =
        (queryRepository.findByQuery(query)?.let { findQuery ->
            findQuery.updateCount().also {
                queryRepository.save(findQuery)
            }
        }) ?: let {
            queryRepository.save(Query(query)).count
        }

    fun findTop10ByCount(): List<QueryResponseDto>? =
        queryRepository.findTop10ByOrderByCountDesc()
}