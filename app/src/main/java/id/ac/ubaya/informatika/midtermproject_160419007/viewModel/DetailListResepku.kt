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
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resepku

class DetailListResepku(application: Application): AndroidViewModel(application) {

    val resepLDku = MutableLiveData<Resepku>()

    private val TAGRku = "VolleyTag"//terserah variable nya
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
    }
}