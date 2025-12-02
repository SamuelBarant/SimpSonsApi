package barant.curso.simpsonsapi.feature.character.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import barant.curso.simpsonsapi.feature.character.domain.Character

object CharacterListDiffCallback: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}