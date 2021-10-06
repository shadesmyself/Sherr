package com.un.sherr.ui.main.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentMainFilterBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.vm.MainViewModel
import com.un.sherr.utils.Constants
import com.un.sherr.utils.MapManagement
import com.un.sherr.utils.Utils
import javax.inject.Inject

class MainFilterFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var mapManagement: MapManagement

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentMainFilterBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding.vgRubric.setOnClickListener(this)
        binding.vgLocation.setOnClickListener(this)
        binding.vgDistance.setOnClickListener(this)
        initSeekbar()
    }

    private fun initSeekbar() {
        binding.rangeSeekbar.setRange(0f, 100f)
        binding.rangeSeekbar.setProgress(
            Constants.PRICE_FROM,
            Constants.PRICE_TO
        )
        binding.rangeSeekbar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
            }

            override fun onRangeChanged(
                view: RangeSeekBar?,
                leftValue: Float,
                rightValue: Float,
                isFromUser: Boolean
            ) {
                binding.tvPrice1.text = leftValue.toInt().toString()
                binding.tvPrice2.text = rightValue.toInt().toString()

                /*viewModel.priceFrom.value = leftValue.toInt()
                viewModel.priceTo.value = rightValue.toInt()*/
            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.vgRubric -> {
                findNavController().navigate(R.id.action_mainFilterFragment_to_rubricFragment)
            }
            R.id.vgLocation -> {
                findNavController().navigate(R.id.action_mainFilterFragment_to_selectLocationFragment)
            }
            R.id.vgDistance -> {
                if (mapManagement.isServicesOK(requireActivity()))
                    if (Utils.checkMapPermission(requireContext()))
                        if (mapManagement.isMapsEnabled(requireActivity()))
                            findNavController().navigate(R.id.action_mainFilterFragment_to_distanceFragment)
            }
        }
    }

}
