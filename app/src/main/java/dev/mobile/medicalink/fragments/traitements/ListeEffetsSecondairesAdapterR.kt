package dev.mobile.medicalink.fragments.traitements

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import dev.mobile.medicalink.R
import dev.mobile.medicalink.db.local.entity.EffetSecondaire


class ListeEffetsSecondairesAdapterR(
    private val list: List<EffetSecondaire>,
    private val onItemClick: (EffetSecondaire) -> Unit
) :
    RecyclerView.Adapter<ListeEffetsSecondairesAdapterR.TraitementViewHolder>() {

    class TraitementViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nomEffetSecondaire: TextView = view.findViewById(R.id.nomEffetSecondaire)
        val messageEffetSecondaire: TextView = view.findViewById(R.id.messageEffetSecondaire)
        val imageEffetSecondaire: ImageView = view.findViewById(R.id.imageEffetSecondaire)
        val dateEffetSecondaire: TextView = view.findViewById(R.id.dateEffetSecondaire)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraitementViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_journal_effet_secondaire, parent, false)
        return TraitementViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TraitementViewHolder, position: Int) {
        val item = list.get(position)

        holder.nomEffetSecondaire.text = item.titre

        holder.messageEffetSecondaire.text = item.message

        val context = holder.view.context
        val path = BitmapDrawable(
            context.resources,
            BitmapFactory.decodeFile(context.filesDir.path + "/" + item.uuidEffetSecondaire + ".png")
        )

        if (path == null) {
            holder.imageEffetSecondaire.visibility = View.GONE
        } else {
            holder.imageEffetSecondaire.setImageDrawable(path)
        }

        holder.dateEffetSecondaire.text = item.date

        //On renvoie l'item au fragment pour qu'il récupère l'item cliqué
        holder.view.setOnClickListener {
            onItemClick.invoke(item)
        }
    }

}
