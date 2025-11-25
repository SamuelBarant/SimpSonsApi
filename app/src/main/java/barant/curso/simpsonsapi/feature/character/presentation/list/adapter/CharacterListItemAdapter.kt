package barant.curso.simpsonsapi.feature.character.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.core.presentation.ext.fromUrl
import barant.curso.simpsonsapi.databinding.ItemCharacterListBinding
import barant.curso.simpsonsapi.feature.character.domain.Character

class CharacterListItemAdapter(
    private val list: MutableList<Character>,
    private val onItemClick: (Character) -> Unit
) :
    RecyclerView.Adapter<CharacterListItemAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character, onItemClick: (Character) -> Unit) {
            binding.apply {
                nameCharacter.text = character.name
                occupationCharacter.text = character.occupation
                ageCharacter.text = character.age?.let { age ->
                    "$age ${binding.root.context.getString(R.string.ageSuffix)}"
                } ?: ""
                genderCharacter.text = character.gender
                phraseCharacter.text = character.phrase?.getOrNull(0) ?: ""
                imgCharacter.fromUrl(character.img)
                root.setOnClickListener {
                    onItemClick(character)
                }
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

    fun addItems(newItems: List<Character>) {
        val start = list.size
        list.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }

    override fun getItemCount(): Int = list.size
}