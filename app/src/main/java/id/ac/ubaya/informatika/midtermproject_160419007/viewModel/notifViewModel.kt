package id.ac.ubaya.informatika.midtermproject_160419007.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.midtermproject_160419007.model.notif

class notifViewModel :ViewModel(){
    val notifLD = MutableLiveData<List<notif>>()

    fun refresh() {
        val notif1 =notif("Natalie started following you","","3 hours ago")
        val notif2 =notif("joan,naki,fiki,vika, and 13 more started following you","","4 hours ago")
        val notif3 =notif("Do you know Chef renata? she is have some good recipe for you","A good delicious chicken recipes make with pleasure...","6 hours ago")
        val notif4 =notif("You start following chef renata","","6 hours ago")
        val notif5 =notif("Oh no! some food ingridients has bad impact","Did you know the impact after cosuming oil to much...","9 hours ago")

        notifLD.value = arrayListOf<notif>(notif1, notif2, notif3,notif4,notif5)
    }
}