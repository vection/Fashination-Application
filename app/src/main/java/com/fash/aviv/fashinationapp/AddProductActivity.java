package com.fash.aviv.fashinationapp;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Add Product page should attached to AccountActivity.
 */
public class AddProductActivity extends AppCompatActivity {

    private StorageReference storageReference;

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText p_name, p_price, p_desc, p_cat, p_image, p_quantity;
    Button add, uimage;
    int id;
    Uri image;
    ImageView mImage;
    ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct_page);

        /*
        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        p_name = (EditText) findViewById(R.id.addproductname_id);
        p_cat = (EditText) findViewById(R.id.addproductcat_id);
        p_price = (EditText) findViewById(R.id.addproductprice_id);
        p_desc = (EditText) findViewById(R.id.addproductdesc_id);
        p_image = (EditText) findViewById(R.id.addproductimage_id);
        p_quantity = (EditText) findViewById(R.id.quantity_id);
        add = (Button) findViewById(R.id.addproduct_btn);
        uimage = (Button) findViewById(R.id.imagebrowse_id);
        mImage = (ImageView) findViewById(R.id.uploadimage_id);
        progressBar = (ProgressBar) findViewById(R.id.progress_upload);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
        uimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

*/
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            image = data.getData();

            mImage.setImageURI(image);
            uploadImage();
        }

    }

    private void addProduct() {
        if (TextUtils.isEmpty(p_name.toString()) || TextUtils.isEmpty(p_price.toString()) || TextUtils.isEmpty(p_price.toString()) || TextUtils.isEmpty(p_image.toString()) || TextUtils.isEmpty(p_quantity.toString())) {
            Toast.makeText(this, "Some fields missing", Toast.LENGTH_LONG).show();
            return;
        }
        
        try {
            String uniqueID = UUID.randomUUID().toString();
            String category = p_cat.getText().toString().trim();
            Product t = new Product(uniqueID, p_name.getText().toString().trim(), p_price.getText().toString().trim(), p_desc.getText().toString().trim(), p_image.getText().toString().trim(), category);
            t.save();
            Toast.makeText(this, "Product added successfuly. ", Toast.LENGTH_SHORT).show();
        } catch (Exception p) {
            Toast.makeText(this, "There is a problem with the details above.", Toast.LENGTH_SHORT).show();
        }
    }

   /* private void writeNewProduct(Product t, int id, String cat) {
        databaseReference = firebaseDatabase.getReference("products/" + cat + "/" + id);
        databaseReference.setValue(t).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Product added succesfuly.", Toast.LENGTH_SHORT);
                return;
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "There is a problem with adding this product.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
*/
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadImage() {
        if (p_image.getText() != null) {
            final StorageReference fileref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(image));
            UploadTask uploadTask = fileref.putFile(image);


            //Upload image
            if(image != null)
            {
                //Show progressbar
                progressBar.setVisibility(View.VISIBLE);

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        return fileref.getDownloadUrl();
                    }

                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            p_image.setText(task.getResult().toString());
                        }
                    }
                });
            }

        }

    }
}
