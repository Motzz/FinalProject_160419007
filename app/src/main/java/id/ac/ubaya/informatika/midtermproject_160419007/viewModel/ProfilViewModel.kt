package id.ac.ubaya.informatika .midtermproject_160419007.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.midtermproject_160419007.model.User
import id.ac.ubaya.informatika.midtermproject_160419007.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfilViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val userLD= MutableLiveData<User>()

    var result=""

    fun fetch(idP: Int)
    {

        launch{
            val db = buildDB(getApplication())//pemanggilan database dari util
            userLD.value=db.profilDao().selectUser(idP)
        }

    }
    fun userEma(username: String)
    {
        launch{
            val db = buildDB(getApplication())//pemanggilan database dari util
            userLD.value=db.profilDao().selectUserStr(username)
        }

    }
    fun register(user: User) {
        launch {
            val db = buildDB(getApplication())//pemanggilan database dari util
            db.profilDao().insertAll(user)
        }
    }
    fun update(username: String,email:String,pass:String,img: String,date:String,idP: Int)
    {
        launch {
            val db= buildDB(getApplication())
            db.profilDao().update(username,email,pass,img,date,idP)
        }
    }

    fun login(user:String,pass: String)
    {
        launch {
            val db= buildDB(getApplication())//pemanggilan database dari util
            if(user==""&&pass=="")
            {
                result="NO"
                Log.d("Global","no")
            }
            else if(user!= userLD.value?.username&&pass!=userLD.value?.pass)
            {
                result="WRONG"
                Log.d("Global","noWrong")
            }
            else
            {
                result="OK"
                Log.d("Global","ok")
            }
            userLD.value=db.profilDao().selectLogin(user, pass)


        }
    }
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}