package id.ac.ubaya.informatika.midtermproject_160419007.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfilDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)//bisa lebih dari 1 user

    @Query("SELECT * FROM user WHERE idP= :id")
    suspend fun selectUser(id:Int): User

    @Query("SELECT * FROM user WHERE username=:user AND pass=:pass")
    suspend fun selectLogin(user: String,pass:String): User
}