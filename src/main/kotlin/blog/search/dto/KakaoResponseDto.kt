package blog.search.dto

data class KakaoResponseDto(
    val meta: Meta,
    val documents: List<Documents>?
) : SearchResponseDto {

    data class Meta (
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean,
    ){
    }

    data class Documents (
        val title: String,
        val contents: String,
        val url: String,
        val blogname: String,
        val thumbnail: String,
        val datetime: String
    ){
    }
}