package blog.search.controller

import blog.search.dto.SearchRequestDto
import blog.search.service.QueryService
import blog.search.service.SearchService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class SearchController(
    val searchService: SearchService,
    val queryService: QueryService
) {

    @Operation(summary = "블로그 검색", description = "키워드를 통해 블로그를 검색합니다.")
    @ApiResponse(responseCode = "200", description = "200 OK")
    @GetMapping("/blog")
    fun searchBlog(
        @RequestBody @Valid searchDto: SearchRequestDto
    ): ResponseEntity<Any> =
        try {
            ResponseEntity.ok(searchService.search(searchDto))
        } finally {
            queryService.searchQuery(searchDto.query)
        }


}