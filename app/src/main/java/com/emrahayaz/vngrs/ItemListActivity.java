package com.emrahayaz.vngrs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details
 * (if present) is a {@link ItemDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity
        implements ItemListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RequestQueue queue;
    ItemListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String placeToSearch=getIntent().getStringExtra(MainActivity.LIST_ARG1);

        setContentView(R.layout.activity_item_list);
        listFragment=((ItemListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.item_list));

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.

            listFragment.setActivateOnItemClick(true);
        }

        queue=Volley.newRequestQueue(this);
        getListOfPlaces(placeToSearch);



        // TODO: If exposing deep links into your app, handle intents here.
    }


    private void getListOfPlaces(String near){

        final ProgressDialog progress = ProgressDialog.show(this, "",
                "Fetching places", true);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(AppData.getRequestUrl(near),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("volley response : "+ response);
                ArrayList<ItemHolder> temp=new ArrayList<ItemHolder>();
                try {
                    JSONArray array=response.getJSONObject("response").getJSONArray("venues");
                    int len=array.length();
                    for(int i=0;i<len;i++){
                        temp.add(new ItemHolder(array.getJSONObject(i)));
                    }
                    ItemListFragment.itemAdapter.setItems(temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {

                }




                progress.dismiss();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("volley error : "+ error);
            }
        });
        queue.add(jsonObjectRequest);
        queue.start();
    }

    /**
     * Callback method from {@link ItemListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(ItemHolder item) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_NAME, item.name);
            arguments.putString(ItemDetailFragment.ARG_ITEM_ADDRESS, item.address);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_NAME, item.name);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ADDRESS, item.address);
            startActivity(detailIntent);
        }
    }
}
