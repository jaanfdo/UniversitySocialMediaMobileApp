package com.example.jaanfdo.myfinalproject.Hero;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.R;
import com.example.jaanfdo.myfinalproject.WebRequest.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class Heros extends AppCompatActivity {
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    //defining views
    EditText editTextHeroId, editTextName, editTextRealname;
    RatingBar ratingBar;
    Spinner spinnerTeam;
    ProgressBar progressBar;
    ListView listView;
    Button buttonAddUpdate;

    //we will use this list to display hero in listview
    List<Hero> heroList;

    //as the same button is used for create and update
    //we need to track whether it is an update or create operation
    //for this we have this boolean
    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heros);

        editTextHeroId = (EditText) findViewById(R.id.editTextHeroId);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextRealname = (EditText) findViewById(R.id.editTextRealname);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        spinnerTeam = (Spinner) findViewById(R.id.spinnerTeamAffiliation);

        buttonAddUpdate = (Button) findViewById(R.id.buttonAddUpdate);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listViewHeroes);

        heroList = new ArrayList<>();

        buttonAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if it is updating
                if (isUpdating) {
                    //calling the method update hero
                    //method is commented becuase it is not yet created
                    updateHero();
                } else {
                    //if it is not updating
                    //that means it is creating
                    //so calling the method create hero
                    createHero();
                }
            }
        });

        //calling the method read heroes to read existing heros from the database
        //method is commented because it is not yet created
        readHeroes();
    }

    private void createHero() {
        String name = editTextName.getText().toString().trim();
        String realname = editTextRealname.getText().toString().trim();
        int rating = (int) ratingBar.getRating();
        String team = spinnerTeam.getSelectedItem().toString();

        //validating the inputs
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please enter name");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(realname)) {
            editTextRealname.setError("Please enter real name");
            editTextRealname.requestFocus();
            return;
        }

        //if validation passes
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("realname", realname);
        params.put("rating", String.valueOf(rating));
        params.put("teamaffiliation", team);

        //Calling the create hero API
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_HERO, params, CODE_POST_REQUEST);
        request.execute();
    }

    private void readHeroes() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_HEROES, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void refreshHeroList(JSONArray heroes) throws JSONException {
        //clearing previous heroes
        heroList.clear();

        //traversing through all the items in the json array
        //the json we got from the response
        for (int i = 0; i < heroes.length(); i++) {
            //getting each hero object
            JSONObject obj = heroes.getJSONObject(i);

            //adding the hero to the list
            heroList.add(new Hero(
                    obj.getInt("id"),
                    obj.getString("name"),
                    obj.getString("realname"),
                    obj.getInt("rating"),
                    obj.getString("teamaffiliation")
            ));
        }

        //creating the adapter and setting it to the listview
        HeroAdapter adapter = new HeroAdapter(heroList);
        listView.setAdapter(adapter);
    }

    private void updateHero() {
        String id = editTextHeroId.getText().toString();
        String name = editTextName.getText().toString().trim();
        String realname = editTextRealname.getText().toString().trim();
        int rating = (int) ratingBar.getRating();
        String team = spinnerTeam.getSelectedItem().toString();

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please enter name");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(realname)) {
            editTextRealname.setError("Please enter real name");
            editTextRealname.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("realname", realname);
        params.put("rating", String.valueOf(rating));
        params.put("teamaffiliation", team);


        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_UPDATE_HERO, params, CODE_POST_REQUEST);
        request.execute();

        Clear();
    }

    private void Clear(){
        buttonAddUpdate.setText("Add");

        editTextName.setText("");
        editTextRealname.setText("");
        ratingBar.setRating(0);
        spinnerTeam.setSelection(0);

        isUpdating = false;
    }

    private void deleteHero(int id) {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_DELETE_HERO + id, null, CODE_GET_REQUEST);
        request.execute();
    }

    //inner class to perform network request extending an AsyncTask
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, String> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        //when the task started displaying a progressbar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    //refreshing the herolist after every operation
                    //so we get an updated list
                    //we will create this method right now it is commented
                    //because we haven't created it yet
                    refreshHeroList(object.getJSONArray("heroes"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    class HeroAdapter extends ArrayAdapter<Hero> {
        //our hero list
        List<Hero> heroList;

        //constructor to get the list
        public HeroAdapter(List<Hero> heroList) {
            super(Heros.this, R.layout.hero_list, heroList);
            this.heroList = heroList;
        }

        //method returning list item
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.hero_list, null, true);

            //getting the textview for displaying name
            TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
            TextView textViewRealName = (TextView) listViewItem.findViewById(R.id.textViewRealName);
            TextView textViewTeam = (TextView) listViewItem.findViewById(R.id.textViewTeam);
            TextView textViewRating = (TextView) listViewItem.findViewById(R.id.textViewRating);

            //the update and delete textview
            TextView textViewUpdate = (TextView) listViewItem.findViewById(R.id.textViewUpdate);
            TextView textViewDelete = (TextView) listViewItem.findViewById(R.id.textViewDelete);

            final Hero hero = heroList.get(position);

            textViewName.setText(hero.getName());
            textViewRealName.setText(hero.getRealname());
            textViewTeam.setText(hero.getTeamaffiliation());
            textViewRating.setText(hero.getRating() + " Rating");

            //attaching click listener to update
            textViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //so when it is updating we will
                    //make the isUpdating as true
                    isUpdating = true;

                    //we will set the selected hero to the UI elements
                    editTextHeroId.setText(String.valueOf(hero.getId()));
                    editTextName.setText(hero.getName());
                    editTextRealname.setText(hero.getRealname());
                    ratingBar.setRating(hero.getRating());
                    spinnerTeam.setSelection(((ArrayAdapter<String>) spinnerTeam.getAdapter()).getPosition(hero.getTeamaffiliation()));

                    //we will also make the button text to Update
                    buttonAddUpdate.setText("Update");
                }
            });

            //when the user selected delete
            textViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // we will display a confirmation dialog before deleting
                    AlertDialog.Builder builder = new AlertDialog.Builder(Heros.this);

                    builder.setTitle("Delete " + hero.getName())
                            .setMessage("Are you sure you want to delete it?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //if the choice is yes we will delete the hero
                                    //method is commented because it is not yet created
                                    deleteHero(hero.getId());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(R.drawable.ic_dialog_alert)
                            .show();

                }
            });

            return listViewItem;
        }
    }
}


