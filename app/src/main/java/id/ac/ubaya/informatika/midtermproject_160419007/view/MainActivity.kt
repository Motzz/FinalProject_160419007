package id.ac.ubaya.informatika.midtermproject_160419007.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.informatika.midtermproject_160419007.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //masukkan nav controller di activity sekarang dan hostnya
        navController = Navigation.findNavController(this,R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)//nambah drawer layout untuk setup side bar
        NavigationUI.setupWithNavController(navView,navController)//untuk side bar


        //untuk bottom navigation
        bottomNav.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        //kalo null back button itu ke stack sebelumnya
        //dikarenakan ada drawer layout,maka ditambahkan drawerlayout
        //
        return NavigationUI.navigateUp(navController,drawerLayout) || super.onSupportNavigateUp()//nambah drawer layout untuk side bar
    }
}