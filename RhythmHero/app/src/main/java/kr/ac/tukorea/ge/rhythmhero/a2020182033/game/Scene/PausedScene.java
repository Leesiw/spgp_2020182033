package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.res.Sound;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;

public class PausedScene extends BaseScene {
   // private final Sprite title;
    private float angle;

    public enum Layer {
        bg, touch, COUNT
    }
    public PausedScene() {
        initLayers(Layer.COUNT);
        //add(Layer.bg, new Sprite(R.mipmap.bg_city_landscape, 8.0f, 4.5f, 16, 9));
        add(Layer.bg, new Sprite(R.mipmap.bg_menu, 8.0f, 4.5f, 10, 5.75f));
        //title = new Sprite(R.mipmap.cookie_run_title, 8.0f, 4.5f, 3.69f, 1.36f);
        //add(Layer.bg, title);
        add(Layer.touch, new Button(R.mipmap.btn_resume, 8f, 3.5f, 4.667f, 3f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    Sound.resumeMusic();
                    popScene();
                }
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_exit, 8f, 5.5f, 4.667f, 3f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    getTopScene().popScene();
                    getTopScene().popScene();
                }
                return false;
            }
        }));
    }

    @Override
    public void update(long elapsedNanos) {
        super.update(elapsedNanos);
        float x = (float) (8.0f + 4.0f * Math.cos(angle));
        float y = (float) (4.5f + 2.0f * Math.sin(angle));
       // title.moveTo(x, y);
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public boolean handleBackKey() {
        Sound.resumeMusic();
        getTopScene().popScene();
        return true;
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}
