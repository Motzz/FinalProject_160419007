package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail_resep.*
import kotlinx.android.synthetic.main.fragment_edit_profil.*
import kotlinx.android.synthetic.main.fragment_profil.*


class FragmentEditProfil : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewEditProfils.loadImage("http://img.sndimg.com/food/image/upload/w_266/v1/img/recipes/50/84/7/picMcSyVd.jpg")
        txtNameProf.setText("Timothy")
        txtEmailProf.setText("Surabaya2020@gmail.com")
        txtTglLahirProf.setText("11/04/01")
        txtPwdProf.setText("123456")

        btnUpdate.setOnClickListener {
            val action = FragmentEditProfilDirections.actionAkun()
            //hanya nav controller yang bisa mengontrol host nya
            Navigation.findNavController(it).navigate(action)
        }
    }


}