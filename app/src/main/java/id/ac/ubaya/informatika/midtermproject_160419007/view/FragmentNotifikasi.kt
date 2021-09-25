package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.notif
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.notifViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_notifikasi.*
import java.util.concurrent.TimeUnit


class FragmentNotifikasi : Fragment() {

    private lateinit var viewModel: notifViewModel
    private  val notifListAdapter = NotifListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifikasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(notifViewModel::class.java)//nama class view model
        viewModel.refresh()//load data

        recViewNotif.layoutManager = LinearLayoutManager(context)
        recViewNotif.adapter=notifListAdapter
        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.notifLD.observe(viewLifecycleOwner, Observer {
            notifListAdapter.updateNotifList(it)
            var notif = it
            Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "five seconds")
                    MainActivity.ShowNotification(Global.notifku,
                        "New Notification !",
                        R.drawable.makanku)
                }
        })
    }


}