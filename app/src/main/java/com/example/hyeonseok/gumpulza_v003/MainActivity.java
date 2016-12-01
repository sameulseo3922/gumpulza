package com.example.hyeonseok.gumpulza_v003;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Question> questionList;
    private Question currentQuestion;

    private ImageView QUESTION;
    private Button btnNext;

    private int questionId = 0;
    private int obtainedScore = 0;

    private byte[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        final DatabaseAccess databaseAccess = new DatabaseAccess(this);
        databaseAccess.getInstance(this);
        databaseAccess.open();
        questionList = databaseAccess.getAllQuestions();
        currentQuestion = questionList.get(questionId);

        setQuestionView();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answered = (RadioButton) findViewById(grp.getCheckedRadioButtonId());

                if (answered != null) {

                    if (currentQuestion.getANSWER().equals(answered.getText())) {
                        obtainedScore++;
                    }

                    if (questionId < databaseAccess.rowCount()) {
                        currentQuestion = questionList.get(questionId);
                        setQuestionView();
                    }
                }

                grp.clearCheck();

            }

        });
    }

    public void init() {

        QUESTION = (ImageView)findViewById(R.id.imageView);

        btnNext = (Button)findViewById(R.id.btnNext);

    }

    public void setQuestionView() {

        data = currentQuestion.getQUESTION();
        Bitmap image = toBitmap(data);
        QUESTION.setImageBitmap(image);

        questionId++;

    }

    public static Bitmap toBitmap(byte[] image) {

        return BitmapFactory.decodeByteArray(image, 0, image.length);

    }

}
