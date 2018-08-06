package com.example.a16019990.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView wvWebPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wvWebPage = findViewById(R.id.webview);

        wvWebPage.getSettings().setBuiltInZoomControls(true);
        wvWebPage.getSettings().setAllowFileAccess(false);

        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");
        Log.i("url",url);

        wvWebPage.loadUrl(url);

    }
}
