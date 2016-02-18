package com.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * Main activity that handles all the logic for this quiz app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up button listener
        final Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make sure to hide the button
                startButton.setVisibility(View.GONE);
                startQuiz();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Logic for begining the quiz. Pulls up the fragment for the image question.
     */
    public void startQuiz() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, ImageQuestionFragment.newInstance(0))
                .addToBackStack(null)
                .commit();
    }

    /**
     * Logic for moving to the second question of the quiz. Pulls up the fragment for the text
     * question.
     *
     * @param score the current number of correctly answered questions
     */
    public void startTextQuestion(int score) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, TextQuestionFragment.newInstance(score))
                .addToBackStack(null)
                .commit();
    }

    /**
     * Logic for ending the quiz. Pulls up the fragment for the results.
     *
     * @param score the current number of correctly answered questions
     */
    public void endQuiz(int score) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, ResultFragment.newInstance(score, 2))
                .addToBackStack(null)
                .commit();
    }
}
