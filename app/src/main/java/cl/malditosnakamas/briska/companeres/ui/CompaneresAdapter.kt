package cl.malditosnakamas.briska.companeres.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.malditosnakamas.briska.companeres.domain.Companere
import cl.malditosnakamas.briska.databinding.ItemCompaneresBinding

class CompaneresAdapter(
    private val companeres : List<Companere>,
    private val itemClickCompanere: OnItemClickCompanere
) : RecyclerView.Adapter<CompaneresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaneresViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemCompaneresBinding.inflate(inflate, parent, false)
        return CompaneresViewHolder(binding, itemClickCompanere)
    }

    override fun onBindViewHolder(holder: CompaneresViewHolder, position: Int) {
        holder.bind(companeres[position])
    }

    override fun getItemCount() = companeres.size
}