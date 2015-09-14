package com.emrahayaz.vngrs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.emrahayaz.vngrs.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_NAME = "item_name";
    public static final String ARG_ITEM_ADDRESS = "item_address";

    String name;
    String address;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_NAME) && getArguments().containsKey(ARG_ITEM_ADDRESS)) {
            name=getArguments().getString(ARG_ITEM_NAME);
            address=getArguments().getString(ARG_ITEM_ADDRESS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (name != null) {
            ((TextView) rootView.findViewById(R.id.item_detail_name)).setText(name);
            ((TextView) rootView.findViewById(R.id.item_detail_address)).setText(address);
        }

        return rootView;
    }
}
