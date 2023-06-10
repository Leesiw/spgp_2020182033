package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;

import android.content.Context;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.res.Sound;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;

public class SelectScene extends BaseScene {
    private static final String TAG = SelectScene.class.getSimpleName();

    public enum Layer {
        bg, touch, COUNT
    }

    Context context;

    public SelectScene(Context context) {
        this.context = context;
        initLayers(SelectScene.Layer.COUNT);
        add(Layer.bg, new Sprite(R.mipmap.pxfuel, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height));

        add(SelectScene.Layer.touch, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button(R.mipmap.canonrockbtn1,
                Metrics.game_width / 2, Metrics.game_height / 2 - 1.3f, 8.f, 2.f, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Callback() {
            @Override
            public boolean onTouch(kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action action) {
                if (action == kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action.pressed) {
                    Sound.stopMusic();
                    new MainScene(0).pushScene();
                }
                return false;
            }
        }));
        add(SelectScene.Layer.touch, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button(R.mipmap.rustynailbtn1,
                Metrics.game_width / 2, Metrics.game_height / 2 + 1.3f, 8.f, 2.f, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Callback() {
            @Override
            public boolean onTouch(kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action action) {
                if (action == kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action.pressed) {
                    Sound.stopMusic();
                    new MainScene(1).pushScene();
                }
                return false;
            }
        }));
    }

    @Override
    public void update(long elapsedNanos) {
        super.update(elapsedNanos);
    }

    @Override
    protected void onStart() {
        Sound.playMusic(R.raw.selectbgm, true);
    }

    @Override
    protected void onEnd() {
        Sound.stopMusic();
    }

    @Override
    protected int getTouchLayerIndex() {
        return SelectScene.Layer.touch.ordinal();
    }
}

