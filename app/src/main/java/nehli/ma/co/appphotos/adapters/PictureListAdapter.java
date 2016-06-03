package nehli.ma.co.appphotos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import nehli.ma.co.appphotos.R;
import nehli.ma.co.appphotos.model.Data;


/**
 * Created by Majid on 03/06/2016.
 */
public class PictureListAdapter extends BaseAdapter implements AdapterView.OnClickListener{

    private Context mContext;
    private List<Data> dataList;
    private TextView textView;
    private ImageView imageView;
    private CheckBox checkBox;

    public PictureListAdapter(Context mContext, List<Data> dataList) {
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
        textView          = (TextView)  v.findViewById(R.id.txt_album);
        imageView         = (ImageView) v.findViewById(R.id.image_grid);
        checkBox    = (CheckBox)  v.findViewById(R.id.checkBox);


        Log.d("IDPIC","size album : "+ dataList.size());
        displayPicture(position);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setColorFilter(Color.GRAY);
            }
        });
        v.setOnClickListener(this);
        return v;
    }

    // display picture by id
    public void displayPicture(int position) {
        Bundle params = new Bundle();
        params.putBoolean("redirect", false);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+ dataList.get(position).getId()+"/picture",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                        Log.d("MINIP",response.toString());
                        JSONObject data = response.getJSONObject();

                        try {

                            Picasso.with(mContext).load(data.getJSONObject("data").getString("url")).into(imageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
        ).executeAsync();
    }

    @Override
    public void onClick(View view) {

        Log.d("IDPIC","click item");
        checkBox.setVisibility(View.VISIBLE);

    }
}
