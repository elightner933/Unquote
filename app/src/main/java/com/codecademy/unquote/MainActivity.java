package com.codecademy.unquote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;

    // Declare View member variables
    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Show app icon in ActionBar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        // assign View member variables
        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);

        // TODO 4-E: set onClickListener for each answer Button

        // TODO 5-A: set onClickListener for the submit answer Button

        startNewGame();
    }

    // TODO 3-F: displayQuestion(Question question) {...}

    // update the remaining questions counter
    public void displayQuestionsRemaining(int questionRemaining) {
        questionsRemainingTextView.setText(Integer.toString(questionRemaining));
    }

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}

    void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
            totalCorrect = totalCorrect + 1;
        }
        questions.remove(currentQuestion);

        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

            // TODO 5-D: Show a popup instead
            System.out.println(gameOverMessage);
        } else {
            chooseNewQuestion();

            // TODO 3-H.i: uncomment after implementing displayQuestion(Question)
            // displayQuestion(getCurrentQuestion());
        }
    }

    void startNewGame() {
        questions = new ArrayList<>();

        // Provide drawables for each question
        Question question0 = new Question(R.drawable.img_quote_0, getString(R.string.question0), getString(R.string.question0_answer0), getString(R.string.question0_answer1), getString(R.string.question0_answer2), getString(R.string.question0_answer3), 2);
        Question question1 = new Question(R.drawable.img_quote_1, getString(R.string.question1), getString(R.string.question1_answer0), getString(R.string.question1_answer1), getString(R.string.question1_answer2), getString(R.string.question1_answer3), 0);
        Question question2 = new Question(R.drawable.img_quote_2, getString(R.string.question2), getString(R.string.question2_answer0), getString(R.string.question2_answer1), getString(R.string.question2_answer2), getString(R.string.question2_answer3), 1);
        Question question3 = new Question(R.drawable.img_quote_3, getString(R.string.question3), getString(R.string.question3_answer0), getString(R.string.question3_answer1), getString(R.string.question3_answer2), getString(R.string.question3_answer3), 3);
        Question question4 = new Question(R.drawable.img_quote_4, getString(R.string.question4), getString(R.string.question4_answer0), getString(R.string.question4_answer1), getString(R.string.question4_answer2), getString(R.string.question4_answer3), 1);
        Question question5 = new Question(R.drawable.img_quote_5, getString(R.string.question5), getString(R.string.question5_answer0), getString(R.string.question5_answer1), getString(R.string.question5_answer2), getString(R.string.question5_answer3), 0);

        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);

        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();

        displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
        // displayQuestion(firstQuestion);
    }

    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);
    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}