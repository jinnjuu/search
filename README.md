# 프로그램 실행
1. [jar 다운로드](https://github.com/jinnjuu/search/raw/main/search.jar)
2. jar 실행
```
$ java -jar search.jar
```
<b>* 소스 clone 하여 실행 시 application.properties 에서 API 키 입력이 필요합니다.</b>
```
kakao.api.key=${KAKAO_API_KEY}

naver.api.client.id=${NAVER_API_CLIENT_ID}
naver.api.client.secret=${NAVER_API_CLIENT_SECRET}
```

# API 명세서
`localhost:8080/api-docs`

## [GET] /api/v1/search/blog : 블로그 검색
키워드를 통해 블로그를 검색합니다.

### Parameters
- **query (string, required)** : 검색 질의어
- **sort (string)** : 결과 정렬 방식 (accuracy 또는 recency)
- **page (integer)** : 결과 페이지 번호
- **size (integer)** : 한 페이지에 보여질 결과 수

### Responses
- **Success Response**

    ```json
    {
        "meta": {
            "total_count": 1531004,
            "pageable_count": 800,
            "is_end": false
        },
        "documents": [
            {
                "title": "숨쉬기 편한 가벼운 마스크, 아에르 피크v 장단점 가격",
                "contents": "끈 길이가 5mm 길어졌습니다. 어드밴스드v라이트핏과 가로세로 길이는 같습니다. 제품실측사이즈는 다음과 같습니다. S 가로113 세로 120 끈길이 155 M 가로<b>123</b> 세로 139 끈길이 165 L 가로130 세로 147 끈길이 170 아에르피크v 라이트 가격 및 장단점 저는 아에르피크를 사는데 v로 사야 돼서 아에르피크v라이트를 구매...",
                "url": "http://saysom2thing.tistory.com/207",
                "blogname": "나는 고양이로소이다",
                "thumbnail": "https://search1.kakaocdn.net/argon/130x130_85_c/Js2xjzwG34d",
                "datetime": "2023-03-22T22:01:06.000+09:00"
            }
        ]
    }
    ```

    ```json
    {
        "lastBuildDate": "Wed, 22 Mar 2023 22:23:22 +0900",
        "total": 181845,
        "start": 4,
        "display": 1,
        "items": [
            {
                "title": "생각없는 과소비는 과소비를 부르지 우린 이걸 FLEX라고... ",
                "link": "https://blog.naver.com/snowperfume0101/223052686731",
                "description": "kr/product/<b>%EC%</b>9D%B8<b>%EC%</b>96%B<b>4%ED%</b>95%9C<b>%EB%</b>B3%B5-<b>%EC%</b>B<b>9%</b>98<b>%EB%A7%</b>88-<b>%EC%</b>82%B<b>4%</b>EA%B5%<b>AC%</b><b>EB%</b><b>B0%</b>B1<b>%EC%</b>83%89-<b>%EB%A</b>1%B1/20/category/31/display/1/ 구매 할 사람 당연히 없겠지만 그냥 공유함... 근데 멍청비용이 생각보다 좋은 결과물... ",
                "bloggername": "말하는 감자 블로그",
                "bloggerlink": "blog.naver.com/snowperfume0101",
                "postdate": "20230322"
            }
        ]
    }
    ```

- **Fail Response**

    ```json
    {
        "code": 400,
        "msg": "page is less than min"
    }
    ```


## [GET] /api/v1/query/top10 : 인기 검색어 목록

사용자들이 많이 검색한 순서대로 검색어 10개를 조회합니다.

### Responses

- **Success Response**

    ```json
    [
        {
            "query": "까뱅",
            "count": 9
        },
        {
            "query": "블로그",
            "count": 8
        },
        {
            "query": "벚꽃",
            "count": 7
        },
        {
            "query": "까까오뺑끄",
            "count": 6
        },
        {
            "query": "아인슈페너",
            "count": 6
        },
        {
            "query": "애플페이",
            "count": 6
        },
        {
            "query": "API",
            "count": 5
        },
        {
            "query": "기온별 옷차림",
            "count": 5
        },
        {
            "query": "맥북 m2",
            "count": 5
        },
        {
            "query": "마스크 착용",
            "count": 1
        }
    ]
    ```




# 개발 환경
- SpringBoot 3.0.4
- Gradle
- Kotlin
- JDK 17

# 사용 라이브러리
- org.springframework.boot:spring-boot-starter-data-jpa : JPA 구현
- com.h2database:h2 : 인메모리 DB (h2) 사용
- org.springdoc:springdoc-openapi-starter-webmvc-ui : API 명세 작성
- org.json:json : JSONObject 사용