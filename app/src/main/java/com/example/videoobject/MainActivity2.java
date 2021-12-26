package com.example.videoobject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    private static int MAX_MESSAGE_LENGTH = 100;

    EditText meditText;
    Button mbutton;
    RecyclerView mrecyclerView;
    ArrayList<String> kommetaris = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        String vidAddress = "https://archive.org/download/sinema-trailer_caligula/Caligula%20%281979%29%20ORIGINAL%20TRAILER%20%28480p_30fps_H264-128kbit_AAC%29.mp4";
        Uri vidUri = Uri.parse(vidAddress);
        videoView.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(videoView);
        videoView.setMediaController(vidControl);
        videoView.start();

        meditText = findViewById(R.id.editText);
        mbutton = findViewById(R.id.button);
        mrecyclerView = findViewById(R.id.recyclerView);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter dataAdapter = new DataAdapter(this, kommetaris);
        mrecyclerView.setAdapter(dataAdapter);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kom = meditText.getText().toString();
                if (kom.equals("")) {
                    Toast.makeText(getApplicationContext(), "Введите сообщение!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (kom.length() > MAX_MESSAGE_LENGTH) {
                    Toast.makeText(getApplicationContext(), "Слишком длинное сообщение!", Toast.LENGTH_LONG).show();
                    return;
                }
                myRef.push().setValue(kom);
                meditText.setText("");
            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String kom = dataSnapshot.getValue(String.class);
                kommetaris.add(kom);
                dataAdapter.notifyDataSetChanged();
                mrecyclerView.smoothScrollToPosition(kommetaris.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {            }
        });
    }
}