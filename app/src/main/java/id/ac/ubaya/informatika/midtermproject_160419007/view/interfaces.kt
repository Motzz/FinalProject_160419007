package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.view.View
import id.ac.ubaya.informatika.midtermproject_160419007.model.User

interface UserLoginListener {
    fun onUserLogin(v:View)
}

interface UserRegisterListener {
    fun onUserRegister(v:View)
}

interface UserEditProfil{
    fun onUserEditProfil(v:View)
}
interface SaveChangesUserListener{
    fun onSaveChangesUser(v:View,obj: User)
}

