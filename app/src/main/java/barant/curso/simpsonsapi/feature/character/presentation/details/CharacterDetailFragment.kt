package barant.curso.simpsonsapi.feature.character.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.core.presentation.ext.fromUrl
import barant.curso.simpsonsapi.databinding.FragmentItemCharacterDetailsBinding
import barant.curso.simpsonsapi.feature.character.presentation.details.adapter.CharacterDetailPhrasesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CharacterDetailFragment : Fragment(R.layout.fragment_item_character_details) {
    val viewModel: CharacterDetailViewModel by viewModel()

    private var _binding: FragmentItemCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadCharacter(args.id)
        val action = CharacterDetailFragmentDirections.backToList()
        binding.exitButton.setOnClickListener {
            findNavController().navigate(action)
        }
    }

    fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner, { uiState ->
            binding.apply {
                if (uiState.isLoading) {
                    nameCharacter.text = getString(R.string.loading)
                } else if (uiState.error != null) {
                    nameCharacter.text =
                        getString(R.string.error).plus(" ").plus(uiState.error.message)
                } else if (uiState.data != null) {
                    nameCharacter.text = uiState.data.name
                    ageCharacter.text = uiState.data.age?.let { age ->
                        "$age ${binding.root.context.getString(R.string.ageSuffix)}"
                    } ?: "-------"
                    genderCharacter.text = uiState.data.gender
                    occupationCharacter.text = uiState.data.occupation
                    birthdateCharacter.text = uiState.data.birthdate?.let {
                        LocalDate.parse(it).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    } ?: "-------"
                    statusCharacter.text = uiState.data.status
                    imgCharacter.fromUrl(uiState.data.img)
                    setupRecyclerList(uiState.data.phrase)
                }
            }
        })
    }

    fun setupRecyclerList(list: List<String>?) {
        if (list != null) {
            val adapter = CharacterDetailPhrasesAdapter(list)
            binding.listPhrasesCharacterContainer.apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}