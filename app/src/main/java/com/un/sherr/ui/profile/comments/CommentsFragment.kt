package com.un.sherr.ui.profile.comments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.models.Comment
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.profile.vm.ProfileViewModel
import com.un.sherr.ui.profile.adapters.CommentsAdapter
import kotlinx.android.synthetic.main.fragment_comments.*
import javax.inject.Inject

class CommentsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var adapter: CommentsAdapter
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_comments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProfileViewModel::class.java)

        adapter = CommentsAdapter()
        rvComments.adapter = adapter

        adapter.addItems(mutableListOf(Comment(""), Comment(""), Comment(""), Comment(""), Comment(""), Comment("")))

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }


}
