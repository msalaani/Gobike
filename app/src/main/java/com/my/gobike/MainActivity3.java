package com.my.gobike;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {

    private Button button;
    private Button butt1;
    private DatabaseReference ProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("bike_info");
        button = (Button)findViewById(R.id.annonce);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater  = getMenuInflater();
        menuInflater.inflate(R.menu.exemple_menu, menu);
        return true;
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id=item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, AddAnnonceActivity.class);
        startActivity(intent);
    }
}
