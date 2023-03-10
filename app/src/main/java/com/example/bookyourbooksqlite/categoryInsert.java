package com.example.bookyourbooksqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class categoryInsert extends AppCompatActivity {
    TextInputEditText categoryName;
    Button catInsertBtn;
    public static final String url = "https://bookyourbook000webhost.000webhostapp.com/Booksimg/categoryInsert.php";
    String category, mEmail;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_insert);

        mEmail = getIntent().getExtras().getString("mEmail").toString().trim();

        catInsertBtn = findViewById(R.id.catInsertBtn);

        catInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryName = findViewById(R.id.categoryName);

                category = categoryName.getText().toString().trim();

                if (category.length() > 0) {
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Category Uploaded Successfully!!")) {
                                mProgressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Category Uploaded Successfully!!", Toast.LENGTH_LONG).show();
                                categoryName.setText("");
                                finish();

                            } else {
                                mProgressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), ":(" + response.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("t1", category);
                            map.put("t2", mEmail);
                            return map;
                        }
                    };

                    mProgressDialog = new ProgressDialog(categoryInsert.this);
                    mProgressDialog.setTitle("Please Wait!!");
                    //mProgressDialog.setMax(100);
                    mProgressDialog.setMessage("Uploading....");
                    mProgressDialog.setProgressStyle(mProgressDialog.STYLE_SPINNER);
                    mProgressDialog.show();
                    mProgressDialog.setCancelable(false);

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(request);

                } else {
                    Toast.makeText(categoryInsert.this, "Category Required!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
