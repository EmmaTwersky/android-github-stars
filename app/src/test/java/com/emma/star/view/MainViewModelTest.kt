package com.emma.star.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emma.star.model.Repo
import com.emma.star.network.GithubService
import com.emma.star.network.RepoRepositoryImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
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

    @Test
    fun testInstanceVariables() {
        assertThat(viewModel.repos.value, nullValue())
        assertThat(viewModel.searchStatus, notNullValue())
    }

    @Test
    fun testSearchRepos() {
//        val repo : Repo = mock()
//        whenever(service.getRepos("test")) doReturn(arrayListOf<Any>(repo))
//        viewModel.searchRepos("test")
//
//        assertEquals("fruit", viewModel.repos.value?.get(0)?.name)
    }
}