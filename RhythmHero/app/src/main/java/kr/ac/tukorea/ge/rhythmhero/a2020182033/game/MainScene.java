package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;


import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;


public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();

    public static float song_play_time;

    public enum Layer {
        hit_mark, slide_mark, spin_mark, score_mark, COUNT
    }

    public MainScene() {
        song_play_time = 0.0f;

        initLayers(Layer.COUNT);
        add(Layer.hit_mark, HitMark.get(1, 1, 5.f, 5.f, 0.f, 5.f));
        add(Layer.slide_mark, SlideMark.get(1, 1, 1.1f, 1.1f, 3.3f, 3.3f, 1.f, 3.f, 6.f, 3));
        add(Layer.spin_mark, SpinMark.get(0.f, 12.f));
    }

    @Override
    public void update(long elapsedNanos) {
        song_play_time += elapsedNanos / 1_000_000_000f; // 이후 곡 재생 시간으로 변경
        super.update(elapsedNanos);
    }
}