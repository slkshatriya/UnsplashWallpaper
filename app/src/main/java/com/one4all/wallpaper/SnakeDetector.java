package com.one4all.wallpaper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SnakeDetector extends AppCompatActivity {
    WebView webView;
    ImageView imageView;
    Button chooseImage,analyze;
    Button button;
    Uri imageBitmapUri;

    final String url2 ="https://www.nriol.com/images/diwali-new.jpg";
    String url ="https://project-av.onrender.com/analyze/";
//    RequestQueue requestQueue;


    public static final String TAG ="volley";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_detector);
        imageView = findViewById(R.id.image_view);
//        chooseImage = findViewById(R.id.choose_button);
//        analyze = findViewById(R.id.analyze);
        button = findViewById(R.id.button);
//        imageView2 = findViewById(R.id.simage_view);
//        requestQueue = Volley.newRequestQueue(this);
        
        Log.d("volley","onCreate called");








////
//            JSONObject jsonObject = new JSONObject();
//            try{
//            jsonObject.put("filename", url2);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,0);
                }
            });
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,url ,new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "onResponse: called"+response);
//                Log.d(TAG, "onResponse: "+imageBitmapUri);
//
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "onErrorResponse: "+error.toString());
//
//            }
//        });
//        requestQueue.add(jsonObject);






////        String url = "https://pixabay.com/api/?key=4305786-c5c9ee84938fa6f3889a9c99c&q="+"flower"+"&image_type=photo&pretty=true";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("volley","success");
//
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("volley","volley failed" +
//                        "/n "+error.toString());
//
//            }
//        }) {
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("Content-Type", "image/jpg");
//                headers.put("origin", "https://project-av.onrender.com");
//                headers.put("referer", "https://project-av.onrender.com/");
//                headers.put("filename",url2);
//                return headers;
//            }
//        };
//
//        requestQueue.add(jsonObjectRequest);

//        ImageRequest
//                imageRequest
//                = new ImageRequest(url2,
//                new Response.Listener() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//
//                    }
//
//
//                },
//                2000, 1358, null);



//        webView = findViewById(R.id.web_view);
//        webView.setWebViewClient(new WebViewClient());
//        WebSettings webViewSetings = webView.getSettings();

//        webView.loadUrl("https://project-av.onrender.com");
////        webView.loadUrl("https://www.google.com");
//        webViewSetings.setJavaScriptEnabled(true);
//        webView.addJavascriptInterface(new WebAppInterface(this),"Android");
//        WebAppInterface webAppInterface = new WebAppInterface(SnakeDetector.this);
//        webView.setClickable(true);
//        ImageRequest imageRequest = new ImageRequest(
//                url, // Image URL
//                new Response.Listener<Bitmap>() { // Bitmap listener
//                    @Override
//                    public void onResponse(Bitmap response) {
//                    Log.d("volley","success");
//
//                    }
//                },
//                0, // Image width
//                0, // Image height
//                ImageView.ScaleType.CENTER_CROP, // Image scale type
//                Bitmap.Config.RGB_565, //Image decode configuration
//                new Response.ErrorListener() { // Error listener
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Do something with error response
//                        error.printStackTrace();
//                        Log.d("volley","failed");
//                    }
//                }
//        );
//        requestQueue.add(imageRequest);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==0 && resultCode == RESULT_OK && data != null){
            Log.d(TAG, "onActivityResult: success");
            imageBitmapUri = data.getData();
            Log.d(TAG, "onActivityResult: "+imageBitmapUri);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageBitmapUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//            imageView2.setBackgroundDrawable(bitmapDrawable);



        }
    }
    public  void fetchImage(){
        Uri parseUri = Uri.parse(url+"/analyze");


    }
    public static String POST(String fileName) throws ClientProtocolException, IOException {

        HttpClient httpclient = HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost("https://project-av.onrender.com/analyze");

        FileBody fileBody = new FileBody(new File(fileName));

        MultipartEntityBuilder mpEntity = MultipartEntityBuilder
                .create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("file", fileBody);

        httppost.setEntity(mpEntity.build());

        HttpResponse response = httpclient.execute(httppost);

        //System.out.println(response.getStatusLine());

        HttpEntity resEntity = response.getEntity();

        return EntityUtils.toString(resEntity);

    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 0 && resultCode == RESULT_OK && data != null){
//            Uri uri = data.getData();
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//            imageView.setBackgroundDrawable(bitmapDrawable);
////            try {
//////                String result = Post(uri.toString());
////            } catch (IOException e) {
////                e.printStackTrace();
////                Log.d(TAG, "onActivityResult:  failed");
////            }
//
//
//        }
//    }

//    @Override
//    public void onBackPressed() {
//
//        if (webView.canGoBack()){
//            webView.goBack();
//        }else {
//            super.onBackPressed();
//        }
//    public   static  void Post(String fileName){
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//         okhttp3.Request request = new okhttp3.Request().Builder.url("https://project-av.onrender.com").build();
//        try (okhttp3.Response response = okHttpClient.newCall(request).execute()) {
//        }catch (Exception e){
//            Log.d(TAG, "Post: "+e.toString());
//        }
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: "+e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                if(!response.isSuccessful()){
//                    Log.d(TAG, "onResponse: "+ "response unsuccesful");
//                }else {
//                    Log.d(TAG, "onResponse: "+"successful");
//                }
//
//            }
//        });
//
//    }
//public static String Post(String fileName) throws ClientProtocolException, IOException {
//
//
//    HttpClient httpclient = HttpClientBuilder.create().build();
//
//    HttpPost httppost = new HttpPost("https://project-av.onrender.com/analyze");
//    FileBody fileBody = new FileBody(new File(fileName));
//
//    MultipartEntityBuilder mpEntity = MultipartEntityBuilder
//            .create()
//            .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
//            .addPart("file", fileBody);
//
//    httppost.setEntity(mpEntity.build());
//
//    HttpResponse response = httpclient.execute(httppost);
//
//    //System.out.println(response.getStatusLine());
//
//    HttpEntity resEntity = response.getEntity();
//
//    return EntityUtils.toString(resEntity);
//
//}




}
