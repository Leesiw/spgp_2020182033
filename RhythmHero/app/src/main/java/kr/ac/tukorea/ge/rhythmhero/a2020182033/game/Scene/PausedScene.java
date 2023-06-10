package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.res.Sound;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;

public class PausedScene extends BaseScene {

    public enum Layer {
        bg, touch, COUNT
    }
    public PausedScene() {
        initLayers(Layer.COUNT);
        add(Layer.bg, new Sprite(R.mipmap.bg_menu, 8.0f, 4.5f, 10, 5.75f));

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
