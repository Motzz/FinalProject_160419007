package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.databinding.ResepkuCardLayoutBinding
import id.ac.ubaya.informatika.midtermproject_160419007.model.UserResep

class ResepKuListAdapter(val userResepList:ArrayList<UserResep>,val adapterOnClick:(Any)-> Unit):RecyclerView.Adapter<ResepKuListAdapter.ResepKuViewHolder>(),UserResepEdit,UserDeleteResep {

    class ResepKuViewHolder(val view: ResepkuCardLayoutBinding):RecyclerView.ViewHolder(view.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepKuViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<ResepkuCardLayoutBinding>(inflater,R.layout.resepku_card_layout,parent,false)
        return ResepKuViewHolder(view)

    }

    override fun onBindViewHolder(holder: ResepKuViewHolder, position: Int) {
        holder.view.userResep=userResepList[position]
        holder.view.listener=this
        holder.view.deleteListener=this

    }
    override fun getItemCount(): Int {
        return userResepList.size
    }
    //method untuk refresh data supaya ke update
    fun updateResepKu(newUserResep:List<UserResep>)
    {
        userResepList.clear()
        userResepList.addAll(newUserResep)
        notifyDataSetChanged()
    }

    override fun onUserResepEdit(v: View) {
        val action = FragmentResepkuDirections.actionItemResepkuToFragmentEditResepku(v.tag.toString())//kirim data id
        Navigation.findNavController(v).navigate(action)
    }

    override fun onUserDeleteResep(v: View, obj: UserResep) {
        adapterOnClick(obj)
    }

}