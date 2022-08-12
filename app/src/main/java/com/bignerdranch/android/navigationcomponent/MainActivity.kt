package com.bignerdranch.android.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // если navController смог выйти на предыдущую активити,то ок
        // а если нет,то передаём управление активити
        return if(navController.navigateUp()){
            true
        } else{
            super.onSupportNavigateUp()
        }
    }
}
// Создаём граф навигации, плюсом добавляем экраны и устанавливаем связи