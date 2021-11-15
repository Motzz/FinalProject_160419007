package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.os.Debug
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
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentLoginBinding
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentRegisterBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.User
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import java.lang.Math.log
import java.lang.StrictMath.log


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
    //        btnLogin.setOnClickListener {
//            if(txtUsername.text.toString()=="Timothy"&&txtPwd.text.toString()=="123456")
//            {
//                startActivity(Intent(this, MainActivity::class.java))
//                Global.username=txtUsername.text.toString()
//            }
//            else
//            {
//                Toast.makeText(applicationContext, "Username/Password anda salah", Toast.LENGTH_LONG).show()
//            }
//
//        }
//        btnRegister.setOnClickListener {
//            startActivity(Intent(this, ActivityRegister::class.java))
//        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProfilViewModel::class.java)
        dataBinding.listener=this
        dataBinding.listener1=this
        dataBinding.user= User("","","","")
    }

    override fun onUserLogin(v: View) {



       /* if (txtUsername.text.toString()==viewModel.userLD.value?.username.toString()&&txtPwd.text.toString()==viewModel.userLD.value?.pass.toString())
        {
            val action = FragmentLoginDirections.actionMainActivity()
            Navigation.findNavController(v).navigate(action)
        }*/

        /*if (login.equals(viewModel.userLD.value?.username.toString())&&login.equals(viewModel.userLD.value?.pass.toString()))
        {

            val action = FragmentLoginDirections.actionMainActivity()
            Navigation.findNavController(v).navigate(action)
        }*/
        //if (login.equals(viewModel.userLD.value))
       // {
       // viewModel.login(txtUsername.text.toString(),txtPwd.text.toString())
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

             val action = FragmentLoginDirections.actionMainActivity()
             Navigation.findNavController(v).navigate(action)
         }


        //}
        /*else
        {
            Toast.makeText(v.context, "Wrong Username or Password", Toast.LENGTH_LONG).show()
        }*/

    }

    override fun onUserRegister(v: View) {
        val action = FragmentLoginDirections.actionFragmentRegister()
        Navigation.findNavController(v).navigate(action)
    }


}