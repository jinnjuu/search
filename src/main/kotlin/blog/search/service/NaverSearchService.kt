package blog.search.service

import blog.search.dto.ExceptionResponse
import blog.search.dto.NaverSearchResponseDto
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.io.*
import java.lang.Exception
import java.net.URLEncoder

@Service
class NaverSearchService: SearchService {

    @Value("\${naver.api.client.id}")
    lateinit var clientId: String

    @Value("\${naver.api.client.secret}")
    lateinit var clientSecret: String

    @Value("\${naver.api.url.search.blog}")
    lateinit var searchBlogUrl: String

    override fun search(
        query: String,
        sort: String,
        start: Int,
        display: Int
    ): Any {
        val log: Logger = LoggerFactory.getLogger(javaClass)

        log.info(">>>>> Naver API")

        val query =
            try {
                URLEncoder.encode(query, "UTF-8")
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException("검색어 인코딩 실패", e)
            }
        val sort = if (sort == "accuracy") "sim" else "date"
        val apiURL = "${searchBlogUrl}?query=$query&display=$display&start=$start&sort=$sort"

        return try {
            val restTemplate = RestTemplate()
            val headers = HttpHeaders()
            headers.set("X-Naver-Client-Id", clientId)
            headers.set("X-Naver-Client-Secret", clientSecret)
            headers.contentType = MediaType.parseMediaType("application/json")
            val entity: HttpEntity<String> = HttpEntity<String>(headers)
            restTemplate.exchange(apiURL, HttpMethod.GET, entity, NaverSearchResponseDto::class.java).body!!
        } catch (e: Exception) {
            println()
            ExceptionResponse(
                (e as HttpClientErrorException).statusCode.value(),
                JSONObject(e.message?.substringAfter(": \"")!!.substringBeforeLast("\"").replace("<EOL>?","").replace("<EOL>","")).getString("errorMessage")
            )
        }
    }

}