package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fast2meet.Model.AddServiceUpdateModel;
import com.example.fast2meet.Model.ServiceProviderModel;
import com.example.fast2meet.databinding.ActivityEditServiceBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.Random;

public class EditService extends AppCompatActivity implements View.OnClickListener {

    String[] service = {"Select Service", "Electrician", "Plumber and Plumbing", "Home Repair", "Painter", "Solar Panel", "Cleaning", "Renovation", "Tailor", "Photograph", "Carpenter", "Ac Service", "Door to Door Package"};

    String mobile;
    ActivityEditServiceBinding binding;
    FirebaseDatabase rootnode;
    DatabaseReference databaseReference;
    Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_service);

        init();
        setSpinnerAdapter();
        setListnear();
    }

    private void init()
    {
        Intent intent = getIntent();

        mobile = intent.getStringExtra("mobile");

        Log.e("Mobile in Updaete ", " Mobile Number  :" + mobile);

        binding.etUpdateSname.setText(intent.getStringExtra("sname"));
        binding.etUpdatecontact.setText(intent.getStringExtra("contact"));
        binding.etUpdatemail.setText(intent.getStringExtra("bemail"));
        binding.etUpdatewebsite.setText(intent.getStringExtra("website"));
        binding.etUpdateAddress.setText(intent.getStringExtra("address"));
        binding.etUpdateServiceDSC.setText(intent.getStringExtra("serviceDSC"));

        Glide.with(getApplicationContext()).load(intent.getStringExtra("Uri").toString()).into(binding.imgupload);


    }

    private void setListnear() {
        binding.btnSubmit.setOnClickListener(this);
        binding.imgupload.setOnClickListener(this);
        binding.imgback.setOnClickListener(this);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<String> splocation = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, service);
        binding.spService.setAdapter(splocation);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnSubmit) {
            updateValue();
        }

        if (id == R.id.imgupload) {
            imgupload();
        }
        if(id == R.id.imgback)
        {
            onBackPressed();
        }
    }


    public void imgupload()
    {
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//
//
//        Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 1);
//        startActivityForResult(captureImage,2);


        final CharSequence[] options = { "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(EditService.this);
        builder.setTitle("Choose  picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    // Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                    takePicture.setType("imaages/*");
                    // mediaFile = getOutputMediaFile();

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void updateValue()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Service Provider");

        String sname = binding.etServiceName.getEditText().getText().toString();
        String contact = binding.etContact.getEditText().getText().toString();
        String bemail = binding.etEmail.getEditText().getText().toString();
        String website = binding.etWebiste.getEditText().getText().toString();
        String address = binding.etAddress.getEditText().getText().toString();
        String serviceName = binding.spService.getSelectedItem().toString();
        String serviceDSC = binding.etserviceDecd.getEditText().getText().toString();



        //AddServiceUpdateModel addServiceUpdateModel = new AddServiceUpdateModel(sname,contact,bemail,website,address);
        // databaseReference.setValue(addServiceUpdateModel);

        databaseReference.child(mobile).child("sname").setValue(sname);
        databaseReference.child(mobile).child("contactNo").setValue(contact);
        databaseReference.child(mobile).child("bemail").setValue(bemail);
        databaseReference.child(mobile).child("website").setValue(website);
        databaseReference.child(mobile).child("address").setValue(address);
        databaseReference.child(mobile).child("serviceDSC").setValue(serviceDSC);
        databaseReference.child(mobile).child("serviceName").setValue(serviceName);


        //Image Update Code

        ProgressDialog progressDialog = new ProgressDialog(EditService.this);
        progressDialog.setTitle("File Uploading..");
        progressDialog.show();

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference("Image" + new Random().nextInt(100));

        storageReference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri)
                    {

                        //progressDialog.dismiss();

                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Service Provider");


                        //ServiceProviderModel serviceProviderModel = new ServiceProviderModel(name, email, phoneNumber, password, sname, contactNo, bemail, website, serviceName, serviceDSC, address, uri.toString());

                        databaseReference1.child(mobile).child("image").setValue(uri.toString());

                        progressDialog.dismiss();
                        Toast.makeText(EditService.this, "Submit Successfully", Toast.LENGTH_SHORT).show();

//                        Intent intent = new Intent(AddService.this,AddServiceDetailed.class);
//                        intent.putExtra("mobile",phoneNumber);

//                        intent.putExtra("sname",sname);
//                        intent.putExtra("contactNo",contactNo);
//                        intent.putExtra("bemail",bemail);
//                        intent.putExtra("website",website);
//                        intent.putExtra("serviceName",serviceName);
//                        intent.putExtra("serviceDSC",serviceDSC);
//                        intent.putExtra("address",address);
//                        intent.putExtra("Uri",uri.toString());

                      //  startActivity(intent);

                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                float percent = (100 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("Upload :"+(int)percent+" %");
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)

    {


//        Log.e("Before URi :","URI :"+filepath);
//      if(requestCode == 0)
//      {
//          Log.e("CAMERA DATA","CAMERA DATA :"+filepath);
//          //filepath = data.getData();
//         //  Log.e("MEDIA PATH ",mediaFile.getAbsolutePath());
//
//          // filepath = Uri.fr(mediaFile);
//
//          //filepath = getUriForFile(getContext(), "com.mydomain.fileprovider", newFile);
//
//          Log.e("CAMERA URL","URL  CAMERA  :"+filepath);
//          Bitmap photo = (Bitmap) data.getExtras().get("data");
//          Log.e("BitMAP Camera ","BITMAP camera :"+photo);
//          binding.imgupload.setImageBitmap(photo);
//      }
//
        if(requestCode == 1)
        {

            filepath = data.getData();

            Log.e(" ACTIVITY RESULT  :", "IMAGE UPLOAD FILE Path " + filepath);
            try
            {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                binding.imgupload.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}