package com.cs4520.assignment4

import android.content.Context
import androidx.work.WorkManager

object WorkManagerProvider {
    fun getWorkManager(context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }
}