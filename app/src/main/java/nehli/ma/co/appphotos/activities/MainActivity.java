package nehli.ma.co.appphotos.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nehli.ma.co.appphotos.R;
import nehli.ma.co.appphotos.model.Data;

public class MainActivity extends AppCompatActivity {

    public static List<Data> dataList;
    public static Activity main;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;
        dataList = new ArrayList<>();

    }

    public void setList(List<Data> list) {

        dataList = list;
    }

    public List<Data>  getAlbumList() {

        return dataList;
    }

}
