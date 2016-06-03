package nehli.ma.co.appphotos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nehli.ma.co.appphotos.R;
import nehli.ma.co.appphotos.activities.MainActivity;
import nehli.ma.co.appphotos.adapters.GridItemAdapter;
import nehli.ma.co.appphotos.model.Data;

/**
 * Created by Majid on 03/06/2016.
 */
public class AlbumsFragment extends Fragment {

    private GridView gridview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albums_grid_fragment, container, false);


        gridview = (GridView) view.findViewById(R.id.gridview);

        final MainActivity mainActivity = (MainActivity ) MainActivity.main;

        gridview.setAdapter(new GridItemAdapter(getActivity(), mainActivity.getAlbumList()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                goToDetailAlbum(position, mainActivity);

            }
        });

        return view;
    }

    // Go to album pictures
    public void goToDetailAlbum(int position, final MainActivity mainActivity) {

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+mainActivity.getAlbumList().get(position).getId()+"/photos",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                                 /* handle the result */

                        JSONObject data = response.getJSONObject();
                        try {
                            JSONArray dataOnly = data.getJSONArray("data");

                            MainActivity mainActivity = (MainActivity ) MainActivity.main;
                            mainActivity.setList(Data.parseList(dataOnly));

                            setFragmentDetail();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
        ).executeAsync();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void setFragmentDetail() {

        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailAlbumFragment fr = new DetailAlbumFragment();
        fragmentTransaction.replace(R.id.fragment, fr);
        fragmentTransaction.commit();
    }

}
