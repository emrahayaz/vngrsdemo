package com.emrahayaz.vngrs;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {

    public static String LIST_ARG1="LOCATION";

    private Button btnConnect;
    private TextView statusText;
    private EditText userInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        statusText = (TextView) findViewById(R.id.statusText);

        btnConnect = (Button) findViewById(R.id.btnConnect);
        userInput = (EditText) findViewById(R.id.userInput);
        btnConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ItemListActivity.class);
                intent.putExtra(LIST_ARG1,userInput.getText().toString());
                startActivity(intent);
            }
        });


    }
}
