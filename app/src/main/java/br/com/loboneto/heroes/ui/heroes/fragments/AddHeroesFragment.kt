package br.com.loboneto.heroes.ui.heroes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.data.database.HeroEntity
import br.com.loboneto.heroes.databinding.FragmentAddHeroBinding
import br.com.loboneto.heroes.domain.RequestState
import br.com.loboneto.heroes.ui.heroes.HeroesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddHeroesFragment : Fragment(R.layout.fragment_add_hero), View.OnClickListener {

    private val binding by lazy {
        FragmentAddHeroBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<HeroesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

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

        val hero = HeroEntity(name, power)
        save(hero)
    }

    private fun save(hero: HeroEntity) {
        viewModel.addHero(hero).observe(viewLifecycleOwner) { roomState ->
            when (roomState) {
                is RequestState.Loading -> {
                    showProgress()
                }
                is RequestState.Success -> {
                    findNavController().popBackStack()
                    showMessage("Herói salvo com sucesso.")
                }
                is RequestState.Failure -> {
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
