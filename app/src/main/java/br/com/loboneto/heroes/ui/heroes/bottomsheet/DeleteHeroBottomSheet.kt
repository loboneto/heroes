package br.com.loboneto.heroes.ui.heroes.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.data.domain.Hero
import br.com.loboneto.heroes.databinding.BottomSheetDeleteHeroBinding
import br.com.loboneto.heroes.ui.heroes.fragments.HeroesFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteHeroBottomSheet(private val fragment: HeroesFragment, private val hero: Hero) :
    BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var binding: BottomSheetDeleteHeroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDeleteHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDelete.setOnClickListener(this)
        binding.buttonCancel.setOnClickListener(this)
    }

    private fun deleteHero() {
        fragment.deleteHero(hero)
        dismiss()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonDelete -> deleteHero()
            R.id.buttonCancel -> dismiss()
        }
    }

    companion object {
        const val TAG = "SignOutBottomSheet"
    }
}