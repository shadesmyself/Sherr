package com.un.sherr.ui.main.filters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.FilterViewModel
import com.un.sherr.ui.main.adapters.SubcategoriesAdapter
import kotlinx.android.synthetic.main.fragment_rubric.*
import kotlinx.android.synthetic.main.fragment_rubric.mainToolBar
import kotlinx.android.synthetic.main.fragment_rubric.progress
import kotlinx.android.synthetic.main.fragment_rubric.rvRubric
import kotlinx.android.synthetic.main.fragment_subcategory.*
import javax.inject.Inject

class SubcategoryFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: FilterViewModel

    private lateinit var adapter: SubcategoriesAdapter

    private val args: SubcategoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_subcategory, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(FilterViewModel::class.java)
        args.category.id?.let {
            viewModel.loadSubcategories(it)
        }
        setupAdapter()
        initObservables()
        mainToolBar.setTitle(args.category.name ?: "Subcategory")
        header.text = args.category.name ?: "Subcategory"
        subheader.text = getString(R.string.all_in_category, args.category.name ?: "Subcategory")
    }

    private fun initObservables() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(activity, "error, $error", Toast.LENGTH_LONG).show()
        }
        viewModel.progressDialog.observe(viewLifecycleOwner) {
            progress.isVisible = it
        }
        viewModel.subcategoriesLD.observe(viewLifecycleOwner) {
            adapter.addItems(it.toMutableList())
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

    private fun setupAdapter() {
        adapter = SubcategoriesAdapter()
        rvRubric.adapter = adapter
    }
}
