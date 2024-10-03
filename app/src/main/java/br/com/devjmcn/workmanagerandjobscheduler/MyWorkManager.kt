package br.com.devjmcn.workmanagerandjobscheduler

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorkManager(context: Context, workParams: WorkerParameters) : Worker(context, workParams) {
    override fun doWork(): Result {
        return try {
            Thread.sleep(3000)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}