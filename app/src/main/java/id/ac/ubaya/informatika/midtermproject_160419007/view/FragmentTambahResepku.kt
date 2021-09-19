package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import kotlinx.android.synthetic.main.fragment_tambah_resepku.*


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