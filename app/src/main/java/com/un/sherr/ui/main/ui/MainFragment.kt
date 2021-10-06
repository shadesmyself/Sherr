package com.un.sherr.ui.main.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.custom.GridSpacingItemDecoration
import com.un.sherr.databinding.FragmentMainPageBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.models.CategoryResponse
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.adapters.CategoriesAdapter
import com.un.sherr.ui.main.adapters.GoodsAdapter
import com.un.sherr.utils.MapManagement
import javax.inject.Inject
import com.un.sherr.models.CategoriesType.*
import com.un.sherr.ui.main.vm.MainViewModel

class MainFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var mapManagement: MapManagement
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var mainAdapter: GoodsAdapter
    private lateinit var adapter: CategoriesAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelProviderFactory
        ).get(MainViewModel::class.java)
        binding.mainToolBar.onIconClick(this)
        binding.searchOrders.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        viewModel.loadOrders()
        initObservables()
        setupGoodsRecycler()
        setupMainCategoriesRecycler()
    }

    private fun initObservables() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(activity, "error, ${error}", Toast.LENGTH_LONG).show()
        })
        viewModel.progressDialog.observe(viewLifecycleOwner, Observer {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.ordersData.observe(viewLifecycleOwner, Observer {
            mainAdapter.addItems(it.toMutableList())
        })
    }

//        if (mapManagement.isServicesOK(requireActivity()))
//            if (Utils.checkMapPermission(requireContext()))
//                if (mapManagement.isMapsEnabled(requireActivity()))
//                    findNavController().navigate(R.id.action_mainPageFragment_to_detailOfferFragment, bundleOf(ID to id))

//    fun updateSearch(s: String) {
//        if (s.isEmpty()) {
//            viewModel.api.getAllOrders()
//        } else {
//            viewModel
//        }
//    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(true)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.mainToolbarImage -> {
                findNavController().navigate(R.id.action_mainPageFragment_to_mainFilterFragment)
            }
        }
    }

    private fun setupGoodsRecycler() {
        mainAdapter = GoodsAdapter()
        binding.rvGoods.addItemDecoration(GridSpacingItemDecoration())
        binding.rvGoods.adapter = mainAdapter
        mainAdapter.onItemClick = { position ->
            mainAdapter.list[position].id?.let {
                findNavController().navigate(
                    MainFragmentDirections.actionMainPageFragmentToDetailOfferFragment(
                        it
                    )
                )
            }
        }
    }

    private fun setupMainCategoriesRecycler() {
        adapter = CategoriesAdapter()
        binding.rvCategories.adapter = adapter
        adapter.addItems(
            mutableListOf(
                CategoryResponse(name = "Жильё", icon = null, categoryType = HOUSE).apply {
                    id = 3
                },
                CategoryResponse(
                    name = "Транспорт",
                    icon = null,
                    categoryType = TRANSPORT
                ).apply { id = 1 },
                CategoryResponse(
                    name = "Техника",
                    icon = null,
                    categoryType = TECHNIQUE
                ).apply { id = 2 },
                CategoryResponse(name = "Все категории", icon = null, categoryType = ALL_CATEGORIES)
            )
        )
        adapter.onItemClick = { position ->
            val item = adapter.list[position]
            when (item.categoryType) {
                ALL_CATEGORIES -> findNavController().navigate(R.id.rubricFragment)
//                else -> findNavController().navigate(MainFragmentDirections.toSubcategoryFragment(item))
            }
        }
    }
}
