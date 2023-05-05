package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;

import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Button;

public class GameClearScene extends BaseScene {
    private static final String TAG = GameClearScene.class.getSimpleName();

    public enum Layer {
        bg, button, COUNT
    }

    public GameClearScene(int score, int num_300, int num_100, int num_50, int num_x, int max_combo) {
        initLayers(SelectScene.Layer.COUNT);
        add(Layer.bg, new Sprite(R.mipmap.clearescenebg, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height));
        //add(Layer.bg, new Sprite(R.mipmap.restart, Metrics.game_width / 2, Metrics.game_height / 2 - 1.f, 4.f, 1.f));
        add(Layer.button, new Button(R.mipmap.yes, Metrics.game_width / 2 - 2.f, Metrics.game_height / 2 + 1.f, 2.5f, 1.5f,1));
        add(Layer.button, new Button(R.mipmap.no, Metrics.game_width / 2 + 2.f, Metrics.game_height / 2 + 1.f, 2.5f, 1.5f,2));
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
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                ArrayList<IGameObject> buttons = getTopScene().getObjectsAt(GameOverScene.Layer.button);
                for (int i = buttons.size() - 1; i >= 0; i--) {
                    Button gobj = (Button) buttons.get(i);
                    if(CollisionHelper.collides(gobj, x, y)) {
                        switch (gobj.getId()) {
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }
}
