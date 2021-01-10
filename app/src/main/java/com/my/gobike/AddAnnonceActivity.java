package com.my.gobike;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddAnnonceActivity extends AppCompatActivity {
    private EditText number, location, d1, d2;
    private static final String TAG="AddAnnonceActivity";
    private ImageView img;
    private String saveCurrentDate, saveCurrentTime, productRandomKey, downloadImageUrl;
    private ProgressDialog loadingBar;
    private Button submitbutton, date1, date2,addpic;
    Spinner spinner;
    FirebaseDatabase rootnode;
    DatabaseReference renter_reference;
    StorageReference mstorage_referenc;
    public Uri imguri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_annonce);
        getSupportActionBar();
        mstorage_referenc = FirebaseStorage.getInstance().getReference("images");
        renter_reference = FirebaseDatabase.getInstance().getReference("renter_offer");
        location = (EditText) findViewById(R.id.location);
        number = (EditText) findViewById(R.id.phonenumber);
        d1 = findViewById(R.id.firstdate);
        d2 = findViewById(R.id.secondate);
        date1 = findViewById(R.id.firstdate1);
        date2 = findViewById(R.id.secondate2);
        spinner=findViewById(R.id.spinner);
        addpic=findViewById(R.id.addpic2);
        img= findViewById(R.id.picture);

        submitbutton = findViewById(R.id.submitbutton);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddAnnonceActivity.this, calendar.class);
                AddAnnonceActivity.this.startActivity(intent);
                finish();
            }
        });
        Intent IncomingIntent = getIntent();
        String date = IncomingIntent.getStringExtra("date");

        d1.setText(date);




        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddAnnonceActivity.this, calendar.class);
                AddAnnonceActivity.this.startActivity(intent);
                finish();
            }
        });
        Intent secondIntent = getIntent();
        String date11 = secondIntent.getStringExtra("date");
        d2.setText(date11);


        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filechooser();

            }
        });





        submitbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adding_ad();
                fileuploder();


            }
        });
    }
    private void filechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null  && data.getData()!=null){
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }
    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void fileuploder(){
        StorageReference Ref=mstorage_referenc.child(System.currentTimeMillis()+"."+ getExtension(imguri));
        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        Toast.makeText(AddAnnonceActivity.this, "image uploaded", Toast.LENGTH_LONG);
                        Ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference imagestore = FirebaseDatabase.getInstance().getReference().child(System.currentTimeMillis()+"."+ getExtension(imguri));
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("imageurl", String.valueOf(uri));
                                imagestore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(AddAnnonceActivity.this, "Finally complete ", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }





    private void adding_ad() {
        String phone = number.getText().toString();
        String text = location.getText().toString();
        String dd1 = d1.getText().toString();
        String dd2 = d2.getText().toString();
        String genre= spinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(dd1)) {
            Toast.makeText(this, "Please enter the date", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(dd2)) {
            Toast.makeText(this, "Please enter the date...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "Please write your location ...", Toast.LENGTH_SHORT).show();
        }

        else {
            String id = renter_reference.push().getKey();
            ;
            renter renter = new renter(id,phone,text,dd1,dd2,genre);
            renter_reference.child(id).setValue(renter);
            Toast.makeText(this, "the offer has been added", Toast.LENGTH_LONG).show();
            loadingBar.setTitle("Adding the offer");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


        }
    }



}