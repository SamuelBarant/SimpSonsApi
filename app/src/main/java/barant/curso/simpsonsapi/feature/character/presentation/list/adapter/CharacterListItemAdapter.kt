package barant.curso.simpsonsapi.feature.character.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import barant.curso.simpsonsapi.databinding.ItemCharacterListBinding
import barant.curso.simpsonsapi.feature.character.domain.Character

class CharacterListItemAdapter(
    private val list: List<Character>,
    private val onItemClick: (Character) -> Unit
) :
    RecyclerView.Adapter<CharacterListItemAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character, onItemClick: (Character) -> Unit) {
            binding.nameCharacter.text = character.name
            binding.occupationCharacter.text = character.occupation
            binding.ageCharacter.text = character.age.toString().plus(" ").plus(
                binding.root.context.getString(barant.curso.simpsonsapi.R.string.ageSuffix)
            )
            binding.genderCharacter.text = character.gender
            binding.phraseCharacter.text = character.phrase[0]
            binding.root.setOnClickListener {
                onItemClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onItemClick)
    }

    override fun getItemCount(): Int = list.size
}