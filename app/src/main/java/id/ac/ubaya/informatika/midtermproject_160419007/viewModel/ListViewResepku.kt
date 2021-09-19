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
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resep
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resepku

class ListViewResepku(application: Application): AndroidViewModel(application)  {
    val ResepKuLD = MutableLiveData<List<Resepku>>()//live data
    val loadingErrorKuLD = MutableLiveData<Boolean>()
    val loadingKuLD= MutableLiveData<Boolean>()

    private val TAGKu = "VolleyTag"//terserah variable nya
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
    }
}