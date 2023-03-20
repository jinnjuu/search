package blog.search.dto

data class QueryResponseDto (
    val query: String,
    val count: Long,
) {
}