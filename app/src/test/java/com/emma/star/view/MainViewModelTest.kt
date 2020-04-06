package com.emma.star.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emma.star.network.GithubService
import com.emma.star.network.RepoRepositoryImpl
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: GithubService
    private lateinit var repository: RepoRepositoryImpl
    private lateinit var viewModel: MainViewModel
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

        repository = RepoRepositoryImpl(service)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test instance variables`() {
        assertThat(viewModel.repos.value, nullValue())
    }
}