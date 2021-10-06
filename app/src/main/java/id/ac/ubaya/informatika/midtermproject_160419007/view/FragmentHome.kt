package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ListViewModel
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ListViewResepku
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentHome : Fragment() {

    private lateinit var viewModel:ListViewModel
    private  val ResepListAdapt = ResepListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtNameHome.text="Hello ${Global.username} Have a nice Day !"

        //tambahan in
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)//nama class view model
        viewModel.refresh()//load data
        //default layout
        val lm = LinearLayoutManager(context)
        //buat kalo mau kesamping aja
        //lm.orientation=LinearLayoutManager.HORIZONTAL
        //kalo ada 2 datanya kesamping (grid)
        val glm= GridLayoutManager(context,2)
        //kalo make straggle grid
        val sg = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recView.layoutManager = glm
        recView.adapter=ResepListAdapt

        //untuk refresh
        refreshLayout.setOnRefreshListener {
            recView.visibility=View.GONE
            txtError.visibility=View.GONE
            progressLoad.visibility=View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()

    }
    fun observeViewModel() {
        viewModel.ResepLD.observe(viewLifecycleOwner, Observer {
            ResepListAdapt.updateResepList(it)//parameter 1=ownernya,2=observer(data list resep)
        })

        //kalo ada error di loadingnya
        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it)
            {
                txtError.visibility=View.VISIBLE
            }
            else
            {
                txtError.visibility=View.GONE
            }
        })

        //progress load
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it)//kalo lagi loading
            {
                progressLoad.visibility=View.VISIBLE
                recView.visibility=View.GONE


            }
            else
            {
                progressLoad.visibility=View.GONE
                recView.visibility=View.VISIBLE


            }
        })
    }

}