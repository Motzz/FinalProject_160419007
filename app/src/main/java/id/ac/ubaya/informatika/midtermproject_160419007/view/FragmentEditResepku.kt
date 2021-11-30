package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentEditResepkuBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResep
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.DetailListResepku
import kotlinx.android.synthetic.main.fragment_edit_profil.*
import kotlinx.android.synthetic.main.fragment_edit_resepku.*

class FragmentEditResepku : Fragment(),UserUpdateResep {
    private lateinit var viewModelKu: DetailListResepku
    private lateinit var dataBinding:FragmentEditResepkuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentEditResepkuBinding>(inflater,R.layout.fragment_edit_resepku, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelKu = ViewModelProvider(this).get(DetailListResepku::class.java)
        val id=FragmentEditResepkuArgs.fromBundle(requireArguments()).NamaMakananEdit
        viewModelKu.fetch(id.toInt())
        dataBinding.listener=this

        /* if (arguments!=null)
         {
             Global.editFoodName=FragmentEditResepkuArgs.fromBundle(requireArguments()).NamaMakananEdit
         }*/

       // viewModelKu.fetchKu()
        observeViewModel()

        btnUpdateEditResepku.setOnClickListener {
            val action=FragmentEditResepkuDirections.actionFragmentEditResepkuToItemResepku()
            Navigation.findNavController(it).navigate(action)

        }
    }
    fun observeViewModel() {
        viewModelKu.resepLDku.observe(viewLifecycleOwner, Observer {
            dataBinding.userResep=it
          /*  txtEditIngridient1.setText((it.bahan?.get(0)))
            txtEditIngridient2.setText((it.bahan?.get(1)))
            txtEditIngridient3.setText((it.bahan?.get(2)))
            txtEditIngridient4.setText((it.bahan?.get(3)))
            txtEditStep1.setText((it.cara?.get(0)))
            txtEditStep2.setText((it.cara?.get(1)))
            txtEditStep3.setText((it.cara?.get(2)))
            txtEditStep4.setText((it.cara?.get(3)))
            txtImageURLedit.setText(it.imageURL)*/



        })
    }


    override fun onUserUpdateResep(v: View, obj: UserResep) {
        viewModelKu.update(obj.nama,obj.bahan,obj.cara,obj.ImageUrl,obj.idR)
        val action=FragmentEditResepkuDirections.actionFragmentEditResepkuToItemResepku()
        Navigation.findNavController(v).navigate(action)
        Toast.makeText(v.context,"ResepKu updated", Toast.LENGTH_SHORT).show()
    }


}