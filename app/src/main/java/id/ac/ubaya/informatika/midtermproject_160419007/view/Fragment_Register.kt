package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentRegisterBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.User
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ProfilViewModel

class Fragment_Register : Fragment(),UserRegisterListener {
    private lateinit var  viewModel:ProfilViewModel
    private lateinit var  dataBinding:FragmentRegisterBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,R.layout.fragment__register, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProfilViewModel::class.java)
        dataBinding.user=User("","","","")
        dataBinding.listener=this


       /* btnRegisR.setOnClickListener {
            //val action = Fragment_RegisterDirections.actionFragmentLogin()

            var userReg=User(txtUsernameRegis.text.toString(),txtEmailRegis.text.toString(),txtPwdRegis.text.toString(),txtUrlGambar.text.toString())
            viewModel.register(userReg)
            Toast.makeText(view.context, "register sucsess", Toast.LENGTH_LONG).show()
            //Navigation.findNavController(it).navigate(action)
            Navigation.findNavController(it).popBackStack()



        }*/
    }

    override fun onUserRegister(v: View) {
        //val action = Fragment_RegisterDirections.actionFragmentLogin()
        var regis=User(dataBinding?.user!!.username,dataBinding?.user!!.email,dataBinding?.user!!.pass,dataBinding?.user!!.ImageUrl)
        viewModel.register(regis)
        //viewModel.register(dataBinding.user!!)
        //Log.d("data","${dataBinding.user}")
        Toast.makeText(v.context, "${dataBinding?.user!!.username}", Toast.LENGTH_LONG).show()
        //Navigation.findNavController(it).navigate(action)
        Navigation.findNavController(v).popBackStack()
    }


}