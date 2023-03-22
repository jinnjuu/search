package blog.search.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ExceptionResponse(

    @Schema(description = "에러 코드")
    val code: Int,

    @Schema(description = "에러 메세지")
    val msg: String,

) {
}