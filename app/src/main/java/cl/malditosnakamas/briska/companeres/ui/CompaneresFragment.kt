package cl.malditosnakamas.briska.companeres.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.malditosnakamas.briska.R
import cl.malditosnakamas.briska.companeres.data.remote.RemoteCompaneresRepository
import cl.malditosnakamas.briska.companeres.domain.Companere
import cl.malditosnakamas.briska.companeres.domain.ObtainCompaneresUseCase
import cl.malditosnakamas.briska.companeres.presentation.CompaneresUiState
import cl.malditosnakamas.briska.companeres.presentation.CompaneresViewModel
import cl.malditosnakamas.briska.companeres.presentation.CompaneresViewModelFactory
import cl.malditosnakamas.briska.databinding.FragmentCompaneresBinding
import cl.malditosnakamas.briska.network.RetrofitHandler

class CompaneresFragment : Fragment(R.layout.fragment_companeres), OnItemClickCompanere {

    private lateinit var viewModel: CompaneresViewModel
    private lateinit var viewModelFactory: CompaneresViewModelFactory
    private lateinit var binding: FragmentCompaneresBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentCompaneresBinding.bind(view)
        setupRecyclerView()
        setupLiveData()
    }

    private fun setupDependencies() {
        viewModelFactory = CompaneresViewModelFactory(
            ObtainCompaneresUseCase(
                RemoteCompaneresRepository(
                    RetrofitHandler.getCompanereApi()
                )
            )
        )
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(CompaneresViewModel::class.java)
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvCompaneres.setHasFixedSize(true)
            rvCompaneres.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer {
                it?.let { safeState -> handleState(safeState) }
            }
        )
        viewModel.obtainCompaneres()
    }

    private fun handleState(safeState: CompaneresUiState) {
        when (safeState) {
            is CompaneresUiState.Loading -> showLoading()
            is CompaneresUiState.ServerError -> showErrorServerMessage()
            is CompaneresUiState.Success -> loadResult(safeState.result)
            is CompaneresUiState.EmptyList -> showEmptyList()
        }
    }

    private fun showEmptyList() {
        Toast.makeText(context, "Empty List", Toast.LENGTH_SHORT).show()
    }

    private fun loadResult(companeres: List<Companere>?) {
        companeres?.let { safeCompaneres ->
            binding.rvCompaneres.adapter = CompaneresAdapter(safeCompaneres, this)
        }
    }

    private fun showErrorServerMessage() {
        Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        Toast.makeText(context, "Cargando", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickCompanere(companere: Companere) {

    }
}