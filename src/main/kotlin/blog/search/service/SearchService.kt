package blog.search.service

import blog.search.dto.SearchRequestDto
import blog.search.dto.SearchResponseDto

interface SearchService {

    fun search(searchDto: SearchRequestDto): SearchResponseDto

}