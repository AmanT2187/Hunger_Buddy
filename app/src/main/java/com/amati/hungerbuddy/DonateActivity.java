package com.amati.hungerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class DonateActivity extends AppCompatActivity {

    private static final int pic_id = 123;

    ImageView img1;
    EditText edt1, edt2, edt3;
    Button btn1;
    TextView txt1;

    String name;
    String phone;
    String foodQ;
    String address = "Noida";


    String url;

    Bitmap photo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseStorage storage;
    StorageReference storageReference;
    donateInfo donateInfo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        linking();


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImg();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt1.getText().toString();
                phone = edt2.getText().toString();
                foodQ = edt3.getText().toString();
//                address = txt1.getText().toString();

                if (name.isEmpty() && phone.isEmpty() &&
                        foodQ.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {

                    addDatatoFirebase();
                }
            }
        });

    }

    private void addDatatoFirebase() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();



        //upload image

        StorageReference ref
                = storageReference
                .child(
                        "images/"
                                + UUID.randomUUID().toString() +".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = ref.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                progressDialog.dismiss();
                Toast.makeText(DonateActivity.this, "Error " + exception.toString(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                url = taskSnapshot.getStorage().getDownloadUrl().toString();


//                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//
//
//
//                    }
//                });

                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!urlTask.isSuccessful());
                Uri downloadUrl = urlTask.getResult();

                url =downloadUrl.toString();
                donateInfo dI = new donateInfo(name,phone, address,foodQ,url);
                databaseReference.setValue(dI);

                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                img1.setImageResource(R.drawable.simg);

                progressDialog.dismiss();
            }
        });
    }

    private void selectImg() {

        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, pic_id);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {
            photo = (Bitmap) data.getExtras().get("data");
//            int photo = data.getIntExtra("data",resultCode);

                img1.setImageBitmap(photo);

        }
    }

    private void linking() {
        img1 = findViewById(R.id.imgFood);
        txt1 = findViewById(R.id.txtDLocation);
        edt1 = findViewById(R.id.edtName);
        edt2 = findViewById(R.id.edtPhone);
        edt3 = findViewById(R.id.edtFoodQuant);
        btn1 = findViewById(R.id.btnFDonate);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("donates").child(UUID.randomUUID().toString());
    }
}