package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resepku
import id.ac.ubaya.informatika.midtermproject_160419007.util.loadImage
import kotlinx.android.synthetic.main.fragment_resepku.view.*
import kotlinx.android.synthetic.main.resep_card_layout.view.*
import kotlinx.android.synthetic.main.resepku_card_layout.view.*

class ResepKuListAdapter(val resepku:ArrayList<Resepku>):RecyclerView.Adapter<ResepKuListAdapter.ResepKuViewHolder>() {

    class ResepKuViewHolder(val view: View):RecyclerView.ViewHolder(view)

    //method untuk refresh data supaya ke update
    fun updateResepKu(newResepKuList:List<Resepku>)
    {
        resepku.clear()
        resepku.addAll(newResepKuList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepKuViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.resepku_card_layout,parent,false)
        return ResepKuViewHolder(view)

    }

    override fun onBindViewHolder(holder: ResepKuViewHolder, position: Int) {
        with(holder.view)
        {
            txtMakananResepku.text=resepku[position].nama
            imgResepku.loadImage(resepku[position].imageURL.toString(),holder.view.progressLoadCardResepKu)
            btnEditResepku.setOnClickListener {
                val action = FragmentResepkuDirections.actionItemResepkuToFragmentTambahResepku()//kirim data id
                Navigation.findNavController(it).navigate(action)
            }


        }

    }

    override fun getItemCount(): Int {
        return resepku.size
    }
}