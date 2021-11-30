package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class ActivityLogin : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        navController = Navigation.findNavController(this,R.id.fragmentHost)

//        btnLogin.setOnClickListener {
//            if(txtUsername.text.toString()=="Timothy"&&txtPwd.text.toString()=="123456")
//            {
//                startActivity(Intent(this, MainActivity::class.java))
//                Global.username=txtUsername.text.toString()
//            }
//            else
//            {
//                Toast.makeText(applicationContext, "Username/Password anda salah", Toast.LENGTH_LONG).show()
//            }
//
//        }
//        btnRegister.setOnClickListener {
//            startActivity(Intent(this, ActivityRegister::class.java))
//        }
    }
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)//akses home
        intent.addCategory(Intent.CATEGORY_HOME)//menambah kategori home agar ketika di back langsung menuju home
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//menghapus history activity yang dibuka
        startActivity(intent)
    }
    override fun onSupportNavigateUp(): Boolean {
        //kalo null back button itu ke stack sebelumnya
        //dikarenakan ada drawer layout,maka ditambahkan drawerlayout
        //
        return NavigationUI.navigateUp(navController,drawerLayout) || super.onSupportNavigateUp()//nambah drawer layout untuk side bar
    }
}