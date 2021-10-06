package com.un.sherr.ui.offer.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentCreateCommentBinding
import com.un.sherr.ui.MainActivity


class CreateCommentFragment : BaseFragment() {
    companion object {
        const val ID: String = "ID"
    }
    private lateinit var binding: FragmentCreateCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View{
        binding = FragmentCreateCommentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ok.setOnClickListener { requireActivity().onBackPressed() }

        val imm: InputMethodManager? = getSystemService(requireActivity(), InputMethodManager::class.java)
        imm!!.showSoftInput(binding.etMessage, InputMethodManager.SHOW_IMPLICIT)

        binding.rangeSeekbar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onRangeChanged(view: RangeSeekBar?, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                binding.communicationRate.text = leftValue.toInt().toString() + "/10"
            }
        })

        binding.rangeSeekbarDeadlines.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onRangeChanged(view: RangeSeekBar?, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                binding.deadlinesRate.text = leftValue.toInt().toString() + "/10"
            }
        })
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
