package com.example.practica_android_avanzado.ui.main.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_android_avanzado.R
import com.example.practica_android_avanzado.databinding.CardHeroeBinding
import com.example.practica_android_avanzado.model.Heroe


interface HeroeClicked {
    fun clicked(pos: Int)
}

class FragmentTableAdapter(
    var listHeroes: List<Heroe>,
    private val heroeClicked: HeroeClicked
): RecyclerView.Adapter<FragmentTableAdapter.MainActivityViewHolder>() {

    class MainActivityViewHolder(private var item: CardHeroeBinding) : RecyclerView.ViewHolder(item.root) {

        fun showHeroe(heroe: Heroe) {
            item.heroeName.text = heroe.name
//            Picasso.get().load(heroe.photo).placeholder(R.drawable.baseline_person_24).into(item.imageView);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val binding = CardHeroeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainActivityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.showHeroe(listHeroes[position])

        holder.itemView.setOnClickListener {
            heroeClicked.clicked(position)
        }
    }


}