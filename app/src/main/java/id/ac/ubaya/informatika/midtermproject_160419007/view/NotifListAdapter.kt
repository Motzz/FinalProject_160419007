package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.model.notif
import kotlinx.android.synthetic.main.notif_layout.view.*



class NotifListAdapter(val notif:ArrayList<notif>):RecyclerView.Adapter<NotifListAdapter.NotifViewHolder>() {
    class NotifViewHolder(val view: View): RecyclerView.ViewHolder(view)

    //method untuk refresh data supaya ke update
    fun updateNotifList(newStudentList:List<notif>)
    {
        notif.clear()
        notif.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.notif_layout,parent,false)
        return NotifViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotifViewHolder, position: Int) {
        with(holder.view)
        {
            txtNotif.text=notif[position].notifku
            Global.notifku=notif[0].notifku.toString()

            btnCheck.isVisible = notif[position].pesan != ""
            btnCheck.setOnClickListener {
                val action = FragmentNotifikasiDirections.actionItemNotifikasiToNewsBottomFragment2()
                Navigation.findNavController(it).navigate(action)
            }
            txtMessage.text=notif[position].pesan
            txtTime.text=notif[position].jam
        }
    }

    override fun getItemCount(): Int {
        return notif.size
    }
}