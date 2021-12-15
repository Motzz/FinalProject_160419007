package id.ac.ubaya.informatika.midtermproject_160419007.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.view.MainActivity

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID="Resep_channel_id"
    private val NOTIFICATION_ID=1

    private fun notificationChanel()
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            val channel=NotificationChannel(CHANNEL_ID,CHANNEL_ID,NotificationManager.IMPORTANCE_DEFAULT).apply { //apply akan dijalankan ketika tidak null
                description="TO DO CHANNEL DESCRIPTION"

            }
            val notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title:String,message:String)
    {
        notificationChanel()
        val intent=Intent(context,MainActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        }
        val pendingIntent=PendingIntent.getActivity(context,0,intent,0)

        val icon= BitmapFactory.decodeResource(context.resources, R.drawable.makanku)
        val notification=NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.makanku)
            .setLargeIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(icon)
                    .bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)


    }




}