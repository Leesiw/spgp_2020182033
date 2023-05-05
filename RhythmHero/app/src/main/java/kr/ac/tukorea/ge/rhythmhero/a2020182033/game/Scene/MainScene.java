package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;


import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util.Gauge;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.HitMark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.SlideMark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.SpinMark;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Score;


public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();

    public static float song_play_time;

    public static Score score;
    public static Score combo;

    public static Gauge gauge1;
    public static Gauge gauge2;
    public static float gaugeValue;

    public enum Layer {
        hit_mark, slide_mark, spin_mark, score_mark, ui, COUNT
    }

    public MainScene() {
        song_play_time = 0.0f;

        initLayers(Layer.COUNT);
        //add(Layer.hit_mark, HitMark.get(1, 1, 5.f, 5.f, 0.f, 5.f));
        add(Layer.slide_mark, SlideMark.get(1, 1, 1.1f, 1.1f, 3.3f, 3.3f, 1.f, 3.f, 6.f, 3));
        //add(Layer.spin_mark, SpinMark.get(0.f, 12.f));
        score = new Score(Metrics.game_height - 1.f, Metrics.game_width - 0.5f, 7);
        score.setScore(0);
        add(Layer.ui, score);

        combo = new Score(Metrics.game_height - 1.f, 2.f, 0);
        combo.setScore(0);
        add(Layer.ui, combo);

        add(Layer.ui, new Sprite(R.mipmap.combox, 2.3f, Metrics.game_height - 0.6f, 0.4f, 0.4f));
        add(Layer.ui, new Sprite(R.mipmap.scoretxt, 10.3f, Metrics.game_height - 0.65f, 3.f, 0.6f));

        gauge1 = new Gauge(0.8f, R.color.pink, R.color.black, 1.f, 0.5f, 7.f);
        gauge2 = new Gauge(0.8f, R.color.yellow, R.color.black, 8.f,0.5f, 7.f);
        gaugeValue = 100.f;

    }

    @Override
    public void update(long elapsedNanos) {
        song_play_time += elapsedNanos / 1_000_000_000f; // 이후 곡 재생 시간으로 변경
        if(gaugeValue > 0) {gaugeValue -= (elapsedNanos / 500_000_000f);}
        else{
            new GameOverScene().pushScene();
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
}