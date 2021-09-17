package id.ac.ubaya.informatika.midtermproject_160419007.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resep

class DetailListResep(application: Application): AndroidViewModel(application) {

    val resepLD = MutableLiveData<Resep>()

    private val TAGR = "VolleyTag"//terserah variable nya
    private var queueR: RequestQueue?=null

    fun fetch() {
        queueR= Volley.newRequestQueue(getApplication())
        var url="https://api.npoint.io/a79c5aa461093935180e?id=${Global.idResepList}"
        var stringRequest= StringRequest(
            Request.Method.GET,url,
            {response ->
                val sType=object : TypeToken<List<Resep>>(){ }.type
                val result1: Resep? = Gson().fromJson(response, Resep::class.java)
                resepLD.value=result1

                Log.d("berhasil",response.toString())

            },
            {
                Log.d("gagal",it.toString())
            })
        stringRequest.tag=TAGR
        queueR?.add(stringRequest)
    }
}