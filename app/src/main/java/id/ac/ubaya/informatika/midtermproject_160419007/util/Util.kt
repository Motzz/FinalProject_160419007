package id.ac.ubaya.informatika.midtermproject_160419007.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserDatabase
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResepDtabase

fun ImageView.loadImage(url: String, progressBar: ProgressBar) {

    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
            }

        })

}

fun ImageView.loadImage(url: String) {

    Picasso.get()
            .load(url)
            .resize(400, 400)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_24)

}
@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoUrl(v: ImageView, url: String?, pb: ProgressBar)
{
    if(url!=null)
    {
        v.loadImage(url!!, pb)
    }


}
fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
    {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)
        val notificationManager =context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

val DB_NAME="newuserdb"

fun buildDB(context: Context): UserDatabase {
    val db = Room.databaseBuilder(context, UserDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()

    return db
}

val DB_NAME2="newresepdb"

fun buildDB2(context: Context): UserResepDtabase {
    val db = Room.databaseBuilder(context, UserResepDtabase::class.java, DB_NAME2)
            .addMigrations()
            .build()

    return db
}

val MIGRATION_1_2=object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE User ADD COLUMN date INTEGER DEFAULT 0 NOT NULL")
    }

}
val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the new table
        database.execSQL(
                "CREATE TABLE users_new (idP INTEGER, email STRING, pass STRING,ImageUrl STRING,date STRING, PRIMARY KEY(idP))")
        // Remove the old table
        database.execSQL("DROP TABLE User")
        // Change the table name to the correct one
        database.execSQL("ALTER TABLE users_new RENAME TO User")
    }
}
