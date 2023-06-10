package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;

public class GameOverScene extends BaseScene {
    private static final String TAG = GameOverScene.class.getSimpleName();
    private int song_id;
    public enum Layer {
        bg, touch, COUNT
    }

    public GameOverScene(int song_id) {
        initLayers(SelectScene.Layer.COUNT);
        add(Layer.bg, new Sprite(R.mipmap.pxfuel, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height));
        add(Layer.bg, new Sprite(R.mipmap.restart, Metrics.game_width / 2, Metrics.game_height / 2 - 1.f, 4.f, 1.f));

        this.song_id = song_id;

        add(GameOverScene.Layer.touch, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button(R.mipmap.yes,
                Metrics.game_width / 2 - 2.f, Metrics.game_height / 2 + 1.f, 2.5f, 1.5f, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Callback() {
            @Override
            public boolean onTouch(kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action action) {
                if (action == kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action.pressed) {
                    getTopScene().popScene();
                    new MainScene(song_id).pushScene();
                }
                return false;
            }
        }));

        add(GameOverScene.Layer.touch, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button(R.mipmap.no,
                Metrics.game_width / 2 + 2.f, Metrics.game_height / 2 + 1.f, 2.5f, 1.5f, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Callback() {
            @Override
            public boolean onTouch(kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action action) {
                if (action == kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action.pressed) {
                    getTopScene().popScene();
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
    protected int getTouchLayerIndex() {
        return PausedScene.Layer.touch.ordinal();
    }
}
