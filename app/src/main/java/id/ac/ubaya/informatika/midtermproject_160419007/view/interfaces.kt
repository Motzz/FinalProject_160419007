package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.view.View
import id.ac.ubaya.informatika.midtermproject_160419007.model.User
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResep

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
interface UserLogout{
    fun onUserLogout(v:View)
}

interface UserDateClickListener{
    fun onUserDateClick(v:View)
}
/*===================================For user resep=====================================*/

interface UserResepEdit{
    fun onUserResepEdit(v:View)
}
interface  UserAddResep{
    fun onUserAddResep(v:View)
}
interface  UserDeleteResep{
    fun onUserDeleteResep(v:View,obj: UserResep)
}
interface  UserUpdateResep{
    fun onUserUpdateResep(v:View,obj: UserResep)
}
interface  UserPindahAdd{
    fun onUserPindahAdd(v:View)
}
/*===================================For resep=====================================*/
interface ResepDetail{
    fun onResepDetail(v:View)
}
interface BackResep{
    fun onBackResep(v:View)
}

