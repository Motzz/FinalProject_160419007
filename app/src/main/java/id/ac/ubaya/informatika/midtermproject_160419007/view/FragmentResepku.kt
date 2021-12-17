package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResep
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ListViewResepku
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_resepku.*
import kotlinx.android.synthetic.main.fragment_resepku.view.*


class FragmentResepku : Fragment()/*,SearchView.OnQueryTextListener*/{

    private lateinit var viewModelKu: ListViewResepku
    private  val ResepKuListAdapt = ResepKuListAdapter(arrayListOf(), { item -> doClick(item) })
   // private lateinit var  dataBinding:FragmentResepkuBinding

    private lateinit var swipe:SwipeRefreshLayout

    fun doClick(item: Any)//untuk delete
    {
        viewModelKu.clearResep(item as UserResep)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resepku, container, false)
      /*  dataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_resepku, container, false)
        return dataBinding.root*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelKu = ViewModelProvider(this).get(ListViewResepku::class.java)
        viewModelKu.refresh()//load data

       // dataBinding.viewModelRefresh

        recViewKu.layoutManager = LinearLayoutManager(context)
        recViewKu.adapter=ResepKuListAdapt
        //untuk refresh
        refreshLayoutKu.setOnRefreshListener {
            recViewKu.visibility=View.GONE
            txtErrorKu.visibility=View.GONE
            ProgressLoadKu.visibility=View.VISIBLE
            viewModelKu.refresh()
            refreshLayoutKu.isRefreshing = false
        }
        fabTambahResep.setOnClickListener {
            val action = FragmentResepkuDirections.actionItemResepkuToFragmentTambahResepku()
            Navigation.findNavController(it).navigate(action)
        }
        btnCari.setOnClickListener {
            search()
        }
        observeViewModel()
    }
    private fun search()
    {
        val kueri= txtSearch.text.toString()
        val cari= "%$kueri%"
        viewModelKu.cariResep(cari)
    }



    fun observeViewModel() {
        viewModelKu.ResepKuLD.observe(viewLifecycleOwner, Observer {
            ResepKuListAdapt.updateResepKu(it)//parameter 1=ownernya,2=observer(data list resepku)
            if (it.isEmpty()) {
                txtErrorKu.visibility = View.VISIBLE
                ProgressLoadKu.visibility=View.VISIBLE
                recViewKu.visibility=View.GONE
            } else {
                txtErrorKu.visibility = View.GONE
                ProgressLoadKu.visibility=View.GONE
                recViewKu.visibility=View.VISIBLE
            }
        })
    }

  /*  override fun onUserPindahAdd(v: View) {
        val action = FragmentResepkuDirections.actionItemResepkuToFragmentTambahResepku()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onRefresh() {
        recViewKu.visibility=View.GONE
        txtErrorKu.visibility=View.GONE
        ProgressLoadKu.visibility=View.VISIBLE
        viewModelKu.refresh()
        refreshLayoutKu.isRefreshing = false
    }

    override fun onRefreshResepku(r: SwipeRefreshLayout.OnRefreshListener) {
        recViewKu.visibility=View.GONE
        txtErrorKu.visibility=View.GONE
        ProgressLoadKu.visibility=View.VISIBLE
        viewModelKu.refresh()
        refreshLayoutKu.isRefreshing = false
    }*/


     /*override fun onQueryTextSubmit(query: String?): Boolean {
         if (query!=null)
         {
             search()
         }
         return true
     }

     override fun onQueryTextChange(newText: String?): Boolean {
         if (newText!=null)
         {
             search()
         }
         return true
     }*/





}

private fun SwipeRefreshLayout.setRefresing(b: Boolean) {

}
