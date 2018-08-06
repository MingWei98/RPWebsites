package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wvPage = findViewById(R.id.webViewPage);
        wvPage.setWebViewClient(new WebViewClient());

        Intent intentReceived = getIntent();
        String webUrl = intentReceived.getStringExtra("url");
        wvPage.loadUrl(webUrl);

    }
}
