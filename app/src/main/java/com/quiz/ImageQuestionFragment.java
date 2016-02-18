package com.quiz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageQuestionFragment extends Fragment {

    private static final String ARG_SCORE = "score";

    private int score;

    private boolean answer;

    private EditText editTextAnswer;
    private Button buttonSubmit;

    public ImageQuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_question, container, false);

        // Instantiate widgets
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        editTextAnswer = (EditText) view.findViewById(R.id.editTextAnswer);

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
                // If the question is answered correctly, increment the score
                if (editTextAnswer.getText().toString().equalsIgnoreCase("star")) {
                    score++;
                }

                // Move on to the text question
                ((MainActivity) getActivity()).startTextQuestion(score);
            }
        });
    }

    public static ImageQuestionFragment newInstance(int score) {
        ImageQuestionFragment f = new ImageQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        f.setArguments(args);
        return f;
    }
}