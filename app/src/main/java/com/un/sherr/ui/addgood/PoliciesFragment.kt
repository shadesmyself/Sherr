package com.un.sherr.ui.addgood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_policies.*

class PoliciesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_policies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.webViewClient = WebViewClient()

        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.setSupportZoom(true)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                if (progDailog != null)
                progDailog.visibility = View.VISIBLE
                url?.let{
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (progDailog != null)
                progDailog.visibility = View.GONE
            }
        }


        webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=http://guideness.org/offerts/offert_en.pdf")
    }

    override fun onPause() {
        super.onPause()
        if (webView.canGoBack())
            webView.goBack()
    }

}
