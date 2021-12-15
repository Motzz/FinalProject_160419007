package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentEditProfilBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.User
import id.ac.ubaya.informatika.midtermproject_160419007.util.loadImage
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment__register.view.*
import kotlinx.android.synthetic.main.fragment_detail_resep.*
import kotlinx.android.synthetic.main.fragment_edit_profil.*
import kotlinx.android.synthetic.main.fragment_edit_profil.view.*
import kotlinx.android.synthetic.main.fragment_profil.*
import java.util.*


class FragmentEditProfil : Fragment(),SaveChangesUserListener,UserDateClickListener, DatePickerDialog.OnDateSetListener {
    private lateinit var  viewModel: ProfilViewModel
    private lateinit var  dataBinding:FragmentEditProfilBinding

    var year=0
    var moth=0
    var day=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_edit_profil, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.userEma(Global.username)
        observerViewModel()
        dataBinding.listener=this
        dataBinding.listenerDate=this

        imageViewEditProfils.loadImage("http://img.sndimg.com/food/image/upload/w_266/v1/img/recipes/50/84/7/picMcSyVd.jpg")
    }

    fun observerViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user=it
        })
    }

    override fun onSaveChangesUser(v: View, obj: User) {
        val c = Calendar.getInstance()
        //c.set(year,moth,day,hour,minute,0)
        //val today=Calendar.getInstance()
        //dataBinding?.user!!.date=c.toString()
        viewModel.update(obj.username,obj.email,obj.pass,obj.ImageUrl,obj.date,obj.idP)
        Toast.makeText(v.context,"Profil updated", Toast.LENGTH_SHORT).show()
        val action = FragmentEditProfilDirections.actionAkun()
        Navigation.findNavController(v).navigate(action)
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
            dataBinding?.root!!.txtTglProf.setText(dayOfMonth.toString().padStart(2,'0')+"-"+
                    (month+1).toString().padStart(2,'0')+"-"+
                    year.toString().padStart(2,'0'))
            this.year=year
            this.moth=month
            this.day=dayOfMonth
        }
    }


}