package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import id.ac.ubaya.informatika.midtermproject_160419007.R
import kotlinx.android.synthetic.main.activity_register.*

class ActivityRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegisR.setOnClickListener {
            startActivity(Intent(this, ActivityLogin::class.java))
        }
    }
//    override fun onBackPressed() {
//        val intent = Intent(Intent.ACTION_MAIN)//akses home
//        intent.addCategory(Intent.CATEGORY_HOME)//menambah kategori home agar ketika di back langsung menuju home
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//menghapus history activity yang dibuka
//        startActivity(intent)
//    }

}

