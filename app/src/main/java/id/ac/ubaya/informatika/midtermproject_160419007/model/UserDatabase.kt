package id.ac.ubaya.informatika.midtermproject_160419007.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version =  1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun profilDao(): ProfilDao

    companion object {
        @Volatile private var instance: UserDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "newuserdb").build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }


    }


}