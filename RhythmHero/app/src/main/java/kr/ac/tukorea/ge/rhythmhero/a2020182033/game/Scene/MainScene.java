package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;


import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.app.MainActivity;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.res.Sound;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util.Gauge;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.HitMark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.Mark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.SlideMark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.SpinMark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.HitMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.SlideMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.SpinMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Score;


public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();

    private static int SongResIds[]={ R.raw.canonrock, R.raw.rustynail };

    public static float song_play_time;

    public static Score score;
    public static Score combo;

    public int song_id;

    public Gauge gauge1;
    public Gauge gauge2;
    public static float gaugeValue;

    private static int mark_num;
    public static int[] score_num = {0, 0, 0, 0}; // x, 50, 100, 300

    public static int max_combo;

    public enum Layer {
        hit_mark, slide_mark, spin_mark, score_mark, ui, COUNT
    }

    public MainScene(int song_id) {
        this.song_id = song_id;

        initLayers(Layer.COUNT);

        score = new Score(Metrics.game_height - 1.f, Metrics.game_width - 0.5f, 7);
        add(Layer.ui, score);

        combo = new Score(Metrics.game_height - 1.f, 2.f, 0);
        add(Layer.ui, combo);

        add(Layer.ui, new Sprite(R.mipmap.combox, 2.3f, Metrics.game_height - 0.6f, 0.4f, 0.4f));
        add(Layer.ui, new Sprite(R.mipmap.scoretxt, 10.3f, Metrics.game_height - 0.65f, 3.f, 0.6f));

        gauge1 = new Gauge(0.8f, R.color.pink, R.color.black, 1.f, 0.5f, 7.f);
        gauge2 = new Gauge(0.8f, R.color.yellow, R.color.black, 8.f,0.5f, 7.f);
        gaugeValue = 100.f;
        
        reset();

        Sound.playMusic(SongResIds[song_id], false);
    }

    static public void reset(){
        for(int i = 0; i < score_num.length; ++i){
            score_num[i] = 0;
        }

        max_combo = 0;

        mark_num = 0;

        song_play_time = 0.0f;

        gaugeValue = 100;

        score.setScore(0);
        combo.setScore(0);
    }

    @Override
    public void update(long elapsedNanos) {
        //song_play_time += elapsedNanos / 1_000_000;//_000f; // 이후 곡 재생 시간으로 변경
        song_play_time = Sound.getCurPosition();

        ArrayList<Mark> objects = MainActivity.songMark.get(song_id);

        for(int j = mark_num; j < objects.size(); ++j){
            Mark mark = objects.get(j);
            if(mark.getAppeared_timing() <= song_play_time){
                int type = mark.getType();
                mark_num++;
                if(type == 0){
                    HitMarkData markData = (HitMarkData) mark;
                    add(Layer.hit_mark, HitMark.get(markData));
                }
                else if(type == 1){
                    SlideMarkData markData = (SlideMarkData) mark;
                    add(Layer.slide_mark, SlideMark.get(markData));
                }
                else{
                    SpinMarkData markData = (SpinMarkData) mark;
                    add(Layer.spin_mark, SpinMark.get(markData));
                }
            }
            else{
               break;
            }
        }

        if(gaugeValue > 0) { gaugeValue -= (elapsedNanos / 500_000_000f); }
        //if(gaugeValue > 0) {gaugeValue -= (elapsedNanos / 5000_000f);}
        else{
            Sound.stopMusic();
            getTopScene().popScene();
            new GameOverScene(song_id).pushScene();
            return;
        }

        if(!Sound.getIsPlaying()){
            getTopScene().popScene();
            new GameClearScene(song_id, score.getScore(), score_num[3], score_num[2], score_num[1], score_num[0],max_combo).pushScene();
        }
        super.update(elapsedNanos);
    }

    @Override
    public void draw(Canvas canvas) {
        if(gaugeValue > 50) {
             gauge1.draw(canvas, 1.f);
             gauge2.draw(canvas, (gaugeValue - 50.f) / 50.f);
        }
        else{
            gauge1.draw(canvas, gaugeValue / 50.f);
            gauge2.draw(canvas, 0.f);
        }
        super.draw(canvas);


        //song_play_time += elapsedNanos / 1_000_000_000f; // 이후 곡 재생 시간으로 변경
        //super.update(elapsedNanos);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());

                ArrayList<IGameObject> spin_marks = getTopScene().getObjectsAt(Layer.spin_mark);
                for (int i = spin_marks.size() - 1; i >= 0; i--) {
                    SpinMark gobj = (SpinMark) spin_marks.get(i);
                    gobj.setPrevAngle(x, y);
                    return true;
                }

                ArrayList<IGameObject> marks = getTopScene().getObjectsAt(Layer.hit_mark);
                for (int i = marks.size() - 1; i >= 0; i--) {
                    HitMark gobj = (HitMark) marks.get(i);
                    if (CollisionHelper.collides(gobj, x, y)) {
                        if (!(gobj instanceof IBoxCollidable)) {
                            continue;
                        }
                        gobj.isTouched();
                        return true;
                    }
                }

                ArrayList<IGameObject> slide_marks = getTopScene().getObjectsAt(Layer.slide_mark);
                for (int i = slide_marks.size() - 1; i >= 0; i--) {
                    SlideMark gobj = (SlideMark) slide_marks.get(i);
                    gobj.touchedHitMark(x, y);
                    return true;
                }
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());

                ArrayList<IGameObject> spin_marks = getTopScene().getObjectsAt(Layer.spin_mark);
                for (int i = spin_marks.size() - 1; i >= 0; i--) {
                    SpinMark gobj = (SpinMark) spin_marks.get(i);
                    gobj.isTouched(x, y);
                    return true;
                }

                ArrayList<IGameObject> slide_marks = getTopScene().getObjectsAt(Layer.slide_mark);
                for (int i = slide_marks.size() - 1; i >= 0; i--) {
                    SlideMark gobj = (SlideMark) slide_marks.get(i);
                    gobj.setXY(x, y);
                    return true;
                }
                return true;
            }
            case MotionEvent.ACTION_UP: {
                ArrayList<IGameObject> slide_marks = getTopScene().getObjectsAt(Layer.slide_mark);
                for (int i = slide_marks.size() - 1; i >= 0; i--) {
                    SlideMark gobj = (SlideMark) slide_marks.get(i);
                    gobj.TouchUp();
                    return true;
                }

            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean handleBackKey() {
        Sound.pauseMusic();
        new PausedScene().pushScene();
        return true;
    }
}