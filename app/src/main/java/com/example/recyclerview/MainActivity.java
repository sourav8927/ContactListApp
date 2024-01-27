package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ArrayList<ContactModel> arrContacts=new ArrayList<>();
RecyclerContactAdapter adapter;
FloatingActionButton btnOpenDialog;
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerContact);
        //for update,insert new contact by floating button
        btnOpenDialog= findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);

                EditText edtName=dialog.findViewById(R.id.edtName);
                EditText edtNumber=dialog.findViewById(R.id.edtNumber);
                Button btnAction=dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",number="";
                        if(!edtName.getText().toString().equals("")) {
                            name = edtName.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                        }
                        if(!edtNumber.getText().toString().equals("")){
                             number=edtNumber.getText().toString();
                    }else{
                            Toast.makeText(MainActivity.this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }

                        arrContacts.add(new ContactModel(R.drawable.a,name,number));

//                        adapter.notifyItemInserted(arrContacts.size()-1);

                        recyclerView.scrollToPosition(arrContacts.size()-1);
                        dialog.dismiss();
                }

            });

                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        ContactModel model=new ContactModel(R.drawable.a,"A","6296103551");
        arrContacts.add(new ContactModel(R.drawable.a,"Sourav","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Tanay","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Souvik","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Srinjan","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Biswarup","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Tanya","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Mahadev","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Nandini","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Kumar","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Yadav","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Sathi","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Madhu","6296103551"));

        arrContacts.add(new ContactModel(R.drawable.a,"Sourav","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Tanay","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Souvik","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Srinjan","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Biswarup","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Tanya","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Mahadev","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Nandini","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Kumar","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Yadav","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Sathi","6296103551"));
        arrContacts.add(new ContactModel(R.drawable.a,"Madhu","6296103551"));
        RecyclerContactAdapter adapter=new RecyclerContactAdapter(this,arrContacts);

        recyclerView.setAdapter(adapter);



    }
}