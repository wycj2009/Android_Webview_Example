package com.example.android_webview_example

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainButtonLoadWebview: Button
    private lateinit var activityMainWebviewTest: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainButtonLoadWebview = findViewById(R.id.activity_main_button_load_webview)
        activityMainWebviewTest = findViewById(R.id.activity_main_webview_test)

        activityMainButtonLoadWebview.setOnClickListener {
            loadWebview() //웹뷰 로드
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //백버튼 누르면 웹뷰에서 뒤로가게 하기
        if (keyCode == KeyEvent.KEYCODE_BACK && activityMainWebviewTest.canGoBack()) {
            activityMainWebviewTest.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /** 웹뷰 로드 */
    private fun loadWebview() {
        activityMainButtonLoadWebview.visibility = View.INVISIBLE //버튼 숨기기

        //새창 열기 없이 웹뷰 내에서 열기
        activityMainWebviewTest.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        activityMainWebviewTest.settings.javaScriptEnabled = true //자바스크립트 허용
        activityMainWebviewTest.webChromeClient = WebChromeClient() //웹뷰에 크롬 사용 허용
        activityMainWebviewTest.loadUrl("https://www.google.com/") //웹뷰 실행
    }

}