package id.ac.ubaya.informatika.midtermproject_160419007.model

import androidx.room.*

@Dao
interface ResepUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg UserResep: UserResep)//bisa lebih dari 1 user

    @Query("SELECT * FROM UserResep ORDER BY nama DESC")
    suspend fun selectAllUserResep(): List<UserResep>

    @Query("SELECT * FROM UserResep WHERE idR= :id")
    suspend fun selectResep(id:Int): UserResep

    @Query("UPDATE UserResep SET nama=:nama,bahan=:bahan,cara=:cara,ImageURL=:ImageURL WHERE idR=:idR")
    suspend fun update(nama:String,bahan:String,cara:String,ImageURL:String,idR:Int)

    @Delete
    suspend fun  deleteResep(userResep: UserResep)
}