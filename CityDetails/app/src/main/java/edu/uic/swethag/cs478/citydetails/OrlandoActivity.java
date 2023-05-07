package edu.uic.swethag.cs478.citydetails;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class OrlandoActivity extends MenuActivity implements OrlandoTouristAttractionsFragment.ListSelectionListener {
    public static String[] mToursitAttractionsArray;
    public static String[] mLinksArray;

    FragmentManager mFragmentManager;
    private int temp = -1;
    private OrlandoTouristAttractionsFragment mTouristAttractionsFragment;
    private OrlandoSiteFragment mSiteFragment;

    private FrameLayout mTouristAttractionsFrameLayout, mSiteFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "OrlandoActivity";

    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ": entered onCreate()");
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Orlando");

        // Get list of tourist attractions and the links for these attractions
        mToursitAttractionsArray = getResources().getStringArray(R.array.OrlandoTouristAttractions);
        mLinksArray = getResources().getStringArray(R.array.OrlandoLinks);

        setContentView(R.layout.activity_placefragment);

        // Get references to the TouristAttractionsListFragment and SiteFragment
        mTouristAttractionsFrameLayout = (FrameLayout) findViewById(R.id.touristAttraction_fragment_container);
        mSiteFrameLayout = (FrameLayout) findViewById(R.id.site_fragment_container);

        // Get a reference to the SupportFragmentManager instead of original FragmentManager
        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        // Commit the FragmentTransaction
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                setLayout();
            }
        });

        // recording recent state of TouristAttractionsFragment
        mTouristAttractionsFragment = (OrlandoTouristAttractionsFragment) mFragmentManager.findFragmentByTag("tourist_attraction");
        if (mTouristAttractionsFragment == null) {
            mTouristAttractionsFragment = new OrlandoTouristAttractionsFragment();
            fragmentTransaction.replace(R.id.touristAttraction_fragment_container, mTouristAttractionsFragment, "tourist_attraction");
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }

        mSiteFragment = (OrlandoSiteFragment) mFragmentManager.findFragmentByTag("site");

        // Initializing if null
        if (mSiteFragment == null) {
            mSiteFragment = new OrlandoSiteFragment();
        } else {
            if (!mSiteFragment.isAdded()) {
                fragmentTransaction.add(R.id.site_fragment_container, mSiteFragment, "site");
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
                mFragmentManager.executePendingTransactions();
            }
        }
        setLayout();
    }

    private void setLayout() {
        // Determine whether the SiteFragment has been added
        if (!mSiteFragment.isAdded()) {
            // list occupies full width
            mTouristAttractionsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            mSiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 1/3, 2/3
            mTouristAttractionsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
            mSiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // separate
            mTouristAttractionsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
            mSiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        }
    }

    @Override
    public void onListSelection(int index) {
        temp = index;
        // If the SiteFragment has not been added, add it now
        if (!mSiteFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.site_fragment_container, mSiteFragment, "site");
            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }
        if (mSiteFragment.getShownIndex() != index) {
            mSiteFragment.showSiteAtIndex(index);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("qwerty", temp);
    }
}