package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tambah_resepku.*
import java.util.concurrent.TimeUnit


class FragmentTambahResepku : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tambah_resepku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddResepku.setOnClickListener {
            val action = FragmentTambahResepkuDirections.actionFragmentTambahResepkuToItemResepku()
            Navigation.findNavController(it).navigate(action)

        }
    }



}