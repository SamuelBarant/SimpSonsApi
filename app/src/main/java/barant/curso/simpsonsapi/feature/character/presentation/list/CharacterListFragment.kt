package barant.curso.simpsonsapi.feature.character.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.databinding.FragmentListCharacterBinding
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.presentation.list.adapter.CharacterAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment(R.layout.fragment_list_character){

    val viewModel: CharacterViewModel by viewModel()
    private var _binding: FragmentListCharacterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListCharacterBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadCharacters()
    }

    fun setupObserver(){
        val observer = Observer<CharacterViewModel.UiState>{ uiState ->
            if (uiState.isLoading){
                binding.subtitleList.text = getString(R.string.loading)
            } else if (uiState.error != null){
                binding.subtitleList.text = getString(R.string.error).plus(" ").plus(uiState.error.message)
            } else if (uiState.data != null){
                setupRecyclerList(uiState.data)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    fun onCharacterClicked(character: Character){

    }
    fun setupRecyclerList(list: List<Character>){
        val adapter = CharacterAdapter(list){
            viewModel.onCharacterClicked(it)
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment()
            findNavController().navigate(action)
        }
        binding.characterContainer.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}