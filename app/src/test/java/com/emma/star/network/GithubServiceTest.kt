package com.emma.star.network

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GithubServiceTest {

    private lateinit var service: GithubService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `request repos and ensure response`() {
        runBlocking {
            enqueueResponse("organization-repos.json")

            val resultResponse = service.getRepos("test")

            assertNotNull(resultResponse)
        }
    }

    @Test
    fun `request repos and validate response size`() {
        runBlocking {
            enqueueResponse("organization-repos.json")
            val resultResponse = service.getRepos("test")

            assertThat(resultResponse.size, `is`(4))
        }
    }

    @Test
    fun `request repos and validate Repo object fields`() {
        runBlocking {
            enqueueResponse("organization-repos.json")
            val resultResponse = service.getRepos("test")

            val repo = resultResponse[0]
            assertThat(repo?.name, `is`("fruit"))
            assertThat(repo?.owner?.name, `is`("fruit"))
            assertThat(repo?.owner?.avatarUrl, `is`("https://avatars3.githubusercontent.com/u/546231?v=4"))
            assertThat(repo?.url, `is`("https://github.com/octocat/Hello-World"))
            assertThat(repo?.description, `is`("Fruits!"))
            assertThat(repo?.stars, `is`(9876))
            assertThat(repo?.forks, `is`(1234))
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("api/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }
}