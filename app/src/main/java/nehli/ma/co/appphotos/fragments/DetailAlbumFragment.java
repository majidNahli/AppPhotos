package nehli.ma.co.appphotos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;

import nehli.ma.co.appphotos.R;
import nehli.ma.co.appphotos.activities.MainActivity;
import nehli.ma.co.appphotos.adapters.PictureListAdapter;

/**
 * Created by Majid on 03/06/2016.
 */
public class DetailAlbumFragment extends Fragment {

    private GridView gridview;
    private CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_list_fragment, container, false);


        gridview    = (GridView) view.findViewById(R.id.gridview);


        final MainActivity mainActivity = (MainActivity ) MainActivity.main;

        gridview.setAdapter(new PictureListAdapter(getActivity(), mainActivity.getAlbumList()));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
