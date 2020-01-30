package com.example.testandroidwebview

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "WebView"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview.loadUrl("https://abc.xyz/investor/")

        webview.webViewClient = MyClient()
        webview.webChromeClient = WebChromeClient()
        webview.setDownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
            Log.d(LOG_TAG, "onDownloadStart")
        }

    }

    class MyClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            Log.d(LOG_TAG, "shouldOverrideUrlLoading")
            return false
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            Log.d(LOG_TAG, "shouldOverrideUrlLoading WRR")
            return shouldOverrideUrlLoading(view, request?.url.toString())
        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            Log.d(LOG_TAG, "onReceivedError")
            Toast.makeText(view?.context, "onReceivedError Called", Toast.LENGTH_LONG).show()
            super.onReceivedError(view, errorCode, description, failingUrl)
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            Log.d(LOG_TAG, "onReceivedError WRR")
            super.onReceivedError(view, request, error)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.d(LOG_TAG, "onPageFinished")
            super.onPageFinished(view, url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Log.d(LOG_TAG, "onPageStarted")
            super.onPageStarted(view, url, favicon)
        }

        override fun onLoadResource(view: WebView?, url: String?) {
            Log.d(LOG_TAG, "onLoadResource")
            super.onLoadResource(view, url)
        }
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
