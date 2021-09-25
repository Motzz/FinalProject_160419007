package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resep
import id.ac.ubaya.informatika.midtermproject_160419007.util.loadImage
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.resep_card_layout.view.*

class ResepListAdapter(val resepList: ArrayList<Resep>): RecyclerView.Adapter<ResepListAdapter.ResepViewHolder>() {
    class ResepViewHolder(val view: View):RecyclerView.ViewHolder(view)

    //method untuk refresh data supaya ke update
    fun updateResepList(newResepList:List<Resep>)
    {
        resepList.clear()
        resepList.addAll(newResepList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.resep_card_layout,parent,false)
        return ResepViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResepViewHolder, position: Int) {
        with(holder.view)
        {
            txtCardNamaMakanan.text=resepList[position].name
            imageView2.loadImage(resepList[position].imageURL.toString(),holder.view.progressLoadResep)
            btnLike.text=resepList[position].like

            btnDetailResep.setOnClickListener {
                val action = FragmentHomeDirections.actionFragmentDetail(resepList[position].name.toString())//kirim data id
                Navigation.findNavController(it).navigate(action)
            }
        }


    }

    override fun getItemCount(): Int {
        return resepList.size
    }
}