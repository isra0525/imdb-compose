package com.dojo.login_data.repository

import com.dojo.login_domain.model.User
import com.dojo.login_domain.model.UserRegister
import com.dojo.login_domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): LoginRepository {

    override fun login(user: User): Flow<Boolean> {
        return callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(true)
                } else {
                    trySend(false)
                }
            }
            awaitClose { this.cancel() }
        }
    }

    override fun register(user: UserRegister): Flow<Boolean> {
        return callbackFlow {
            firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(true)
                } else {
                    trySend(false)
                }
            }
            awaitClose { this.cancel() }
        }
    }

    override suspend  fun logout() {
        firebaseAuth.signOut()
    }

    override val currentUser: Flow<Boolean>
        get() = callbackFlow {
            firebaseAuth.currentUser?.let {
                this.trySend(true)
            }?: this.trySend(false)
            awaitClose { this.cancel() }
        }
}