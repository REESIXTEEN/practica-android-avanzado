package com.example.practica_android_avanzado.ui.main.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_android_avanzado.R
import com.example.practica_android_avanzado.databinding.CardHeroeBinding
import com.example.practica_android_avanzado.ui.model.Hero
import com.squareup.picasso.Picasso


interface HeroClicked {
    fun clicked(id: String)
}

class FragmentTableAdapter(
    var listHeroes: List<Hero>,
    private val heroClicked: HeroClicked
): RecyclerView.Adapter<FragmentTableAdapter.MainActivityViewHolder>() {

    class MainActivityViewHolder(private var item: CardHeroeBinding) : RecyclerView.ViewHolder(item.root) {

        fun showHeroe(hero: Hero) {
            item.heroeName.text = hero.name
            Picasso.get().load(hero.photo).placeholder(R.drawable.baseline_person_24).into(item.imageView);

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
            heroClicked.clicked(listHeroes[position].id)
        }
    }


}