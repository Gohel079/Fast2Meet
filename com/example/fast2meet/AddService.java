package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fast2meet.Model.ServiceProviderModel;
import com.example.fast2meet.databinding.ActivityAddServiceBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static java.security.AccessController.getContext;

public class AddService extends AppCompatActivity implements View.OnClickListener {

    String[] service = {"Select Service", "Electrician", "Plumber and Plumbing", "Home Repair","Painter", "Solar Panel", "Cleaning", "Renovation", "Tailor", "Photograph", "Carpenter", "Ac Service", "Door to Door Package"};


    ActivityAddServiceBinding binding;

    FirebaseDatabase rootnode;
    DatabaseReference databaseReference;

    String name, email, phoneNumber, password;
    String sname, contactNo, bemail, website, serviceName, serviceDSC, address;
    Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_service);

        setSpinnerAdapter();

        init();

        setListnear();





    }

    private void init()
    {
        //Toast.makeText(this, "dfddfdfdf", Toast.LENGTH_SHORT).show();


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phoneNumber = intent.getStringExtra("mobile");
        password = intent.getStringExtra("password");

        Toast.makeText(this, "phone", Toast.LENGTH_SHORT).show();
        Log.e("ADD SERVICE", "ADD SERVICE PHONE NU :" + name);
        Log.e("ADD SERVICE", "ADD SERVICE PHONE NU :" + email);
        Log.e("ADD SERVICE", "ADD SERVICE PHONE NU :" + phoneNumber);
        Log.e("ADD SERVICE", "ADD SERVICE PHONE NU :" + password);

    }

    private void setListnear()
    {
        binding.btnSubmit.setOnClickListener(this);
        binding.imgupload.setOnClickListener(this);
    }

    private void setSpinnerAdapter()
    {
        ArrayAdapter<String> splocation = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, service);
        binding.spService.setAdapter(splocation);

    }


    private Boolean validateServiceName() {
        String val = binding.etServiceName.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.etServiceName.setError("Field Cannot be Empty");
            return false;
        } else {
            binding.etServiceName.setError(null);
            binding.etServiceName.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateNumber() {
        String val = binding.etContact.getEditText().getText().toString();

        if (val.length() > 10 | val.length() < 10) {
            binding.etContact.setError("At least 10 Digit number");
            return false;
        } else {
            binding.etContact.setError(null);
            binding.etContact.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = binding.etEmail.getEditText().getText().toString();

        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (!val.matches(emailPattern)) {
            binding.etEmail.setError("Invalid Email Address");
            return false;
        } else {

            binding.etEmail.setError(null);
            binding.etEmail.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validateSpinner() {

        if (binding.spService.getSelectedItem().toString().trim().equals("Select Service")) {
            Toast.makeText(getApplicationContext(), "Please Select Service", Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }

    private Boolean validateServiceDesc() {
        String val = binding.etserviceDecd.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.etserviceDecd.setError("Field Cannot be Empty");
            return false;
        } else {
            binding.etserviceDecd.setError(null);
            binding.etserviceDecd.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateAddress() {
        String val = binding.etAddress.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.etAddress.setError("Field Cannot be Empty");
            return false;
        } else {
            binding.etAddress.setError(null);
            binding.etAddress.setErrorEnabled(false);
            return true;
        }
    }


    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        if (id == R.id.btnSubmit) {
//            if(!validateServiceName() | !validateNumber() | !validateEmail() | !validateSpinner() | !validateServiceDesc() | !validateAddress())
//            {
//                return;
//            }

            ProgressDialog progressDialog = new ProgressDialog(AddService.this);
            progressDialog.setTitle("File Uploading..");
            progressDialog.show();


            sname = binding.etServiceName.getEditText().getText().toString();
            contactNo = binding.etContact.getEditText().getText().toString();
            bemail = binding.etEmail.getEditText().getText().toString();
            website = binding.etWebiste.getEditText().getText().toString();
            serviceName = binding.spService.getSelectedItem().toString();
            serviceDSC = binding.etserviceDecd.getEditText().getText().toString();
            address = binding.etAddress.getEditText().getText().toString();


            //imgupload();

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

                            rootnode = FirebaseDatabase.getInstance();

                            databaseReference = rootnode.getReference("Service Provider");

                            ServiceProviderModel serviceProviderModel = new ServiceProviderModel(name, email, phoneNumber, password, sname, contactNo, bemail, website, serviceName, serviceDSC, address, uri.toString());

                            databaseReference.child(phoneNumber).setValue(serviceProviderModel);

                            progressDialog.dismiss();

                            Toast.makeText(AddService.this, "Submit Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AddService.this,AddServiceDetailed.class);
                            intent.putExtra("mobile",phoneNumber);

                            intent.putExtra("sname",sname);
                            intent.putExtra("contactNo",contactNo);
                            intent.putExtra("bemail",bemail);
                            intent.putExtra("website",website);
                            intent.putExtra("serviceName",serviceName);
                            intent.putExtra("serviceDSC",serviceDSC);
                            intent.putExtra("address",address);
                            intent.putExtra("Uri",uri.toString());

                            startActivity(intent);

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


        if (id == R.id.imgupload) {
            imgupload();
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

        AlertDialog.Builder builder = new AlertDialog.Builder(AddService.this);
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


//        if (requestCode == 0 && resultCode == RESULT_OK)
//        {
//            filepath = data.getData();
//            Log.e("ACTIVITY RESULT  :", "IMAGE UPLOAD FILE Path " + filepath);
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(filepath);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                binding.imgupload.setImageBitmap(bitmap);
//            } catch (Exception e)
//            {
//
//            }
//
//        }

//        if (requestCode == 1 && resultCode == RESULT_OK)
//        {
//            filepath = data.getData();
//            Log.e("ACTIVITY RESULT  :", "IMAGE UPLOAD FILE Path " + filepath);
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(filepath);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                binding.imgupload.setImageBitmap(bitmap);
//            } catch (Exception e)
//            {
//
//            }
//
//        }


//    public File getOutputMediaFile() {
//
//        // External sdcard location
//        File mediaStorageDir = AddService.this.getExternalCacheDir();
//
//        // Create a media file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
//                Locale.getDefault()).format(new Date());
//        return new File(mediaStorageDir.getPath() + File.separator
//                + "IMG_" + timeStamp + "_" + new Random().nextInt() + ".jpg");
//    }



}