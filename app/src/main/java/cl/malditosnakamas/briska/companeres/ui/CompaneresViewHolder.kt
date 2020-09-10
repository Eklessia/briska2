package cl.malditosnakamas.briska.companeres.ui

import androidx.recyclerview.widget.RecyclerView
import cl.malditosnakamas.briska.companeres.domain.Companere
import cl.malditosnakamas.briska.databinding.ItemCompaneresBinding

class CompaneresViewHolder(
    private val binding: ItemCompaneresBinding,
    private val onItemClickCompanere: OnItemClickCompanere
    ) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(companere: Companere){
        binding.apply {
            tvName.text = companere.name
            //Todo pendiente el resto de las propiedades
        }
        onItemClickCompanere.onItemClickCompanere(companere)
    }
}