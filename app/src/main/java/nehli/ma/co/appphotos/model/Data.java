package nehli.ma.co.appphotos.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Majid on 28/05/2016.
 */
public class Data {

    private String id;
    private String name;
    private String created_time;

    public Data() {
    }


    public static Data parseJSONObject(JSONObject jsonObject) {

        Data data = new Data();
        try {

            data.setCreated_time(jsonObject.getString("created_time"));
            data.setId(jsonObject.getString("id"));

            if (jsonObject.has("name"))
                data.setName(jsonObject.getString("name"));

            return data;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static List<Data> parseList(JSONArray jsonArray) {

        List<Data> dataList = new ArrayList<>();

        for (int i=0 ; i< jsonArray.length(); i++) {

            try {

                dataList.add(parseJSONObject(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return dataList;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
