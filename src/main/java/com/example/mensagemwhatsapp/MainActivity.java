package com.example.mensagemwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutNumber;
    private TextInputLayout inputLayoutMessage;

    private TextInputEditText inputEditNumber;
    private TextInputEditText inputEditMessage;

    private Button buttonSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLayoutNumber = findViewById(R.id.input_layout_number);
        inputLayoutMessage = findViewById(R.id.input_layout_message);

        inputEditNumber = findViewById(R.id.input_edit_number);
        inputEditMessage = findViewById(R.id.input_edit_message);

        buttonSendMessage = findViewById(R.id.submit_button);

        buttonSendMessage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendMessage();
                    }

                }
        );
    }

    private void sendMessage() {



        String tel = inputEditNumber.getText().toString().trim();
        String message = inputEditMessage.getText().toString();


        if(tel.isEmpty()) {
            inputEditNumber.setError("Favor preencher o campo");
            return;
        }

        if(message.isEmpty()) {
            inputEditMessage.setError("Favor preencher o campo");
            return;
        }


        if(tel.length() < 11) {
            inputEditNumber.setError("Favor inserir um número válido");
            return;
        }

        if(!tel.substring(0, 2).equals("55") && tel.length() == 11) {
            tel = "55" + tel;
        }

        String url = "https://wa.me/" + tel +"?text=" + message;

        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);

    }
}