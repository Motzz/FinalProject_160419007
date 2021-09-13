package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Resep
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
        }

    }

    override fun getItemCount(): Int {
        return resepList.size
    }
}