package com.example.realtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivty extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_activty);
        listView = findViewById(R.id.listdatalayout);
        list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    Model model = dataSnapshot1.getValue(Model.class);
                    list.add(model);
                }
                MyAdatper myAdatper = new MyAdatper(DisplayActivty.this, list);
                listView.setAdapter(myAdatper);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Model model = list.get(position);
                        Intent intent = new Intent(DisplayActivty.this, SingleData.class);
                        intent.putExtra("key_id", model.getID());
                        intent.putExtra("key_name", model.getName());
                        intent.putExtra("key_email", model.getEmail());
                        intent.putExtra("key_contact", model.getContact());
                        startActivity(intent);
                    }
                });
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Model model = list.get(position);
                        showUpdateDialog(model.getID(), model.getName(), model.getEmail(), model.getContact());
                        return false;
                    }

                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showUpdateDialog(String id, String name, String email, String contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayActivty.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.update_layout, null);
        builder.setView(view);
        final EditText UNAME=view.findViewById(R.id.u_name);
        EditText UEMAIL=view.findViewById(R.id.u_email);
        EditText UCONTACT=view.findViewById(R.id.u_contact);
        Button UPDATE=view.findViewById(R.id.updatebtn);
        Button DELETE=view.findViewById(R.id.deletebtn);

        builder.setTitle("Updating for "+ name);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

        UPDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME=UNAME.getText().toString().trim();
                String EMAIL=UEMAIL.getText().toString().trim();
                String CONTACT=UCONTACT.getText().toString().trim();
                if(NAME.isEmpty())
                {
                    UNAME.setError("Name required");
                    UNAME.requestFocus();
                    return;
                }
                if(EMAIL.isEmpty())
                {
                    UEMAIL.setError("Email required");
                    UEMAIL.requestFocus();
                    return;
                }
                if(CONTACT.isEmpty())
                {
                    UCONTACT.setError("Contact required");
                    UCONTACT.requestFocus();
                    return;
                }
                updateUser(id,NAME,EMAIL,CONTACT);
                alertDialog.dismiss();
            }
        });
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(id);
            }
        });

    }

    private void deleteData(String id) {
      DatabaseReference  databaseReference=FirebaseDatabase.getInstance().getReference("user").child(id);
        databaseReference.removeValue();
        Toast.makeText(DisplayActivty.this,"Data Deleted",Toast.LENGTH_SHORT).show();

    }

    private boolean updateUser(String id, String name, String email, String contact) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(id);
        Model model = new Model(id, name, email, contact);
        databaseReference.setValue(model);
        Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show();
        return true;
    }
}