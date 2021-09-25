package id.ac.ubaya.informatika.midtermproject_160419007.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.util.createNotificationChannel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    //penambahan notif===============================================================================================
    init {
        instance = this
    }

    companion object{
        private var instance:MainActivity ?=null
        fun ShowNotification(title:String,content:String,icon:Int){
            val channelId =
                "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext,channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext)//di slide kedobelan
            notificationManager.notify(1001, notificationBuilder.build())

        }

    }
    //============================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //notif
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,getString(R.string.app_name), "App notification channel.")

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