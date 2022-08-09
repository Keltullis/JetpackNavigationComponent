package com.bignerdranch.android.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
// Создаём граф навигации, плюсом добавляем экраны и устанавливаем связи