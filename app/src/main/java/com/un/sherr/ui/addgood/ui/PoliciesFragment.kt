package com.un.sherr.ui.addgood.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentPoliciesBinding

class PoliciesFragment : BaseFragment() {
private lateinit var binding: FragmentPoliciesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentPoliciesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.setSupportZoom(true)
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                binding.progDailog.visibility = View.VISIBLE
                url?.let{
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progDailog.visibility = View.GONE
            }
        }


        binding.webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=http://guideness.org/offerts/offert_en.pdf")
    }

    override fun onPause() {
        super.onPause()
        if (binding.webView.canGoBack())
            binding.webView.goBack()
    }

}
