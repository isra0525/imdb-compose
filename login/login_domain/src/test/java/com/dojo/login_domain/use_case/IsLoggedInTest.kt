package com.dojo.login_domain.use_case

import com.dojo.login_domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class IsLoggedInTest {

    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRepository = mockk()
    }

    @Test
    fun `if user is logged in, return true`() = runBlocking {
        coEvery { loginRepository.currentUser } returns flow { emit(true) }
        val isLoggedIn = IsLoggedIn(loginRepository)
        assert(isLoggedIn().first())
    }

    @Test
    fun `if user is not logged in, return false`() = runBlocking {
        coEvery { loginRepository.currentUser } returns flow { emit(false) }
        val isLoggedIn = IsLoggedIn(loginRepository)
        assert(!isLoggedIn().first())
    }
}