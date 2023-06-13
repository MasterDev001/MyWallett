package com.example.r_usecase.repositoryimpl

import com.example.r_usecase.common.TRANSACTIONS
import com.example.r_usecase.common.USERS
import com.example.z_entity.db.daos.TransactionDao
import com.example.z_entity.db.entity.MyTransaction
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.TransactionRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class TransactionRepositoryImpl @Inject constructor(
    private val local: TransactionDao,
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) : TransactionRepository {

    private val fireStoreTransactionPath =
        fireStore.collection(USERS).document(authRepository.currentUser?.email.toString())
            .collection(TRANSACTIONS)

    override suspend fun add(transaction: MyTransaction): Long {
        val result = local.add(transaction)

        fireStoreTransactionPath.document(transaction.id)
            .set(transaction.toTransactionRemote().copy(date = System.currentTimeMillis()))
            .addOnSuccessListener {
                runBlocking {
                    local.add(transaction.copy(date = System.currentTimeMillis(), uploaded = true))
                }
            }
        return result
    }

    override suspend fun delete(transaction: MyTransaction): Int {
        val result = local.delete(transaction.id)

        fireStoreTransactionPath.document(transaction.id).delete().await()
        return result
    }

    override suspend fun getAll(): Flow<List<MyTransaction>> {
        return local.getAll()
    }
}