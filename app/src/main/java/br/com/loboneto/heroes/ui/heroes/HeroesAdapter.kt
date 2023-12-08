package br.com.loboneto.heroes.ui.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.loboneto.heroes.BR
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.data.database.HeroEntity
import br.com.loboneto.heroes.databinding.AdapterHeroBinding
import br.com.loboneto.heroes.ui.heroes.bottomsheet.DeleteHeroBottomSheet
import br.com.loboneto.heroes.ui.heroes.fragments.HeroesFragment
import java.util.*

class HeroesAdapter(
    private val fragment: HeroesFragment
) : RecyclerView.Adapter<HeroesAdapter.ViewHolder>(), Filterable {

    private var heroes = ArrayList<HeroEntity>()
    private var allHeroes = ArrayList<HeroEntity>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroes[position]
        holder.bindView(hero, fragment)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_hero,
            parent,
            false
        )
    )

    class ViewHolder(private val binding: AdapterHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(hero: HeroEntity, fragment: HeroesFragment) {
            binding.run {
                setVariable(BR.hero, hero)
                executePendingBindings()
                root.setOnClickListener {
                    val bottomSheet = DeleteHeroBottomSheet(fragment, hero)
                    bottomSheet.show(fragment.parentFragmentManager, "DELETE")
                }
            }
        }
    }

    override fun getItemCount(): Int = heroes.size

    fun setHeroes(heroes: List<HeroEntity>) {
        this.heroes.clear()
        this.heroes.addAll(heroes)
        this.allHeroes.addAll(heroes)
        notifyDataSetChanged()
    }

    fun removeHero(hero: HeroEntity) {
        this.heroes.remove(hero)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter = filteredList

    private val filteredList: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filteredHeroes = ArrayList<HeroEntity>()

            if (constraint.isNullOrEmpty()) {
                filteredHeroes.addAll(allHeroes)
            } else {
                val string = constraint.toString().lowercase(Locale.getDefault())
                allHeroes.forEach { hero ->
                    val newTitle = hero.name.lowercase(Locale.getDefault())
                    if (newTitle.contains(string)) {
                        filteredHeroes.add(hero)
                    } else {
                        filteredHeroes.remove(hero)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredHeroes
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            try {
                heroes.clear()
                heroes.addAll(results.values as ArrayList<HeroEntity>)
                notifyDataSetChanged()
            } catch (e: Exception) {
                notifyDataSetChanged()
            }
        }
    }
}
