package id.ac.ubaya.informatika.midtermproject_160419007.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "email")
    var email:String,
    @ColumnInfo(name = "pass")
    var pass:String,
    @ColumnInfo(name = "ImageUrl")
    var ImageUrl:String,
    @ColumnInfo(name="date")
    var date:String

    ){
    @PrimaryKey(autoGenerate = true)
    var idP:Int=0
}