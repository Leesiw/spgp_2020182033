package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;


import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;


public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();

    public static float song_play_time;

    public enum Layer {
        hit_mark, slide_mark, spin_mark, score_mark, COUNT
    }

    public MainScene() {
        song_play_time = 0.0f;

        initLayers(Layer.COUNT);
        //add(Layer.hit_mark, HitMark.get(1, 1, 5.f, 5.f, 0.f, 5.f));
       // add(Layer.slide_mark, SlideMark.get(1, 1, 1.1f, 1.1f, 3.3f, 3.3f, 1.f, 3.f, 6.f, 3));
        add(Layer.spin_mark, SpinMark.get(0.f, 12.f));
    }

    @Override
    public void update(long elapsedNanos) {
        song_play_time += elapsedNanos / 1_000_000_000f; // 이후 곡 재생 시간으로 변경
        super.update(elapsedNanos);
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
                    SlideMark gobj = (SlideMark) spin_marks.get(i);
                    return true;
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }
}