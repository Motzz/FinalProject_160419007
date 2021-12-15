package id.ac.ubaya.informatika.midtermproject_160419007.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResep
import id.ac.ubaya.informatika.midtermproject_160419007.util.buildDB2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewResepku(application: Application): AndroidViewModel(application), CoroutineScope {
    val ResepKuLD = MutableLiveData<List<UserResep>>()//live data
    val loadingErrorKuLD = MutableLiveData<Boolean>()
    val loadingKuLD= MutableLiveData<Boolean>()

    fun refresh() { //method untuk select all dari kueri todoDao
        loadingKuLD.value = true
        loadingErrorKuLD.value = false
        launch {
            val db = buildDB2(getApplication())//pemanggilan database
            ResepKuLD.value = db.resepUserDao().selectAllUserResep()//dan select all lagi
        }
    }

    fun clearResep(userResep: UserResep) { //method kueri untuk delete
       launch{
           val db = buildDB2(getApplication())//pemanggilan database
           db.resepUserDao().deleteResep(userResep)
           ResepKuLD.value = db.resepUserDao().selectAllUserResep()//dan select all lagi
       }
    }

    /*fun cariResep(searchResepii:String) {
        launch {
            val db = buildDB2(getApplication())//pemanggilan database
            ResepKuLD.value = db.resepUserDao().searchResep(searchResepii)
        }
    }*/

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main//

























































    /*private val TAGKu = "VolleyTag"//terserah variable nya
    private var queueKu: RequestQueue?=null

      fun refresh() {
        loadingKuLD.value = true
        loadingErrorKuLD.value = false
        queueKu= Volley.newRequestQueue(getApplication())
        var url="https://api.npoint.io/7b36bfca18ba59a070e3"
        var stringRequest= StringRequest(
            Request.Method.GET,url,
            //kalo berahsil
            {response ->
                val sType=object : TypeToken<List<Resepku>>(){ }.type
                val result= Gson().fromJson<List<Resepku>>(response,sType)
                ResepKuLD.value=result

                loadingKuLD.value=false
                Log.d("berhasil",response.toString())

            },
            {
                loadingErrorKuLD.value=true
                loadingKuLD.value=false
                Log.d("showVolley",it.toString())
            })
        stringRequest.tag=TAGKu
        queueKu?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queueKu?.cancelAll(TAGKu)
    }*/
}