package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.FragmentTambahResepkuBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResep
import id.ac.ubaya.informatika.midtermproject_160419007.util.ResepWalker
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.DetailListResepku
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tambah_resepku.*
import java.util.concurrent.TimeUnit


class FragmentTambahResepku : Fragment(),UserAddResep {
    private lateinit var  viewModel:DetailListResepku
    private lateinit var  databinding:FragmentTambahResepkuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        databinding= DataBindingUtil.inflate(inflater,R.layout.fragment_tambah_resepku,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(DetailListResepku::class.java)
        databinding.userResep= UserResep("","","","")
        databinding.listener=this
    }

    override fun onUserAddResep(v: View) {
        viewModel.addResep(databinding.userResep!!)
        //notif pemanggilan week 12
        val myWorkRequest= OneTimeWorkRequestBuilder<ResepWalker>()
            .setInitialDelay(10,TimeUnit.SECONDS)
            .setInputData(workDataOf(
                "title" to "New Resep added",
                "message" to "Enjoy your new recipe!"))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        val action = FragmentTambahResepkuDirections.actionFragmentTambahResepkuToItemResepku()
        Navigation.findNavController(v).navigate(action)
    }


}