package com.example.firebaseone;

import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    EditText edname;
    EditText edaddress;
    EditText edphone;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    modelAdapter adapter;
    ArrayList<model> model;
    ListView listView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname = findViewById(R.id.name);
        edaddress = findViewById(R.id.address);
        edphone = findViewById(R.id.phone);
        listView = findViewById(R.id.modelTV);
        model = new ArrayList<>();
    }

    public void saveToFirebase(View v) {
        String Name = edname.getText().toString();
        String PhoneNumber = edphone.getText().toString();
        String Address = edaddress.getText().toString();


        Map<String, Object> contact = new HashMap<>();
        contact.put("Name", Name);
        contact.put("Number", PhoneNumber);
        contact.put("Address", Address);
        db.collection("contacts")
                .add(contact)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "The addition succeeded .... " + documentReference.getId());
                    }
                })                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("TAG", "Added Error ......", e);
            }
        });

        db.collection("models")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for(DocumentSnapshot userSnapshot : task.getResult()){
                                String name = (String) userSnapshot.get("name");
                                String phone = (String) userSnapshot.get("phone");
                                String address = (String) userSnapshot.get("address");

                                model user = new model(name,phone,address);
                                model.add(user);
                            }
                            adapter=new modelAdapter(model,MainActivity.this);
                            listView.setAdapter(adapter);

                        } else {
                            Log.w("TAG", "Faild.", task.getException());
                        }
                    }
                });




    }
}