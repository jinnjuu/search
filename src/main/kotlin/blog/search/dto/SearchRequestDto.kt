package blog.search.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class SearchRequestDto (

    @field:NotEmpty(message="검색어를 입력하세요")
    @field:NotBlank
    val query: String,
    val sort: String?,
    val page: Int?,
    val size: Int?

) {
}
