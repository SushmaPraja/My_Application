package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class FrameActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    Button button, buttonClear;
    String[] words = {"apple","orange","grapes","banana","mango"};
    int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        listView = findViewById(R.id.wordList);
        textView = findViewById(R.id.textV);
        button = findViewById(R.id.btnOk);
        buttonClear = findViewById(R.id.btnClear);

        showWord(level);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr_word = textView.getText().toString();
                if(usr_word.equals( words[level])){
                    level++;
                    showWord(level);
                    textView.setText("");
                }
                else{
                    Toast.makeText(FrameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
       // Toast.makeText(this, shufflewWord(words[0]) , Toast.LENGTH_SHORT).show();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
            }
        });
    }


    public void  showWord(int i){
        Character[] word = shufflewWord(words[i]);
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(FrameActivity.this, R.layout.spinner_values, word);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String letter = parent.getItemAtPosition(position).toString();
                textView.append(letter);
            }
        });

    }
    private Character[] shufflewWord(String word){
        ArrayList<Character> chars = new ArrayList<>(word.length());
        for(char c: word.toCharArray()){
            chars.add(c);
        }
        Collections.shuffle(chars);
        Character[] shuffeled = new  Character[chars.size()];
        for (int i=0; i<shuffeled.length; i++){
            shuffeled[i] = chars.get(i);
        }
       // String shuffeledword = new String(shuffeled);
        return  shuffeled;
    }
}
