package com.one4all.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GroupAdapter groupAdapter;
    RequestQueue requestQueue;
    private static final String TAG = "hello";
    EditText editText;
    Button button;
    ArrayList<String> imageArrayList;
    ArrayList<String> authorArrayList;
    ArrayList<String> likesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Search Wallpaper");
        findView();
        editText.setHint("Search Image");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupAdapter.clear();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                parseJson();
            }
        });
//        groupAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(@NonNull Item item, @NonNull View view) {
////                Log.d(TAG, "onItemClick: "+imageArrayList.get(0));
////                Intent intent = new Intent(MainActivity.this,FullPhoto.class);
////                intent.putExtra("imageUrl",imageArrayList.get(0));
//            }
//        });




    }
    public void findView(){
        recyclerView = findViewById(R.id.recyler_view);
        groupAdapter = new GroupAdapter<GroupieViewHolder>();
        requestQueue = Volley.newRequestQueue(this);
        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);
        imageArrayList = new ArrayList<>();
        authorArrayList = new ArrayList<>();
        likesArrayList = new ArrayList<>();

    }
    public void parseJson(){
        String searchText = editText.getText().toString();
        String url = "https://pixabay.com/api/?key=4305786-c5c9ee84938fa6f3889a9c99c&q="+searchText+"&image_type=photo&pretty=true";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i =0;i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imageUrl = jsonObject.getString("webformatURL");
                        String author = jsonObject.getString("user");
                        String likes = jsonObject.getString("likes");
                        Log.d(TAG, "onResponse: "+imageUrl);
                        Log.d(TAG, "onResponse: "+author);
                        Log.d(TAG, "onResponse: "+likes);
//                        imageArrayList.add(imageUrl);
//                        authorArrayList.add(author);
//                        likesArrayList.add(likes);
                        groupAdapter.add(new ExampleAdapter(MainActivity.this,imageUrl,author,likes));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());

            }
        });


        requestQueue.add(jsonObjectRequest);
        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

}

    class ExampleAdapter extends Item<GroupieViewHolder> {
        String imageUrl;
        String author;
        String likes;
        ImageView imageView;
        TextView textViewAuthor;
        TextView textViewLiks;
        Context context;

        public ExampleAdapter(Context context,String imageUrl, String author, String likes) {
            this.imageUrl = imageUrl;
            this.author = author;
            this.likes = likes;
            this.context =context;
        }

        @Override
        public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
            imageView = viewHolder.itemView.findViewById(R.id.image_view);
            textViewAuthor = viewHolder.itemView.findViewById(R.id.text_view_creator);
            textViewLiks = viewHolder.itemView.findViewById(R.id.text_like);
            Picasso.get().load(imageUrl).into(imageView);
            textViewAuthor.setText("Photo clicked by  "+ author);
            textViewLiks.setText(" "+likes);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("hello2",imageUrl);
                    Intent intent = new Intent(context,FullPhoto.class);
                    intent.putExtra("imageUrl",imageUrl);
                    context.startActivity(intent);

                }
            });
            



        }


        @Override
        public int getLayout() {
            return R.layout.card_view;
        }
    }

