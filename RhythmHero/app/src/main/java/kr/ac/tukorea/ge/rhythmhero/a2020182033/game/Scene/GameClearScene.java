package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Score;

public class GameClearScene extends BaseScene {
    private static final String TAG = GameClearScene.class.getSimpleName();

    private int song_id;
    public enum Layer {
        bg, touch, COUNT
    }

    public GameClearScene(int song_num, int score, int num_300, int num_100, int num_50, int num_x, int max_combo) {
        initLayers(SelectScene.Layer.COUNT);
        add(Layer.bg, new Sprite(R.mipmap.clearescenebg, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height));

        this.song_id = song_num;

        if(num_x == 0) {
            if(num_50 == 0) {
                add(Layer.bg, new Sprite(R.mipmap.ranks, 2.f, 2.f, 3.f, 3.f));
            }
            else{
                add(Layer.bg, new Sprite(R.mipmap.ranka, 2.f, 2.f, 3.f, 3.f));
            }
        }
        else if(num_x < 10){
            if(num_50 < 30) {
                add(Layer.bg, new Sprite(R.mipmap.rankb, 2.f, 2.f, 3.f, 3.f));
            }
            else{
                add(Layer.bg, new Sprite(R.mipmap.rankc, 2.f, 2.f, 3.f, 3.f));
            }
        }
        else if(num_x < 30){
            if(num_50 < 50) {
                add(Layer.bg, new Sprite(R.mipmap.rankd, 2.f, 2.f, 3.f, 3.f));
            }
            else{
                add(Layer.bg, new Sprite(R.mipmap.ranke, 2.f, 2.f, 3.f, 3.f));
            }
        }
        else{
            add(Layer.bg, new Sprite(R.mipmap.rankf, 2.f, 2.f, 3.f, 3.f));
        }

        add(Layer.bg, new Sprite(R.mipmap.scoretxt, Metrics.game_width - 9.f, 1.30f, 4.f, 0.7f));
        add(Layer.bg, new Sprite(R.mipmap.maxcombotxt, Metrics.game_width - 9.f, 2.30f, 4.f, 0.7f));

        add(Layer.bg, new Sprite(R.mipmap.score300, 2.f, 5.f, 1.5f, 1.5f));
        add(Layer.bg, new Sprite(R.mipmap.score100, 2.f, 7.f, 1.5f, 1.5f));
        add(Layer.bg, new Sprite(R.mipmap.score50, Metrics.game_width - 9.f, 5.f, 1.5f, 1.5f));
        add(Layer.bg, new Sprite(R.mipmap.scorex, Metrics.game_width - 9.f, 7.f, 1.5f, 1.5f));



        Score score1 = new Score(1.f, Metrics.game_width - 3.f, 7);
        score1.setScore(score);
        add(Layer.bg, score1);

        Score combo = new Score(2.f, Metrics.game_width - 3.f, 3);
        combo.setScore(max_combo);
        add(Layer.bg, combo);

        Score score300 = new Score(4.75f, 4.5f, 3);
        score300.setScore(num_300);
        add(Layer.bg, score300);

        Score score100 = new Score(6.75f, 4.5f, 3);
        score100.setScore(num_100);
        add(Layer.bg, score100);

        Score score50 = new Score(4.75f, Metrics.game_width - 6.5f, 3);
        score50.setScore(num_50);
        add(Layer.bg, score50);

        Score scorex = new Score(6.75f, Metrics.game_width - 6.5f, 3);
        scorex.setScore(num_x);
        add(Layer.bg, scorex);


        add(GameClearScene.Layer.touch, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button(
                R.mipmap.prev, 1.f, Metrics.game_height - 1.f, 1.f, 1.f, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Callback() {
            @Override
            public boolean onTouch(kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action action) {
                if (action == kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Action.pressed) {
                    getTopScene().popScene();
                    new MainScene(song_id).pushScene();
                }
                return false;
            }
        }));
        add(GameClearScene.Layer.touch, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button(
                R.mipmap.next, Metrics.game_width - 1.f, Metrics.game_height - 1.f, 1.f, 1.f, new kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Button.Callback() {
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
        return GameClearScene.Layer.touch.ordinal();
    }

}
