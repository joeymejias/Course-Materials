package com.example.joeymejias.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    EditText mEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);

        mTextView.setText("");
        mEditText.setHint("This is delicious");

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View view) {
                if(view instanceof Button) {
                    Toast.makeText(view.getContext(), "Button clicked!", Toast.LENGTH_SHORT).show();
                } else if (view instanceof TextView){
                    Toast.makeText(view.getContext(), "TextView clicked!", Toast.LENGTH_SHORT).show();
                }
                mTextView.setText("Bro, wtf?");
            }
        };

        mButton.setOnClickListener(clickListener);
        mTextView.setOnClickListener(clickListener);
    }
}
