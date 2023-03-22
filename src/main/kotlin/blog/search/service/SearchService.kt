package blog.search.service

interface SearchService {

    fun search(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Any

}