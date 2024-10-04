package br.com.devjmcn.workmanagerandjobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.impl.WorkManagerImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        workManager()
        jodScheduler()
    }

    private fun jodScheduler() {
        //configurar job
        val componentName = ComponentName(this, MyJobScheduler::class.java)
        val jobInfo = JobInfo.Builder(1,componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) // realizar tarefa apenas no wifi
            .setRequiresCharging(true)
            .build()

        //agenda execução do job
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }

    private fun workManager() {
        //cria a requisição unica de trabalho de trabalho
        val requestWork = OneTimeWorkRequestBuilder<MyWorkManager>().build()

        //envia a requisição para a execução
        WorkManager.getInstance(this).enqueue(requestWork)
    }


}