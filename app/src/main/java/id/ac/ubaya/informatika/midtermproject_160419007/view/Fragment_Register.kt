package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentRegisterBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.User
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment__register.view.*
import java.util.*

class Fragment_Register : Fragment(),UserRegisterListener,UserDateClickListener, DatePickerDialog.OnDateSetListener {
    private lateinit var  viewModel:ProfilViewModel
    private lateinit var  dataBinding:FragmentRegisterBinding
    var year=0
    var moth=0
    var day=0
    var hour=0
    var minute=0


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
        dataBinding.user=User("","","","","")
        dataBinding.listener=this
        dataBinding.listenerDate=this

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
        val c = Calendar.getInstance()
        //c.set(year,moth,day,hour,minute,0)
        //val today=Calendar.getInstance()
        //dataBinding?.user!!.date=c.toString()
        //dataBinding?.user!!.date=(c.set(year,moth,day).toString().toInt())
        //var regis=User(dataBinding?.user!!.username,dataBinding?.user!!.email,dataBinding?.user!!.pass,dataBinding?.user!!.ImageUrl,dataBinding?.user!!.date)
        viewModel.register(dataBinding.user!!)
        //Log.d("data","${dataBinding.user}")
        //Toast.makeText(v.context, "${dataBinding?.user!!.username}", Toast.LENGTH_LONG).show()
        //Navigation.findNavController(it).navigate(action)
        Navigation.findNavController(v).popBackStack()
    }

    override fun onUserDateClick(v: View) {
        val c = Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)

        activity?.let {
            it->DatePickerDialog(it,this,year,month,day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Calendar.getInstance().let {
           it.set(year,month,dayOfMonth)
           dataBinding.root.txtTglLahir.setText(dayOfMonth.toString().padStart(2,'0')+"-"+
                   (month+1).toString().padStart(2,'0')+"-"+
                                               year.toString().padStart(2,'0'))
           this.year=year
           this.moth=month
           this.day=dayOfMonth
       }
    }


}