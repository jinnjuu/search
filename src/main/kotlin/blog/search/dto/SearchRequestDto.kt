package blog.search.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class SearchRequestDto (

    @field:NotEmpty(message="검색어를 입력하세요")
    @field:NotBlank
    val query: String,

    // accuracy(정확도순) 또는 recency(최신순)
    // sim, date
    val sort: String = "accuracy",

    // 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
    // 검색 시작 위치(기본값: 1, 최댓값: 1000)
    val page: Int = 1,

    // 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10
    // 한 번에 표시할 검색 결과 개수(기본값: 10, 최댓값: 100)
    val size: Int = 10

) {
}
