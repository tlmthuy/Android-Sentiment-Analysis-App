package com.example.sentimentanalysis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private Button submit;
    private TextView output;
    private final String api_key = BuildConfig.API_KEY;
    private GenerativeModelFutures modelFutures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        submit = findViewById(R.id.submit);
        output = findViewById(R.id.output);

        GenerationConfig config = new GenerationConfig.Builder().build();

        GenerativeModel gm = new GenerativeModel(
                "models/gemini-2.5-flash",
                api_key,
                config
        );

        modelFutures = GenerativeModelFutures.from(gm);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sentence = input.getText().toString().trim();
                if (!sentence.isEmpty()) {
                    output.setText("Đang phân tích...");
                    analyzeSentiment(sentence);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hw2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void analyzeSentiment(String userInput) {
        String prompt = "Phân tích ngắn gọn, chỉ 1 câu cảm xúc của câu sau: " + userInput;
        Content content = new Content.Builder().addText(prompt).build();

        ListenableFuture<GenerateContentResponse> response = modelFutures.generateContent(content);
        Executor executor = Executors.newSingleThreadExecutor();

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                runOnUiThread(() -> output.setText(resultText));
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> output.setText("Lỗi: " + t.getMessage()));
            }
        }, executor);
    }
}