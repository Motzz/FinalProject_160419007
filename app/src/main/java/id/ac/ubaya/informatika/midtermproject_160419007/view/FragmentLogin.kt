package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentLoginBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.User
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ProfilViewModel


class FragmentLogin : Fragment(),UserRegisterListener,UserLoginListener {
    private lateinit var  viewModel: ProfilViewModel
    private lateinit var  dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProfilViewModel::class.java)
        dataBinding.listener=this
        dataBinding.listener1=this
        dataBinding.user= User("","","","","")
    }

    override fun onUserLogin(v: View) {

        viewModel.login(dataBinding?.user!!.username,dataBinding?.user!!.pass)

         if (viewModel.result=="NO")
         {
             Toast.makeText(v.context, "please insert the username and password", Toast.LENGTH_LONG).show()
         }
         else if(viewModel.result=="WRONG")
         {
             Toast.makeText(v.context, "Wrong Username or Password", Toast.LENGTH_LONG).show()
         }
         else if(viewModel.result=="OK")
         {
             Toast.makeText(v.context, "Welcome!!", Toast.LENGTH_LONG).show()
             val action = FragmentLoginDirections.actionMainActivity()
             Navigation.findNavController(v).navigate(action)
             Global.username=dataBinding?.user!!.username

             /*Toast.makeText(v.context, "${Global.username}", Toast.LENGTH_LONG).show()*/
         }




    }

    override fun onUserRegister(v: View) {
        val action = FragmentLoginDirections.actionFragmentRegister()
        Navigation.findNavController(v).navigate(action)
    }


}