package barant.curso.simpsonsapi.feature.character.presentation.list.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.R
import barant.curso.simpsonsapi.feature.character.domain.Character
import androidx.recyclerview.widget.RecyclerView
import barant.curso.simpsonsapi.databinding.ItemCharacterListBinding

class CharacterAdapter(private val list: List<Character>, private val onClick: (Character) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemCharacterListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character, onClick: (Character) -> Unit){
            binding.nameCharacter.text = character.name
            binding.occupationCharacter.text = character.occupation
            binding.ageCharacter.text = "( " + character.age.toString() + " )"
            binding.genderCharacter.text = character.gender
            binding.phraseCharacter.text = character.phrase
            binding.root.setOnClickListener {
                onClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size
}