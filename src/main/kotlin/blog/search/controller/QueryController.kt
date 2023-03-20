package blog.search.controller

import blog.search.dto.QueryResponseDto
import blog.search.service.QueryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/query")
class QueryController(
    val queryService: QueryService
) {

    @Operation(summary = "인기 검색어 목록", description = "사용자들이 많이 검색한 순서대로 검색어 10개를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "200 OK")
    @GetMapping
    fun getTop10(): ResponseEntity<List<QueryResponseDto>> {
        return ResponseEntity.ok(queryService.findTop10ByCount())
    }

}