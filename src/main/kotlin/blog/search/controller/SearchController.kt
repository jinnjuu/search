package blog.search.controller

import blog.search.service.KakaoSearchService
import blog.search.service.QueryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Search")
@RestController
@RequestMapping("/api/v1/search")
class SearchController(
    val kakaoService: KakaoSearchService,
    val queryService: QueryService
) {
    @Operation(summary = "블로그 검색", description = "키워드를 통해 블로그를 검색합니다.")
    @GetMapping("/blog")
    fun searchBlog(
        @Parameter(description = "검색 질의어", required = true) query: String,
        @Parameter(description = "결과 정렬 방식 (accuracy 또는 recency)", required = false, example = "accuracy") sort: String?,
        @Parameter(description = "결과 페이지 번호", required = false, example = "1") page: Int?,
        @Parameter(description = "한 페이지에 보여질 결과 수", required = false, example = "10") size: Int?
    ): ResponseEntity<Any> {
        val sort: String = sort ?: "accuracy"
        val page: Int = page ?: 1
        val size: Int = size ?: 10

        if (!query.isNullOrBlank()) queryService.searchQuery(query)
        return ResponseEntity.ok(kakaoService.search(query, sort, page, size))
    }

}