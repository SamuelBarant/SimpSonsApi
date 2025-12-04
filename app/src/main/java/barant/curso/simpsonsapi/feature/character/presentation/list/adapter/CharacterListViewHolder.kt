package barant.curso.simpsonsapi.feature.character.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.core.presentation.ext.fromUrl
import barant.curso.simpsonsapi.databinding.ItemCharacterListBinding
import barant.curso.simpsonsapi.feature.character.domain.Character

class CharacterListViewHolder(private val binding: ItemCharacterListBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Character?, onItemClick: (Character)-> Unit){
        if (item == null){
            binding.apply {
                nameCharacter.text = "Loading..."
                occupationCharacter.text = ""
                ageCharacter.text = ""
                genderCharacter.text = ""
                phraseCharacter.text = ""
                imgCharacter.setImageResource(R.drawable.example)
                return
            }
        }
        binding.apply {
            nameCharacter.text = item.name
            occupationCharacter.text = item.occupation
            ageCharacter.text = item.age?.let { age ->
                "$age ${binding.root.context.getString(R.string.ageSuffix)}"
            } ?: ""
            genderCharacter.text = item.gender
            phraseCharacter.text = item.phrase?.getOrNull(0) ?: ""
            imgCharacter.fromUrl(item.img)
            root.setOnClickListener { onItemClick(item) }
        }
    }
}