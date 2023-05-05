package kr.ac.tukorea.ge.rhythmhero.a2020182033.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

    }

    public void onStart(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}