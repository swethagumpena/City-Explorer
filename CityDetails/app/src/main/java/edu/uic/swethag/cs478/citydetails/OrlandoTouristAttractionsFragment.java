package edu.uic.swethag.cs478.citydetails;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OrlandoTouristAttractionsFragment extends ListFragment {
    private static final String TAG = "TouristAttractionsFragment";
    private ListSelectionListener mListener = null;

    public interface ListSelectionListener {
        void onListSelection(int index);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int pos, long id) {
        // Indicates the selected item has been checked
        getListView().setItemChecked(pos, true);
        l.setItemChecked(pos, true);
        // Inform the OrlandoSiteActivity that the item in position pos has been selected
        mListener.onListSelection(pos);
    }

    @Override
    public void onAttach(@NonNull Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);

        try {
            // Set the ListSelectionListener for communicating with the OrlandoSiteActivity
            // Try casting the containing activity to a ListSelectionListener
            mListener = (ListSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnListItemSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
        super.onViewCreated(view, savedState);

        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.attraction_item, OrlandoActivity.mToursitAttractionsArray));

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
}