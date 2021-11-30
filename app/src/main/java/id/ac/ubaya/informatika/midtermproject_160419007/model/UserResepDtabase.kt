package id.ac.ubaya.informatika.midtermproject_160419007.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserResep::class), version =  1)

abstract class UserResepDtabase:RoomDatabase() {
    abstract fun resepUserDao():ResepUserDao

    companion object {
        @Volatile private var instance: UserResepDtabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                    UserResepDtabase::class.java,
                "newresepdb").build()

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