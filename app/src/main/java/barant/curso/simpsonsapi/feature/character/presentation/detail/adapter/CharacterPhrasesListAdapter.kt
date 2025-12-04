package barant.curso.simpsonsapi.feature.character.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import barant.curso.simpsonsapi.databinding.ItemCharacterPhrasesBinding

class CharacterPhrasesListAdapter(private val list: List<String>) :
    RecyclerView.Adapter<CharacterPhrasesListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterPhrasesListViewHolder {
        val binding = ItemCharacterPhrasesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterPhrasesListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CharacterPhrasesListViewHolder,
        position: Int
    ) {
        holder.binding.textView.text = list[position]
    }

    override fun getItemCount(): Int = list.size

}