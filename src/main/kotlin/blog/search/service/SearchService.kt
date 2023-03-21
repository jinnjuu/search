package blog.search.service

import blog.search.dto.SearchResponseDto

interface SearchService {

    fun search(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchResponseDto

}