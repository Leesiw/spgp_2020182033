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

public class SelectScene extends BaseScene {
    private static final String TAG = SelectScene.class.getSimpleName();

    public enum Layer {
        bg, button, COUNT
    }

    public SelectScene() {
        initLayers(SelectScene.Layer.COUNT);
        add(Layer.bg, new Sprite(R.mipmap.pxfuel, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height));
        add(Layer.button, new Button(R.mipmap.cannonrockbtn1, Metrics.game_width / 2, Metrics.game_height / 2 - 1.3f, 8.f, 2.f,1));
        add(Layer.button, new Button(R.mipmap.rustynailbtn1, Metrics.game_width / 2, Metrics.game_height / 2 + 1.3f, 8.f, 2.f,2));
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
                ArrayList<IGameObject> buttons = getTopScene().getObjectsAt(SelectScene.Layer.button);
                for (int i = buttons.size() - 1; i >= 0; i--) {
                    Button gobj = (Button) buttons.get(i);
                    //Log.d("button pressed", "id " + gobj.getId());

                    if(CollisionHelper.collides(gobj, x, y)) {

                        switch (gobj.getId()) {
                            case 1:
                                new MainScene(0).pushScene();
                                break;
                            case 2:
                                new MainScene(1).pushScene();
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

