package kr.ac.tukorea.ge.rhythmhero.a2020182033.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.GameView;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.Mark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.HitMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.SlideMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.SpinMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene.TitleScene;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    public static ArrayList<ArrayList<Mark>> songMark = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        for (int i = 0; i < 2; i++) {
            songMark.add(new ArrayList<>());
        }

        jsonParsing(getJsonString("CanonRock.json"), 0);
        jsonParsing(getJsonString("RustyNail.json"), 1);

        Collections.sort(songMark.get(0));
        Collections.sort(songMark.get(1));

        new TitleScene(MainActivity.this).pushScene();
    }

    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        GameView.clear();
        super.onDestroy();
    }

    private void jsonParsing(String json, int song_id)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray hitmarkArray = jsonObject.getJSONArray("HitMark");

            for(int i=0; i<hitmarkArray.length(); i++)
            {
                JSONObject markObject = hitmarkArray.getJSONObject(i);

                int num = markObject.getInt("num");
                int color = markObject.getInt("color");
                float x = (float)markObject.getDouble("x");
                float y = (float)markObject.getDouble("y");
                int appeared_timing = markObject.getInt("appeared_timing");
                float touch_timing = (float)markObject.getDouble("touch_timing");

                songMark.get(song_id).add(new HitMarkData(num, color, x, y, appeared_timing, touch_timing));
            }

            JSONArray slideArray = jsonObject.getJSONArray("SlideMark");

            for(int i=0; i<slideArray.length(); i++)
            {
                JSONObject markObject = slideArray.getJSONObject(i);

                int num = markObject.getInt("num");
                int color = markObject.getInt("color");
                float x1 = (float)markObject.getDouble("x1");
                float y1 = (float)markObject.getDouble("y1");
                float x2 = (float)markObject.getDouble("x2");
                float y2 = (float)markObject.getDouble("y2");
                int appeared_timing = markObject.getInt("appeared_timing");
                float start_timing = (float)markObject.getDouble("start_timing");
                float end_timing = (float)markObject.getDouble("end_timing");
                int return_num = markObject.getInt("return_num");
                songMark.get(song_id).add(new SlideMarkData(num, color, x1, y1, x2, y2, appeared_timing, start_timing, end_timing, return_num));
            }

            JSONArray spinArray = jsonObject.getJSONArray("SpinMark");

            for(int i=0; i<spinArray.length(); i++)
            {
                JSONObject markObject = spinArray.getJSONObject(i);

                int appeared_timing = markObject.getInt("appeared_timing");
                float end_timing = (float)markObject.getDouble("end_timing");
                songMark.get(song_id).add(new SpinMarkData(appeared_timing, end_timing));
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getJsonString(String file_name)
    {
        String json = "";

        try {
            InputStream is = getAssets().open(file_name);
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }
}