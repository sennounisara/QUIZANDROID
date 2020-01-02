package com.quiz.app.sara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.quiz.app.sara.ResActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    int score = 0;
    int currentQuestion = 0;
    TextView questionNumber;
    SpinKitView spinKitView;
    TextView question;
    RadioGroup radioGroup;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    RadioButton answer4;
    ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();
    ArrayList<String> answers = new ArrayList<String>();
    Button prev;
    Button answer;
    Button next;
    String trueAnswer;
    List<ParseObject> parseObjects;
    ArrayList<Question> Questions = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        spinKitView = (SpinKitView)  findViewById(R.id.spin_kit);


        questionNumber = findViewById(R.id.questionNumber);
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radioG);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        prev = findViewById(R.id.prev);
        answer = findViewById(R.id.answer);
        next = findViewById(R.id.next);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Questions");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    parseObjects = scoreList;
                    for (ParseObject object: scoreList ) {
                        int id = object.getInt("lvl");
                        String q = object.getString("Question") ;
                        String a = object.getString("answer") ;
                        String f1 = object.getString("falseOne") ;
                        String f2 = object.getString("falseTow") ;
                        String f3 = object.getString("falseThree");



                        Question question = new Question(id,q,a,f1,f2,f3);
                        Questions.add(question);
                    }

                    currentQuestion = 0 ;
                    Question q1 = Questions.get(0);

                    updateUi(q1.getId(),q1.getQuestion(),q1.getAnswer(),q1.getFalseOne(),q1.getFalseTow(),q1.getFalseThre());




                    spinKitView.setVisibility(View.GONE);

                } else {
                    Toast.makeText(getApplicationContext(),"There was an error getting Question from server",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });





        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                String selectedAnswer = radioButton.getText().toString();
                if(selectedAnswer.equals(trueAnswer)){
                    score++;
                }

                nextQuestion(currentQuestion+1);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(currentQuestion+1);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(currentQuestion-1);
            }
        });




    }

    public void nextQuestion(int id){
        if(id>=0 && id<Questions.size()){
            Question q1 = Questions.get(id);

            updateUi(q1.getId(),q1.getQuestion(),q1.getAnswer(),q1.getFalseOne(),q1.getFalseTow(),q1.getFalseThre());
            currentQuestion = id;
        }else if(id == Questions.size()){
            Intent i = new Intent(getApplicationContext(), ResActivity.class);
            i.putExtra("score",score);
            i.putExtra("total",Questions.size());
            startActivity(i);

        }else{

        }


    }

    public void updateUi(int questionNumber,String Question,String answer,String falseOne,String falseTow,String falseThree){

        trueAnswer = answer;

        String s = "Question Number "+questionNumber;
        this.questionNumber.setText(s);
        this.question.setText(Question);
        radioGroup.clearCheck();



        Random random = new Random();
        radioButtons.add(answer1);
        radioButtons.add(answer2);
        radioButtons.add(answer3);
        radioButtons.add(answer4);
        answers.add(answer);
        answers.add(falseOne);
        answers.add(falseTow);
        answers.add(falseThree);
        int i = 0 ;
        while(i<4){
            RadioButton  randomRadioButton = radioButtons.get( random.nextInt(radioButtons.size()));

            String randomAnswer = answers.get( random.nextInt(answers.size()));

            randomRadioButton.setText(randomAnswer);

            radioButtons.remove(randomRadioButton);
            answers.remove(randomAnswer);
            i++;
        }


    }
}
