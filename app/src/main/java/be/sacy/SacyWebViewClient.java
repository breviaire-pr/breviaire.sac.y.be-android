package be.sacy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.webkit.WebViewAssetLoader;

public class SacyWebViewClient extends WebViewClient {

    WebViewAssetLoader mAssetLoader;
    String mDomain;

    public SacyWebViewClient(Activity activity, String domain) {
        mDomain = domain;
        mAssetLoader = new WebViewAssetLoader.Builder()
                .setDomain(mDomain)
                .addPathHandler("/", new WebViewAssetLoader.AssetsPathHandler(activity))
                .build();
    }


    private boolean matchExtUrl(String url) {
        return (url.startsWith("http") && !url.startsWith("https://" + mDomain + "/"))
                || url.startsWith("mailto:");
    }

    @Override
    @SuppressWarnings("deprecation") // for API < 24
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (matchExtUrl(url)) {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    @RequiresApi(24)
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (matchExtUrl(request.getUrl().toString())) {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, request.getUrl()));
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    @RequiresApi(21)
    public WebResourceResponse shouldInterceptRequest(WebView view,
                                                      WebResourceRequest request) {
        return mAssetLoader.shouldInterceptRequest(request.getUrl());
    }

    @Override
    @SuppressWarnings("deprecation") // for API < 21
    public WebResourceResponse shouldInterceptRequest(WebView view,
                                                      String url) {
        return mAssetLoader.shouldInterceptRequest(Uri.parse(url));
    }
}
