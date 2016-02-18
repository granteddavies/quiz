package com.quiz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TextQuestionFragment extends Fragment {

    private static final String ARG_SCORE = "score";

    private int score;

    private boolean answer;

    private CheckBox checkBoxYes;
    private Button buttonSubmit;

    public TextQuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_question, container, false);

        // Instantiate widgets
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        checkBoxYes = (CheckBox) view.findViewById(R.id.checkBoxYes);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            score = getArguments().getInt(ARG_SCORE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the question is answered correctly, increment score
                if (checkBoxYes.isChecked()) {
                    score++;
                }

                // End the quiz and show resuslts
                ((MainActivity) getActivity()).endQuiz(score);
            }
        });
    }

    public static TextQuestionFragment newInstance(int score) {
        TextQuestionFragment f = new TextQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        f.setArguments(args);
        return f;
    }
}