package br.com.devjmcn.workmanagerandjobscheduler

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

        //cria a requisição de trabalho
        val requestWork = OneTimeWorkRequestBuilder<MyWorkManager>().build()

        //envia a requisição para a execução
        WorkManager.getInstance(this).enqueue(requestWork)
    }
}