package barant.curso.simpsonsapi.feature.character.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import barant.curso.simpsonsapi.databinding.ItemCharacterPhrasesBinding

class CharacterDetailPhrasesAdapter(private val listPhrases: List<String>) :
    RecyclerView.Adapter<CharacterDetailPhrasesAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemCharacterPhrasesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phrase: String) {
            binding.textView.text = phrase
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterPhrasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPhrases[position])
    }

    override fun getItemCount(): Int = listPhrases.size
}