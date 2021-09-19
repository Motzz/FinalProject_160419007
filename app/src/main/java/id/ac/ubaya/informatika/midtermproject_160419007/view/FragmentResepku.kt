package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ListViewModel
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ListViewResepku
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_resepku.*
import kotlinx.android.synthetic.main.fragment_resepku.view.*


class FragmentResepku : Fragment() {

    private lateinit var viewModelKu: ListViewResepku
    private  val ResepKuListAdapt = ResepKuListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resepku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelKu = ViewModelProvider(this).get(ListViewResepku::class.java)
        viewModelKu.refresh()//load data

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

        observeViewModel()
    }
    fun observeViewModel() {
        viewModelKu.ResepKuLD.observe(viewLifecycleOwner, Observer {
            ResepKuListAdapt.updateResepKu(it)//parameter 1=ownernya,2=observer(data list resepku)
        })

        //kalo ada error di loadingnya
        viewModelKu.loadingErrorKuLD.observe(viewLifecycleOwner, Observer {
            if(it)
            {
                txtErrorKu.visibility=View.VISIBLE
            }
            else
            {
                txtErrorKu.visibility=View.GONE
            }
        })

        //progress load
        viewModelKu.loadingKuLD.observe(viewLifecycleOwner, Observer {
            if(it)//kalo lagi loading
            {
                ProgressLoadKu.visibility=View.VISIBLE
                recViewKu.visibility=View.GONE

            }
            else
            {
                ProgressLoadKu.visibility=View.GONE
                recViewKu.visibility=View.VISIBLE
            }
        })
    }


}