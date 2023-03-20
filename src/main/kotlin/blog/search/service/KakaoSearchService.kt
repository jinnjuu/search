package blog.search.service

import blog.search.dto.KakaoResponseDto
import blog.search.dto.SearchRequestDto
import blog.search.dto.SearchResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class KakaoSearchService(): SearchService {

    @Value("\${kakao.api.key}")
    lateinit var apiKey: String

    @Value("\${kakao.api.url.search.blog}")
    lateinit var searchBlogUrl: String

    override fun search(searchDto: SearchRequestDto): SearchResponseDto {

        val query = searchDto.query
        val sort: String = searchDto.sort ?: "accuracy"
        val page: Int = searchDto.page ?: 1
        val size: Int = searchDto.size ?: 10
        val apiURL = "${searchBlogUrl}?query=$query&page=$page&size=$size&sort=$sort"

        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.set("Authorization", "KakaoAK $apiKey")
        headers.contentType= MediaType.parseMediaType("application/json")
        val entity: HttpEntity<String> = HttpEntity<String>(headers)

        return restTemplate.exchange(apiURL, HttpMethod.GET, entity, KakaoResponseDto::class.java).body!!
    }

}