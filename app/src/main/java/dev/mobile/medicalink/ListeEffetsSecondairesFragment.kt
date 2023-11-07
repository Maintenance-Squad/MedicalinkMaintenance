package dev.mobile.medicalink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class ListeEffetsSecondairesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var annuler: ImageView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_liste_effets_secondaires, container, false)

        annuler = view.findViewById(R.id.annulerListeEffetsSecondaires)

        val effetsSecondairesDoliprane = mutableListOf("Nausées", "Vomissements", "Maux de tête")
        val effetsSecondairesAspirine = mutableListOf("Irritation de l'estomac", "Hémorragie")
        val effetsSecondairesVitamineC = null
        val effetsSecondairesIbuprofène = mutableListOf("Maux d'estomac", "Saignement d'estomac")
        val effetsSecondairesAntibiotique = mutableListOf("Réaction allergique", "Diarrhée")
        val effetsSecondairesMédicamentX = mutableListOf("Somnolence", "Vertiges")
        val effetsSecondairesVitamineD = null
        val effetsSecondairesParacétamol = null
        val effetsSecondairesAntiInflammatoire = mutableListOf("Irritation de l'estomac", "Saignement d'estomac")
        val effetsSecondairesMédicamentY = mutableListOf("Somnolence", "Confusion")

        val lp = mutableListOf(
            Traitement("Doliprane", 4, "Jours", LocalDate.of(2023, 5, 25), 15, false, effetsSecondairesDoliprane),
            Traitement("Aspirine", 7, "Mois", LocalDate.of(2023, 6, 10), 10, false, effetsSecondairesAspirine),
            Traitement("Vitamine C", 30, "An", null, 20, false, effetsSecondairesVitamineC),
            Traitement("Ibuprofène", 5, "Jours", LocalDate.of(2023, 8, 15), 12, false, effetsSecondairesIbuprofène),
            Traitement("Antibiotique", 10, "Mois", null, 1, false, effetsSecondairesAntibiotique),
            Traitement("Médicament X", 14, "Jours", LocalDate.of(2023, 10, 5), 8, false, effetsSecondairesMédicamentX),
            Traitement("Vitamine D", 60, "An", LocalDate.of(2023, 9, 1), 25, false, effetsSecondairesVitamineD),
            Traitement("Paracétamol", 3, "Mois", null, 18, false, effetsSecondairesParacétamol),
            Traitement("Anti-inflammatoire", 7, "Jours", LocalDate.of(2023, 12, 1), 15, false, effetsSecondairesAntiInflammatoire),
            Traitement("Médicament Y", 21, "An", LocalDate.of(2024, 1, 20), 10, false, effetsSecondairesMédicamentY)
        )

        val traitementsTries = lp.sortedBy { it.expire }.toMutableList()
        println(traitementsTries.first().nomTraitement)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewListeAddManuallySearch)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ListeEffetsSecondairesAdapterR(traitementsTries)

        //Gestion espacement entre items RecyclerView
        val espacementEnDp = 22
        recyclerView.addItemDecoration(SpacingRecyclerView(espacementEnDp))


        //On retourne au fragment précédent TODO : choisir le fragment précédent (je sais pas c quoi)
        annuler.setOnClickListener {
            //On appelle le parent pour changer de fragment
            val fragTransaction = parentFragmentManager.beginTransaction()
            fragTransaction.replace(R.id.FL, MainTraitementsFragment())
            fragTransaction.addToBackStack(null)
            fragTransaction.commit()
        }

        return view
    }

}