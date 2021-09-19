package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.DetailListResepku
import kotlinx.android.synthetic.main.fragment_edit_profil.*
import kotlinx.android.synthetic.main.fragment_edit_resepku.*

class FragmentEditResepku : Fragment() {
    private lateinit var viewModelKu: DetailListResepku


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_resepku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (arguments!=null)
        {
            Global.editFoodName=FragmentEditResepkuArgs.fromBundle(requireArguments()).NamaMakananEdit
        }
        viewModelKu = ViewModelProvider(this).get(DetailListResepku::class.java)
        viewModelKu.fetchKu()
        observeViewModel()

        btnUpdateEditResepku.setOnClickListener {
            val action=FragmentEditResepkuDirections.actionFragmentEditResepkuToItemResepku()
            Navigation.findNavController(it).navigate(action)

        }
    }
    fun observeViewModel() {
        viewModelKu.resepLDku.observe(viewLifecycleOwner, Observer {
            txtEditNameFood.setText(it.nama)
            txtEditIngridient1.setText((it.bahan?.get(0)))
            txtEditIngridient2.setText((it.bahan?.get(1)))
            txtEditIngridient3.setText((it.bahan?.get(2)))
            txtEditIngridient4.setText((it.bahan?.get(3)))
            txtEditStep1.setText((it.cara?.get(0)))
            txtEditStep2.setText((it.cara?.get(1)))
            txtEditStep3.setText((it.cara?.get(2)))
            txtEditStep4.setText((it.cara?.get(3)))
            txtImageURLedit.setText(it.imageURL)



        })
    }


}