package edu.uic.swethag.cs478.citydetails;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class OrlandoSiteFragment extends Fragment {
    private static final String TAG = "SiteFragment";
    private WebView mWebView;
    private int mCurrIdx = -1;
    private int mLinksArrayLen;
    private ListViewModel mModel;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the site at position newIndex
    void showSiteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mLinksArrayLen)
            return;
        mCurrIdx = newIndex;
        mWebView.loadUrl(OrlandoActivity.mLinksArray[mCurrIdx]);
    }

    @Override
    public void onAttach(@NonNull Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        // Inflate the layout defined in quote_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        View view = inflater.inflate(R.layout.web_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
        super.onViewCreated(view, savedInstanceState);

        mModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        // retains last site shown on config change
        mModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            if (item == mCurrIdx || item < 0 || item >= mLinksArrayLen)
                return;

            // Otherwise, make sure site fragment is visible

            // Update UI
            mCurrIdx = item;
            mWebView.loadUrl(OrlandoActivity.mLinksArray[mCurrIdx]);
        });
        mWebView = (WebView) getActivity().findViewById(R.id.webView);
        mLinksArrayLen = OrlandoActivity.mLinksArray.length;
        showSiteAtIndex(mCurrIdx);
    }
}