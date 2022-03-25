package com.example.browser;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    WebView web;
    EditText searchBar;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView) findViewById(R.id.webView);
        searchBar = (EditText) findViewById(R.id.editText);

        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);


        web.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                searchBar.setText(web.getUrl());
                return false;
            }
        });

        searchBar.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() != KeyEvent.ACTION_DOWN)
                return false;
            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                url = "http://" + searchBar.getText().toString();
                if (url.equals("http://index.html"))
                {
                    web.loadUrl("file:///android_asset/index.html");
                }
                else
                {
                    web.loadUrl(url);
                    searchBar.setText(web.getUrl());
                    return true;
                }
            }
            return false;
        });
    }


    public void refresh(View v)
    {
        web.loadUrl(web.getUrl());
    }


    public void shoutout(View v)
    {
        web.evaluateJavascript("javascript:shoutOut()", null);
    }


    public void initialize(View v)
    {
        web.evaluateJavascript("javascript:initialize()", null);
    }
}