package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ListViewModel
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

        //tambahan in

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)//nama class view model
        viewModel.refresh()//load data

        recView.layoutManager = LinearLayoutManager(context)
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
            ResepListAdapt.updateResepList(it)//parameter 1=ownernya,2=observer(data list student)
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