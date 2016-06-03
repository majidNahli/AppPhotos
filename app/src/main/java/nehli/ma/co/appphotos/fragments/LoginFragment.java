package nehli.ma.co.appphotos.fragments;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nehli.ma.co.appphotos.R;
import nehli.ma.co.appphotos.activities.MainActivity;
import nehli.ma.co.appphotos.model.Data;
import nehli.ma.co.appphotos.utils.Constant;
import nehli.ma.co.appphotos.utils.PreferencesManager;


/**
 * Created by Majid on 03/06/2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private TextView txtProfile;
    private ImageView image;
    private Button btnDisplay;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProfileTracker mProfileTracker;
    private FacebookCallback<LoginResult> resultFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            if(Profile.getCurrentProfile() == null) {
                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                        // profile2 is the new profile
                        Log.v("profile", profile2.getId());
                        txtProfile.setText(profile2.getName());


                        PreferencesManager.putStringPref(getActivity(), Constant.PRPFILE_ID,profile2.getId());
                        Picasso.with(getActivity()).load("https://graph.facebook.com/" + profile2.getId() + "/picture?type=large").into(image);

                        mProfileTracker.stopTracking();
                    }
                };

            }
            else {
                Profile profile = Profile.getCurrentProfile();
                Log.v("profile", profile.getFirstName());
            }


        }

        @Override
        public void onCancel() {

            Log.e("MINI","canceled");
        }

        @Override
        public void onError(FacebookException error) {

            Log.e("MINI",error.getMessage());
        }
    };

    public LoginFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment, container, false);

        txtProfile              = (TextView)  view.findViewById(R.id.txt_profile);
        image                   = (ImageView) view.findViewById(R.id.image);
        btnDisplay              =  (Button)   view.findViewById(R.id.btn_display);

        btnDisplay.setOnClickListener(this);

        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_photos");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, resultFacebookCallback);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void goToListAlbums() {

        Log.d("PROFILE",PreferencesManager.getStringPref(getActivity(), Constant.PRPFILE_ID)+" Token : "+AccessToken.getCurrentAccessToken().getToken());
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+PreferencesManager.getStringPref(getActivity(), Constant.PRPFILE_ID)+"/albums",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                        JSONObject data = response.getJSONObject();
                        try {
                            JSONArray dataOnly = data.getJSONArray("data");

                            MainActivity mainActivity = (MainActivity ) MainActivity.main;
                            mainActivity.setList(Data.parseList(dataOnly));
                            setFragmentAlbums();
                            Log.d("TTM",response.toString());
                            Log.v("MINI","size : "+ Data.parseList(dataOnly).size()+" item 0 "+ Data.parseList(dataOnly).get(1).getName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }
        ).executeAsync();
    }
    public void setFragmentAlbums() {

        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AlbumsFragment fr = new AlbumsFragment();
        fragmentTransaction.replace(R.id.fragment, fr);
        fragmentTransaction.commit();
    }
    public void picWithId(String id_pic) {

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+id_pic,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                    }
                }
        ).executeAsync();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_display :
                goToListAlbums();
                break;
        }
    }
    // AsyncTask load data


    private class DownloadPictureTask extends AsyncTask<Void, Void, Void> {

        private String id;

        public DownloadPictureTask(String id) {
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
