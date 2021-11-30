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
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentProfilBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.util.loadImage
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_detail_resep.*
import kotlinx.android.synthetic.main.fragment_profil.*


class FragmentProfil : Fragment(),UserEditProfil,UserLogout {
    private lateinit var  viewModel: ProfilViewModel
    private lateinit var  dataBinding:FragmentProfilBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_profil, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.userEma(Global.username)
        dataBinding.editListener=this
        observerViewModel()
        //imageViewProfils.loadImage("http://img.sndimg.com/food/image/upload/w_266/v1/img/recipes/50/84/7/picMcSyVd.jpg")



    }
    fun observerViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user=it
        })
    }

    override fun onUserEditProfil(v: View) {
        val action = FragmentProfilDirections.actionEditProfil()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onUserLogout(v: View) {
        val action = FragmentProfilDirections.actionItemAkunToActivityLogin()
        //hanya nav controller yang bisa mengontrol host nya
        Navigation.findNavController(v).navigate(action)
    }

}