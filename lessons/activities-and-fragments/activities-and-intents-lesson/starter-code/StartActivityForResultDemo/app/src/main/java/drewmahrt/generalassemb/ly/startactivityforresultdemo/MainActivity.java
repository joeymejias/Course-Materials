package drewmahrt.generalassemb.ly.startactivityforresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int NAME_REQUEST = 1; // requestCode Constant must be above 0

    private Button mButton;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView)findViewById(R.id.text);

        mButton = (Button)findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, NAME_REQUEST); // requestCode Constant must be above 0
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NAME_REQUEST){
            if(resultCode == RESULT_OK){
                int firstName = data.getIntExtra("first", 0);
                int lastName = data.getIntExtra("last", 0);
                mText.setText("Addition: " + (firstName + lastName)); // setText takes string setting "Addition:"
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
