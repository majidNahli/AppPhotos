package nehli.ma.co.appphotos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nehli.ma.co.appphotos.R;
import nehli.ma.co.appphotos.model.Data;


/**
 * Created by Majid on 03/06/2016.
 */
public class GridItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<Data> dataList;
    private TextView tv;
    private ImageView img;

    public GridItemAdapter(Context mContext, List<Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    public int getCount() {
        return dataList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_album_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        tv       = (TextView) v.findViewById(R.id.txt_album);
        img      = (ImageView) v.findViewById(R.id.image_grid);

            // if it's not recycled, initialize some attributes

        img.setColorFilter(Color.GRAY);
        tv.setText(dataList.get(position).getName());


        return v;
    }

}
