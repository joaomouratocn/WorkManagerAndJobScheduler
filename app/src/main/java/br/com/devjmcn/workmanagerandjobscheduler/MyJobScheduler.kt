package br.com.devjmcn.workmanagerandjobscheduler

import android.app.job.JobParameters
import android.app.job.JobService


//precisa declarar o serviço no manifest
class MyJobScheduler : JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        Thread.sleep(3000)
        jobFinished(p0,false)
        return true //quando a tarefa estiver finalizada
    }

    //acessar aqui caso a tarefa seja interrompida
    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }
}