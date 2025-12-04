package barant.curso.simpsonsapi.feature.character.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import barant.curso.simpsonsapi.databinding.ItemCharacterListBinding
import barant.curso.simpsonsapi.feature.character.domain.Character

class CharacterListItemAdapter(
    private val onItemClick: (Character) -> Unit
) : PagingDataAdapter<Character, CharacterListViewHolder>(CharacterListDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding = ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItemClick) }
    }
}