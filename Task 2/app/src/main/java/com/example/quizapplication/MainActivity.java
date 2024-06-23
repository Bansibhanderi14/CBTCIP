package com.example.quizapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private RadioGroup answersGroup;
    private Button nextButton;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.question_text);
        answersGroup = findViewById(R.id.answers_group);
        nextButton = findViewById(R.id.next_button);
        scoreText = findViewById(R.id.score_text);

        // Sample questions
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"Shakespeare", "Dickens", "Tolkien", "Austen"}, 0));

        displayQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answersGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedAnswer = findViewById(selectedId);
                    int answerIndex = answersGroup.indexOfChild(selectedAnswer);

                    if (answerIndex == questions.get(currentQuestionIndex).getCorrectAnswerIndex()) {
                        score++;
                    }

                    currentQuestionIndex++;

                    if (currentQuestionIndex < questions.size()) {
                        displayQuestion();
                    } else {
                        questionText.setText("Quiz Over!");
                        nextButton.setEnabled(false);
                    }

                    scoreText.setText("Score: " + score);
                }
            }
        });
    }

    private void displayQuestion() {
        Question currentQuestion;
        currentQuestion = questions.get(currentQuestionIndex);
        questionText.setText(currentQuestion.getQuestion());

        String[] answers = currentQuestion.getAnswers();
        for (int i = 0; i < answers.length; i++) {
            RadioButton radioButton = (RadioButton) answersGroup.getChildAt(i);
            radioButton.setText(answers[i]);
        }

        answersGroup.clearCheck();
    }
}
