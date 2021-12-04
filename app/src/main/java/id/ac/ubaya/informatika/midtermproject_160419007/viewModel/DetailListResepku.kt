package id.ac.ubaya.informatika.midtermproject_160419007.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResep
import id.ac.ubaya.informatika.midtermproject_160419007.util.buildDB2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailListResepku(application: Application): AndroidViewModel(application),CoroutineScope {

    val resepLDku = MutableLiveData<UserResep>()

    //week9
    fun fetch(idR:Int)
    {
        launch{
            val db = buildDB2(getApplication())//pemanggilan database
            resepLDku.value=db.resepUserDao().selectResep(idR)
        }

    }

    fun update(nama: String,bahan:String,cara: String,imgURL:String,idR: Int)
    {
        launch {
            val db = buildDB2(getApplication())//pemanggilan database
            db.resepUserDao().update(nama,bahan,cara,imgURL,idR)
        }
    }


    //week8
    fun addResep(userResep: UserResep) {
        launch {
            val db = buildDB2(getApplication())//pemanggilan database
            db.resepUserDao().insertAll(userResep)
        }
    }


    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main//



























    /* private val TAGRku = "VolleyTag"//terserah variable nya
     private var queueRku: RequestQueue?=null

     fun fetchKu() {
         queueRku= Volley.newRequestQueue(getApplication())
         var url="https://api.npoint.io/7b36bfca18ba59a070e3"
         var stringRequest= StringRequest(
             Request.Method.GET,url,
             {response ->

                 val sType=object : TypeToken<Resepku>(){ }.type
                 val result1: List<Resepku> = Gson().fromJson(response, Array<Resepku>::class.java).toList()

                 if (Global.editFoodName=="Nasi Goreng")
                 {
                     resepLDku.value=result1[0]
                 }
                 else if (Global.editFoodName=="Mie Goreng")
                 {
                     resepLDku.value=result1[1]
                 }


                 Log.d("berhasil",response.toString())

             },
             {
                 Log.d("gagal",it.toString())
             })
         stringRequest.tag=TAGRku
         queueRku?.add(stringRequest)
     }*/
}