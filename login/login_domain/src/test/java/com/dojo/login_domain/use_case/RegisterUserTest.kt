package com.dojo.login_domain.use_case

import com.dojo.login_domain.model.User
import com.dojo.login_domain.model.UserRegister
import com.dojo.login_domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RegisterUserTest {

    lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun `User register successfully`() = runBlocking {
        val user = UserRegister("Juan","jperez@aaa.com", "password")
        val registerUser = RegisterUser(repository)
        coEvery { repository.register(user) } returns flow { emit(true) }
        assert(registerUser(user).first())
    }

    @Test
    fun `User register failure`() = runBlocking {
        val user = UserRegister("Juan","jperez@aaa.com", "password1")
        val registerUser = RegisterUser(repository)
        coEvery { repository.register(user) } returns flow { emit(false) }
        assert(!registerUser(user).first())
    }
}