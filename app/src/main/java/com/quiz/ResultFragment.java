package com.quiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultFragment extends Fragment {

    private static final String ARG_POSSIBLE_SCORE = "possibleScore";
    private static final String ARG_SCORE = "score";

    private int score;
    private int possibleScore;

    private Button buttonAgain;
    private Button buttonQuit;
    private TextView textScore;

    public ResultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        // Instantiate widgets
        buttonAgain = (Button) view.findViewById(R.id.buttonAgain);
        buttonQuit = (Button) view.findViewById(R.id.buttonQuit);
        textScore = (TextView) view.findViewById(R.id.score);

        textScore.setText("Score: " + score + "/" + possibleScore);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            score = getArguments().getInt(ARG_SCORE);
            possibleScore = getArguments().getInt(ARG_POSSIBLE_SCORE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restart the quiz
                ((MainActivity) getActivity()).startQuiz();
            }
        });
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to home (exit the app)
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public static ResultFragment newInstance(int score, int possibleScore) {
        ResultFragment f = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSSIBLE_SCORE, possibleScore);
        args.putInt(ARG_SCORE, score);
        f.setArguments(args);
        return f;
    }
}
