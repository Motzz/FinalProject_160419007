package id.ac.ubaya.informatika.midtermproject_160419007.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserResep(
        @ColumnInfo(name = "nama")
        var nama:String,
        @ColumnInfo(name = "bahan")
        var bahan:String,
        @ColumnInfo(name = "cara")
        var cara:String,
        @ColumnInfo(name = "ImageUrl")
        var ImageUrl:String
){
    @PrimaryKey(autoGenerate = true)
    var idR:Int=0
}