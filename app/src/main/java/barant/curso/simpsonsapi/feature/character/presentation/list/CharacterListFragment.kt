package barant.curso.simpsonsapi.feature.character.presentation.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.databinding.FragmentListCharacterBinding
import barant.curso.simpsonsapi.feature.character.presentation.list.adapter.CharacterListItemAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment(R.layout.fragment_list_character) {

    private var _binding: FragmentListCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModel()
    private lateinit var adapter: CharacterListItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListCharacterBinding.bind(view)

        setupRecycler()
        observerCharacter()
    }

    private fun setupRecycler() {
        adapter = CharacterListItemAdapter { character ->
            findNavController().navigate(
                R.id.action_characterList_to_characterDetail,
                bundleOf("id" to character.id)
            )
        }

        binding.characterContainer.layoutManager = LinearLayoutManager(requireContext())
        binding.characterContainer.adapter = adapter
    }

    private fun observerCharacter() {
        lifecycleScope.launch {
            viewModel.characters.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }
}