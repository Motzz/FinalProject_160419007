package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ac.ubaya.informatika.midtermproject_160419007.R
import kotlinx.android.synthetic.main.fragment_news_bottom.*


class NewsBottomFragment : BottomSheetDialogFragment()  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack.setOnClickListener {
            dismiss()

        }
    }


}