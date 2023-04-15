package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;

public class HitMark extends Mark implements IRecyclable{

    private Sprite mark;
    private Paint circle1Paint;
    private Paint circle2Paint;

    float x, y;
    protected RectF dstRect = new RectF();
    private static final int[][] resIds = {{
        R.mipmap.blue1, R.mipmap.blue2, R.mipmap.blue3, R.mipmap.blue4, R.mipmap.blue5,
            R.mipmap.blue6, R.mipmap.blue7, R.mipmap.blue8, R.mipmap.blue9, R.mipmap.blue10,
            R.mipmap.blue11, R.mipmap.blue12, R.mipmap.blue13, R.mipmap.blue14, R.mipmap.blue15,
    },{
        R.mipmap.green1, R.mipmap.green2, R.mipmap.green3, R.mipmap.green4, R.mipmap.green5,
            R.mipmap.green6, R.mipmap.green7, R.mipmap.green8, R.mipmap.green9, R.mipmap.green10,
            R.mipmap.green11, R.mipmap.green12, R.mipmap.green13, R.mipmap.green14, R.mipmap.green15,
    },{
        R.mipmap.purple1, R.mipmap.purple2, R.mipmap.purple3, R.mipmap.purple4, R.mipmap.purple5,
            R.mipmap.purple6, R.mipmap.purple7, R.mipmap.purple8, R.mipmap.purple9, R.mipmap.purple10,
            R.mipmap.purple11, R.mipmap.purple12, R.mipmap.purple13, R.mipmap.purple14, R.mipmap.purple15,
    },{
            R.mipmap.red1, R.mipmap.red2, R.mipmap.red3, R.mipmap.red4, R.mipmap.red5,
            R.mipmap.red6, R.mipmap.red7, R.mipmap.red8, R.mipmap.red9, R.mipmap.red10,
            R.mipmap.red11, R.mipmap.red12, R.mipmap.red13, R.mipmap.red14, R.mipmap.red15,
    },{
            R.mipmap.yellow1, R.mipmap.yellow2, R.mipmap.yellow3, R.mipmap.yellow4, R.mipmap.yellow5,
            R.mipmap.yellow6, R.mipmap.yellow7, R.mipmap.yellow8, R.mipmap.yellow9, R.mipmap.yellow10,
            R.mipmap.yellow11, R.mipmap.yellow12, R.mipmap.yellow13, R.mipmap.yellow14, R.mipmap.yellow15,
    }
    };

    public static final float SIZE = 1.0f;
    private static final float TIME_SIZE = 0.5f;
    public static final float RADIUS = SIZE / 2;
    protected RectF collisionRect = new RectF();

    protected static float touch_timing;

    public HitMark(int num, int color, float x, float y, float appeared_timing, float touch_timing) {
        super(appeared_timing);
        mark = new Sprite(resIds[color][num - 1], x, y, SIZE, SIZE);
        this.touch_timing = touch_timing;
        this.x = x;
        this.y = y;
        circle1Paint = new Paint();
        circle1Paint.setStyle(Paint.Style.STROKE);
        circle1Paint.setAntiAlias(true);
        circle1Paint.setStyle(Paint.Style.STROKE);
        circle1Paint.setColor(Color.BLACK);
        circle1Paint.setStrokeWidth(0.1f);

        this.circle2Paint = new Paint();

        switch(color) {
            case 0:
                this.circle2Paint.setColor(Color.parseColor("#8CC6E7"));
                //R.color.mark_blue
                break;
            case 1:
                this.circle2Paint.setColor(Color.parseColor("#6BCE9E"));
                //R.color.mark_green
                break;
            case 2:
                this.circle2Paint.setColor(Color.parseColor("#A7A2DE"));
                //R.color.mark_purple
                break;
            case 3:
                this.circle2Paint.setColor(Color.parseColor("#F18D80"));
                //R.color.mark_red
                break;
            case 4:
                this.circle2Paint.setColor(Color.parseColor("#FCC43D"));
                //R.color.mark_yellow
                break;
        }

        circle2Paint.setStyle(Paint.Style.STROKE);
        circle2Paint.setAntiAlias(true);
        circle2Paint.setStrokeWidth(0.1f);

    }

    private void init(int level) {

    }

    @Override
    public void update() {
        super.update();

        if (touch_timing - MainScene.song_play_time < -1.f) {
            BaseScene.getTopScene().remove(MainScene.Layer.hit_mark, this);
            BaseScene.getTopScene().add(MainScene.Layer.score_mark, new ScoreMark(x, y, 0));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mark.draw(canvas);

        canvas.save();
        float circle_size = RADIUS + (touch_timing - MainScene.song_play_time) * TIME_SIZE;
        if(circle_size >= RADIUS) {
            canvas.drawCircle(x, y, circle_size, circle2Paint);
        }

        canvas.drawCircle(x, y, RADIUS, circle1Paint);
        canvas.restore();
    }

    @Override
    public void onRecycle() {
    }

}
