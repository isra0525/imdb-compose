package com.dojo.login_domain.use_case

import com.dojo.login_domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class LogoutUserTest {

    lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        repository = mockk()
    }

}