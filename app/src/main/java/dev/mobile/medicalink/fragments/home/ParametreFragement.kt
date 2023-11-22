package dev.mobile.medicalink.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import dev.mobile.medicalink.R
import dev.mobile.medicalink.db.local.AppDatabase


class ParametreFragement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_parametre_fragement, container, false)

        //Get elements from view
        val btnTsarBomba : Button = view.findViewById(R.id.btnTsraBomba)

        //Set click listener
        btnTsarBomba.setOnClickListener {
            //Create database connexion, use `userDatabaseInterface` to access to the database
            val db = AppDatabase.getInstance(view.context.applicationContext)
            //Call tsarBomba function to clear all tables :)
            //ça va faire boum badaboum
            db.tsarBomba()
        }

        return view
    }
}