package com.example.uberapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.uberapp.Model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyFile extends AppCompatActivity {

    private ImageView mProfileImage;
    private Button imageButton;
    private Button updateButton,imgupload;
    private EditText Name,EmailId,Phone,CarName;
    private RadioGroup mRadioGroup;
    Uri resultUri;

    DatabaseReference mDriverDatabase;
    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    private static final String UName = "nameKey";

    private String userID;
    private String mName;
    private String mPhone;
    private String mCar;
    private String mProfileImageUrl;
    private String mservices;


    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        mProfileImage = findViewById(R.id.profileimage);
        Name = findViewById(R.id.name);
        Phone = findViewById(R.id.number);
        EmailId = findViewById(R.id.emaiid);
        CarName=findViewById(R.id.car);
        mRadioGroup=findViewById(R.id.radiogroup);
        updateButton=findViewById(R.id.update);
        imageButton=findViewById(R.id.btnChoose);
        imgupload=findViewById(R.id.upload);
        imgupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadimg();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Driver").child(userID);

        getUserInfo();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personEmail = acct.getEmail();
            EmailId.setText(personEmail);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
                //Intent in = new Intent(MyFile.this,MapsActivity.class);
                //startActivity(in);
            }
        });

/*
        user = new User();
        sharedPreferencesUser=getApplicationContext().getSharedPreferences("Users",0);
        String imguri=sharedPreferencesUser.getString("images","");
        final String num = sharedPreferencesUser.getString("phone","");
        System.out.println("Phone No"+num);
        Phone.setText(num);

        System.out.println("Shared Preference string is"+imguri);
        Glide.with(this).load(imguri).error(R.drawable.ic_person).into(imageView);
        if(sharedPreferencesUser.contains(UName))
        {
            Name.setText(sharedPreferencesUser.getSt