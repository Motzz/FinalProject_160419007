package id.ac.ubaya.informatika.midtermproject_160419007.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resep
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val ResepLD = MutableLiveData<List<Resep>>()//live data
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD= MutableLiveData<Boolean>()

    private val TAG = "VolleyTag"//terserah variable nya
    private var queue:RequestQueue?=null

    fun refresh() {
        loadingLD.value = true
        loadingErrorLD.value = false
        queue=Volley.newRequestQueue(getApplication())
        var url="https://api.npoint.io/a79c5aa461093935180e"
        var stringRequest=StringRequest(Request.Method.GET,url,
                //kalo berahsil
                {response ->
                    val sType=object :TypeToken<List<Resep>>(){ }.type
                    val result=Gson().fromJson<List<Resep>>(response,sType)
                    ResepLD.value=result

                    loadingLD.value=false
                    Log.d("berhasil",response.toString())

                },
                {
                    loadingErrorLD.value=true
                    loadingLD.value=false
                    Log.d("showVolley",it.toString())
                })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}