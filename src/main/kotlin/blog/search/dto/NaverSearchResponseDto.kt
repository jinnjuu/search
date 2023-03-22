package blog.search.dto

data class NaverSearchResponseDto(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>?
){

    data class Item(
        var title: String,
        var link: String,
        var description: String,
        var bloggername: String,
        var bloggerlink: String,
        var postdate: String
    ){
    }

}