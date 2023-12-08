package br.com.loboneto.heroes.ui.heroes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.data.database.HeroEntity
import br.com.loboneto.heroes.databinding.FragmentHeroesBinding
import br.com.loboneto.heroes.domain.RequestState
import br.com.loboneto.heroes.ui.heroes.HeroesAdapter
import br.com.loboneto.heroes.ui.heroes.HeroesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesFragment : Fragment(),
    SwipeRefreshLayout.OnRefreshListener,
    View.OnClickListener {

    private lateinit var binding: FragmentHeroesBinding

    private lateinit var searchView: SearchView

    private val adapter by lazy {
        HeroesAdapter(this)
    }

    private val viewModel by viewModels<HeroesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setListeners()
        getHeroes()
    }

    private fun setView() {
        val color = ContextCompat.getColor(requireContext(), R.color.red)
        binding.swipeRefreshHeroes.setColorSchemeColors(color)
        val divider = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding.recyclerViewHeroes.addItemDecoration(divider)
        binding.recyclerViewHeroes.adapter = adapter
        binding.recyclerViewHeroes.hasFixedSize()
    }

    private fun setListeners() {
        binding.fabAddHero.setOnClickListener(this)
        binding.recyclerViewHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(-1)) {
                    binding.fabAddHero.extend()
                } else {
                    binding.fabAddHero.shrink()
                }
            }
        })
    }

    private fun getHeroes() {
        viewModel.getHeroes().observe(viewLifecycleOwner) { state ->
            when (state) {
                is RequestState.Loading -> {
                    showProgress()
                }
                is RequestState.Success -> {
                    hideProgress()
                    binding.swipeRefreshHeroes.isEnabled = false
                    adapter.setHeroes(state.data)
                    if (state.data.isEmpty()) {
                        showMessage("Nenhum herói salvo.")
                    }
                }
                is RequestState.Failure -> {
                    hideProgress()
                    showMessage("Falha ao obter heróis, tente novamente.")
                }
            }
        }
    }

    fun deleteHero(hero: HeroEntity) {
        viewModel.deleteHero(hero).observe(viewLifecycleOwner) { state ->
            when (state) {
                is RequestState.Loading -> { }
                is RequestState.Success -> {
                    adapter.removeHero(hero)
                    showMessage("Herói removido com sucesso.")
                }
                is RequestState.Failure -> {
                    showMessage("Falha ao remover herói, tente novamente.")
                }
            }
        }
    }

    private fun showProgress() {
        binding.swipeRefreshHeroes.isRefreshing = true
    }

    private fun hideProgress() {
        binding.swipeRefreshHeroes.isRefreshing = false
    }

    private fun showMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menu_action_search)

        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onRefresh() {
        getHeroes()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fabAddHero -> findNavController().navigate(R.id.action_add_marvel_hero)
        }
    }
}
