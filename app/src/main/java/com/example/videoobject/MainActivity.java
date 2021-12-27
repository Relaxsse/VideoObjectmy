package com.example.videoobject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public final static String EXSTRA_MESSAGE = "com.example.videoobject.MESSAGE";

    String[] videos = {"Первое видео", "Второе видео", "Третье видео", "Четвертое видео"};

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listitemvideoview);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, videos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemid = position;
                String itemId = Integer.toString(itemid);

                switch (position) {
                    case 0:
                        i = new Intent(MainActivity.this, MainActivity2.class);
                        i.putExtra(EXSTRA_MESSAGE, itemId);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, MainActivity2.class);
                        i.putExtra(EXSTRA_MESSAGE, itemId);
                        break;
                    case 2:
                        i = new Intent(MainActivity.this, MainActivity2.class);
                        i.putExtra(EXSTRA_MESSAGE, itemId);
                        break;
                    case 3:
                        i = new Intent(MainActivity.this, MainActivity2.class);
                        i.putExtra(EXSTRA_MESSAGE, itemId);
                        break;
                }
                startActivity(i);
            }
        });
    }
}