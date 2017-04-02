package com.example.daychen.guessnumber;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daychen.guessnumber.GuessNumberAdapter.GuessNumberAdapterOnClickHandler;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements GuessNumberAdapterOnClickHandler {

    private RecyclerView mRececleView;
    private GuessNumberAdapter mGuessNumberAdapter;
    private int computerAnswer;
    private int totalNumber = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRececleView = (RecyclerView)findViewById(R.id.recyclerview_guessnumber);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRececleView.setLayoutManager(layoutManager);

        mRececleView.setHasFixedSize(true);

        mGuessNumberAdapter = new GuessNumberAdapter(this);

        mRececleView.setAdapter(mGuessNumberAdapter);
        loadGuessNumberData();
    }

    private void loadGuessNumberData(){
        showGuessNumberDataView();
        Context context = this;
        Toast.makeText(context, "新回合開始", Toast.LENGTH_SHORT)
                .show();
        Random ran = new Random();
        computerAnswer = ran.nextInt(totalNumber) + 1;
        mGuessNumberAdapter.setGuessNumber(totalNumber, computerAnswer);

    }

    private void showGuessNumberDataView(){
        mRececleView.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.guess_number, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            // COMPLETED (46) Instead of setting the text to "", set the adapter to null before refreshing
            totalNumber = Integer.parseInt(((TextView)findViewById(R.id.totalNumber)).getText().toString());
            mGuessNumberAdapter.setGuessNumber();
            loadGuessNumberData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int guessThisNumber) {
        Context context = this;
        if (guessThisNumber == computerAnswer) {
            Toast.makeText(context, "本回合結束，答案就是" + Integer.toString(guessThisNumber), Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
