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
        var url="https://api.npoint.io/a79c5aa461093935180e?"
        var stringRequest= StringRequest(
            Request.Method.GET,url,
            {response ->

                val sType=object : TypeToken<Resep>(){ }.type
                val result1: List<Resep> = Gson().fromJson(response, Array<Resep>::class.java).toList()

                    if (Global.foodName=="Crock Pot Roast")
                    {
                        resepLD.value=result1[0]
                    }
                    else if (Global.foodName=="Roasted Asparagus")
                    {
                        resepLD.value=result1[1]

                    }
                    else if (Global.foodName=="Curried Lentils and Rice")
                    {
                        resepLD.value=result1[2]

                    }
                    else if (Global.foodName=="Big Night Pizza")
                    {
                        resepLD.value=result1[3]

                    }
                    else if (Global.foodName=="Cranberry and Apple")
                    {
                        resepLD.value=result1[4]
                    }
                    else if (Global.foodName=="Mic's Yorkshire Puds")
                    {
                        resepLD.value=result1[5]
                    }

                Log.d("berhasil",response.toString())

            },
            {
                Log.d("gagal",it.toString())
            })
        stringRequest.tag=TAGR
        queueR?.add(stringRequest)
    }
}