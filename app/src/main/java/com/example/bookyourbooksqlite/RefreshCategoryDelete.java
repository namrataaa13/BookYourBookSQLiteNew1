package com.example.bookyourbooksqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RefreshCategoryDelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_category_delete);

        String mEmail = getIntent().getExtras().getString("mEmail").toString();

        Intent delIntent=new Intent(RefreshCategoryDelete.this,category.class);
        delIntent.putExtra("mEmail", mEmail);
        finish();
        startActivity(delIntent);

    }
}