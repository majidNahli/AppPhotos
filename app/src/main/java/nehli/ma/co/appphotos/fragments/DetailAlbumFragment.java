package nehli.ma.co.appphotos.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RelativeLayout relativeLayout = (RelativeLayout) v;
                int color = Color.TRANSPARENT;
                Drawable background = v.getBackground();
                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable) background).getColor();
                relativeLayout.setBackgroundColor(color == Color.WHITE ? Color.parseColor("#5A5B57") :  Color.WHITE);

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
