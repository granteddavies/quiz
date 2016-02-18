package com.quiz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionFragment extends Fragment {

    private static final String ARG_INDEX = "index";
    private static final String ARG_SCORE = "score";
    private static final int NUM_QUESTIONS = 3;

    private int questionIndex;
    private int score;

    private boolean answer;

    private Button buttonYes;
    private Button buttonNo;
    private TextView textQuestion;
    private ImageView imageQuestion;

    public QuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        // Instantiate widgets
        buttonYes = (Button) view.findViewById(R.id.buttonYes);
        buttonNo = (Button) view.findViewById(R.id.buttonNo);
        textQuestion = (TextView) view.findViewById(R.id.questionText);
        imageQuestion = (ImageView) view.findViewById(R.id.questionImage);

        updateQuestion();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            questionIndex = getArguments().getInt(ARG_INDEX);
            score = getArguments().getInt(ARG_SCORE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endQuestion(answer);
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endQuestion(!answer);
            }
        });
    }

    public void updateQuestion() {
        switch (questionIndex) {
            case 0:
                textQuestion.setText("Is 2 + 2 = 5?");
                imageQuestion.setImageResource(0);
                answer = false;
                break;
            case 1:
                textQuestion.setText("Is this a star?");
                imageQuestion.setImageResource(R.drawable.star);
                answer = true;
                break;
            case 2:
                textQuestion.setText("Was this quiz fun?");
                imageQuestion.setImageResource(0);
                answer = true;
                break;
        }
    }

    public void endQuestion(boolean correct) {
        if (correct) {
            score++;
        }

        questionIndex++;
        if (questionIndex < NUM_QUESTIONS) {
            updateQuestion();
        }
        else {
            ((MainActivity) getActivity()).endQuiz(score, NUM_QUESTIONS);
        }
    }

    public static QuestionFragment newInstance(int questionIndex, int score) {
        QuestionFragment f = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, questionIndex);
        args.putInt(ARG_SCORE, score);
        f.setArguments(args);
        return f;
    }
}