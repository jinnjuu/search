package blog.search.service

import blog.search.dto.ExceptionResponse
import blog.search.dto.KakaoSearchResponseDto
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

@Service
class KakaoSearchService(
    val naverSearchService: NaverSearchService
): SearchService {

    @Value("\${kakao.api.key}")
    lateinit var apiKey: String

    @Value("\${kakao.api.url.search.blog}")
    lateinit var searchBlogUrl: String

    override fun search(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Any {
        val log: Logger = LoggerFactory.getLogger(javaClass)

        log.info(">>>>> Kakao API")

        val apiURL = "${searchBlogUrl}?query=$query&page=$page&size=$size&sort=$sort"

        return try {
            val restTemplate = RestTemplate()
            val headers = HttpHeaders()
            headers.set("Authorization", "KakaoAK $apiKey")
            headers.contentType = MediaType.parseMediaType("application/json")
            val entity: HttpEntity<String> = HttpEntity<String>(headers)
            restTemplate.exchange(apiURL, HttpMethod.GET, entity, KakaoSearchResponseDto::class.java).body!!
        } catch (e: Exception) {
            when (e) {
                is HttpClientErrorException.Unauthorized, is HttpServerErrorException -> {
                    log.error(e.printStackTrace().toString())
                    naverSearchService.search(query, sort, page, size)
                } else -> {
                    ExceptionResponse(
                        (e as HttpClientErrorException).statusCode.value(),
                        JSONObject(e.message?.substringAfter(": \"")!!.substringBeforeLast("\"")).getString("message")
                    )
                }
            }
        }
    }

}