package com.dojo.login_domain.use_case

import com.dojo.login_domain.model.User
import com.dojo.login_domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginUserTest {

    lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun `User login successfully`() = runBlocking {
        val user = User("jperez@gmail.com", "password")
        coEvery { repository.login(user) } returns flow { emit(true) }
        val loginUser = LoginUser(repository)
        assert(loginUser(user).first())
    }

    @Test
    fun `User login failure`() = runBlocking {
        val user = User("jperez@gmail.com", "password1")
        coEvery { repository.login(user) } returns flow { emit(false) }
        val loginUser = LoginUser(repository)
        assert(!loginUser(user).first())
    }

}