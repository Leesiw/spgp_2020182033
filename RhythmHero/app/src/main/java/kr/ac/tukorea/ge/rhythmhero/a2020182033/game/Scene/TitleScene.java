package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;

public class TitleScene extends BaseScene {
    private static final String TAG = TitleScene.class.getSimpleName();

    public enum Layer {
        bg, COUNT
    }

    public TitleScene() {
        initLayers(TitleScene.Layer.COUNT);
        add(TitleScene.Layer.bg, new AnimSprite(R.mipmap.rythm, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height, 10, 500, 0));
        add(TitleScene.Layer.bg, new Sprite(R.mipmap.rhythmherotxt, Metrics.game_width / 2, Metrics.game_height / 2, 10.f, 2.f));
        add(TitleScene.Layer.bg, new Sprite(R.mipmap.touchtostart, Metrics.game_width / 2, Metrics.game_height / 2 + 3.f, 4.f, 0.8f));
    }

    @Override
    public void update(long elapsedNanos) {
        super.update(elapsedNanos);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                new SelectScene().pushScene();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }
}
