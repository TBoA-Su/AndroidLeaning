package com.tboa.learning15_roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDatabase wordDatabase;
    WordDao wordDao;
    Button button, button2, button3, button4;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        wordDatabase = Room.databaseBuilder(this, WordDatabase.class, "word_database").allowMainThreadQueries().build();
        wordDao = wordDatabase.getWordDao();
        updateView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hello", "你好");
                Word word2 = new Word("World", "世界");
                wordDao.insertWords(word, word2);
                updateView();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好啊");
                word.setId(20);
                wordDao.updateWords(word);
                updateView();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
                updateView();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好啊");
                word.setId(17);
                wordDao.deleteWords(word);
                updateView();
            }
        });
    }

    void updateView() {
        List<Word> list = wordDao.getAllWords();
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            Word word = list.get(i);
            text += word.getId() + ":" + word.getWord() + "=" + word.getChineseMeaning() + "\n";
        }
        textView.setText(text);
    }
}