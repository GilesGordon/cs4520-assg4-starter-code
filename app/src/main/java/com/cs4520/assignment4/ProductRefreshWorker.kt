package com.cs4520.assignment4

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cs4520.assignment4.databases.Api
import com.cs4520.assignment4.databases.AppDatabase
import com.cs4520.assignment4.databases.ProductRepository

class ProductRefreshWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val productApi = Api.productApi
            val productDao = AppDatabase.getDatabase(applicationContext).productDao()
            val repository = ProductRepository(productApi, productDao)

            // Fetch products from the API and insert into DB using the repository
            val products = repository.getProducts()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}