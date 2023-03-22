package blog.search.dto

import io.swagger.v3.oas.annotations.media.Schema

data class QueryResponseDto (

    @Schema(description = "검색 키워드")
    val query: String,

    @Schema(description = "검색된 횟수")
    val count: Long,

) {
}