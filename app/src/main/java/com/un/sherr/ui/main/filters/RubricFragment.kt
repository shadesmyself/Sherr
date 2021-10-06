package com.un.sherr.ui.main.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentRubricBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.vm.FilterViewModel
import com.un.sherr.ui.main.adapters.RubricAdapter
import javax.inject.Inject

class RubricFragment : BaseFragment() {

    private lateinit var adapter: RubricAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: FilterViewModel
    private lateinit var binding: FragmentRubricBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentRubricBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelProviderFactory).get(FilterViewModel::class.java)
        viewModel.loadCategories()
        setupAdapter()
        initObservables()
    }

    private fun initObservables() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(activity, "error, ${error}", Toast.LENGTH_LONG).show()
        })
        viewModel.progressDialog.observe(viewLifecycleOwner, Observer {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.categoriesLD.observe(viewLifecycleOwner) {
            adapter.addItems(it.toMutableList())
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

    private fun setupAdapter() {
        adapter = RubricAdapter()
        binding.rvRubric.adapter = adapter
        adapter.onItemClick = { position ->
            adapter.list[position].let {
                findNavController().navigate(RubricFragmentDirections.toSubcategoryFragment(it))
            }
        }
    }
}
