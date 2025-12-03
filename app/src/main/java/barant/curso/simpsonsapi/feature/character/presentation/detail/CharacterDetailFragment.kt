package barant.curso.simpsonsapi.feature.character.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.core.presentation.ext.fromUrl
import barant.curso.simpsonsapi.databinding.FragmentItemCharacterDetailsBinding
import barant.curso.simpsonsapi.feature.character.presentation.detail.adapter.CharacterPhrasesListAdapter
import barant.curso.simpsonsapi.feature.character.presentation.list.adapter.CharacterListItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment(R.layout.fragment_item_character_details) {

    private val viewModel: CharacterDetailViewModel by viewModel()

    private var _binding: FragmentItemCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CharacterPhrasesListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentItemCharacterDetailsBinding.bind(view)

        val id = requireArguments().getInt("id")

        adapter = CharacterPhrasesListAdapter(emptyList())

        setupRecycler()

        observeUi()
        viewModel.loadCharacter(id)
    }
    private fun setupRecycler(){
        binding.listPhrasesCharacterContainer.layoutManager = LinearLayoutManager(requireContext())
        binding.listPhrasesCharacterContainer.adapter = adapter
    }

    private fun observeUi() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            state.data?.let { character ->
                adapter = CharacterPhrasesListAdapter(character.phrase ?: emptyList())
                binding.listPhrasesCharacterContainer.adapter = adapter

                binding.apply {
                    nameCharacter.text = character.name
                    ageCharacter.text = character.age?.toString() ?: getString(R.string.unknown1)
                    occupationCharacter.text = character.occupation.ifBlank { getString(R.string.unknown2) }
                    genderCharacter.text = character.gender.ifBlank { getString(R.string.unknown2) }
                    imgCharacter.fromUrl(character.img)
                    statusCharacter.text = character.status.ifBlank { getString(R.string.unknown1) }
                    birthdateCharacter.text = character.birthdate ?: getString(R.string.unknown3)
                }
            }
        }

        binding.exitButton.setOnClickListener {
            findNavController().navigate(R.id.back_to_list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
