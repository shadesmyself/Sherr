package com.un.sherr.ui.offer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_create_comment.*


class CreateCommentFragment : BaseFragment() {
    companion object {
        const val ID: String = "ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_create_comment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ok.setOnClickListener { requireActivity().onBackPressed() }

        val imm: InputMethodManager? = getSystemService(requireActivity(), InputMethodManager::class.java)
        imm!!.showSoftInput(etMessage, InputMethodManager.SHOW_IMPLICIT)

        rangeSeekbar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onRangeChanged(view: RangeSeekBar?, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                communication_rate.text = leftValue.toInt().toString() + "/10"
            }
        })

        rangeSeekbarDeadlines.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}
            override fun onRangeChanged(view: RangeSeekBar?, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                deadlines_rate.text = leftValue.toInt().toString() + "/10"
            }
        })
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
