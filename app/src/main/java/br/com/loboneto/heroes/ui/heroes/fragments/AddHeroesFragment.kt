package br.com.loboneto.heroes.ui.heroes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.data.domain.Hero
import br.com.loboneto.heroes.data.dao.RoomState
import br.com.loboneto.heroes.databinding.FragmentAddHeroBinding
import br.com.loboneto.heroes.ui.heroes.HeroesViewModel

class AddHeroesFragment : Fragment(R.layout.fragment_add_hero), View.OnClickListener {

    private lateinit var binding: FragmentAddHeroBinding

    private val viewModel: HeroesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HeroesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener(this)
    }

    private fun checkFields() {
        val name = binding.textFieldHeroName.text.toString()
        val power = binding.textFieldHeroPower.text.toString()
        if (name.isEmpty()) {
            binding.textLayoutHeroName.error = "Campo obrigatório"
            return
        }

        if (power.isEmpty()) {
            binding.textLayoutHeroPower.error = "Campo obrigatório"
            return
        }

        val hero = Hero(name, power)
        save(hero)
    }

    private fun save(hero: Hero) {
        viewModel.save(hero).observe(viewLifecycleOwner) { roomState ->
            when (roomState) {
                is RoomState.Loading -> {
                    showProgress()
                }
                is RoomState.Success -> {
                    findNavController().popBackStack()
                    showMessage("Herói salvo com sucesso.")
                }
                is RoomState.Failure -> {
                    hideProgress()
                    showMessage("Falha ao salvar herói, tente novamente.")
                }
            }
        }
    }

    private fun showProgress() {
        binding.progressSaveHero.visibility = View.GONE
    }

    private fun hideProgress() {
        binding.progressSaveHero.visibility = View.VISIBLE
    }

    private fun showMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonSave -> checkFields()
        }
    }
}
