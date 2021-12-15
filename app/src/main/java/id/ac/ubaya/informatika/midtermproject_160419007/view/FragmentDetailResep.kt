package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentDetailResepBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.util.loadImage
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.DetailListResep
import kotlinx.android.synthetic.main.fragment_detail_resep.*

class FragmentDetailResep : Fragment(),BackResep {
    private lateinit var viewModel: DetailListResep
    private lateinit var dataBinding: FragmentDetailResepBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding= DataBindingUtil.inflate<FragmentDetailResepBinding>(inflater,R.layout.fragment_detail_resep, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailListResep::class.java)
        dataBinding.listener=this
        if (arguments!=null)
        {
            Global.foodName=FragmentDetailResepArgs.fromBundle(requireArguments()).foodName
        }
        viewModel.fetch()
        observeViewModel()
        /*btnBackDetailRes.setOnClickListener {
            val action = FragmentDetailResepDirections.actionFragmentDetailResepToItemHome()
            //hanya nav controller yang bisa mengontrol host nya
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun observeViewModel() {
        viewModel.resepLD.observe(viewLifecycleOwner, Observer {
            dataBinding.resep=it
            /*txtNameFood.text=it.name.toString()
            txtIngridients.text= it.ingredients!!.joinToString("\n\n")
            txtSteps.text=it.steps!!.joinToString("\n\n")
            imageViewDetailResep.loadImage(it.imageURL.toString(),progressBarDetailResep)*/

        })
    }

    override fun onBackResep(v: View) {
        val action = FragmentDetailResepDirections.actionFragmentDetailResepToItemHome()
        Navigation.findNavController(v).navigate(action)
    }


}