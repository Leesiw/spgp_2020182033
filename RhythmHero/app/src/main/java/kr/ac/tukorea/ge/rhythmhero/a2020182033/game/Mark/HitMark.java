package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.HitMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene.MainScene;

public class HitMark extends Mark implements IRecyclable, IBoxCollidable {

    private Sprite mark;
    private static Paint circle1Paint = new Paint();
    private static Paint circle2Paint = new Paint();

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
    private static final float TIME_SIZE = 0.0005f;
    public static final float RADIUS = SIZE / 2;
    protected RectF collisionRect = new RectF();

    protected float touch_timing;

    public HitMark() {
        super(0);
        circle1Paint.setStyle(Paint.Style.STROKE);
        circle1Paint.setAntiAlias(true);
        circle1Paint.setStyle(Paint.Style.STROKE);
        circle1Paint.setColor(Color.BLACK);
        circle1Paint.setStrokeWidth(0.1f);

        circle2Paint.setStyle(Paint.Style.STROKE);
        circle2Paint.setAntiAlias(true);
        circle2Paint.setStrokeWidth(0.1f);
    }

    public static HitMark get(int num, int color, float x, float y, int appeared_timing, float touch_timing) {
        HitMark mark = (HitMark) RecycleBin.get(HitMark.class);
        if (mark == null) {
            mark = new HitMark();
        }
        mark.init(num, color, x, y, appeared_timing, touch_timing);
        return mark;
    }

    public static HitMark get(HitMarkData data) {
        HitMark mark = (HitMark) RecycleBin.get(HitMark.class);
        if (mark == null) {
            mark = new HitMark();
        }
        mark.init(data.getNum(), data.getColor(), data.getX(), data.getY(), data.appeared_timing, data.getTouch_timing());
        return mark;
    }

    private void init(int num, int color, float x, float y, int appeared_timing, float touch_timing) {
        this.appeared_timing = appeared_timing;
        mark = null;
        mark = new Sprite(resIds[color][num - 1], x, y, SIZE, SIZE);
        this.touch_timing = touch_timing;
        this.x = x;
        this.y = y;

        float half_width = SIZE / 2;
        float half_height = SIZE / 2;
        dstRect.set(x - half_width, y - half_height, x + half_width, y + half_height);

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
    }

    public void isTouched(){
        float touched_time = Math.abs(touch_timing - MainScene.song_play_time);

        if (touched_time > 1000) {
            BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(x, y, 0));
            MainScene.score.add(0);
        }
        else if(touched_time > 500f){
            BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(x, y, 1));
            MainScene.score.add(50);
        }
        else if(touched_time > 200f){
            BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(x, y, 2));
            MainScene.score.add(100);
        }
        else{
            BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(x, y, 3));
            MainScene.score.add(300);
        }
        BaseScene.getTopScene().remove(MainScene.Layer.hit_mark, this);
    }

    public int isTouchedInSlide(){
        float touched_time = Math.abs(touch_timing - MainScene.song_play_time);

        if (touched_time > 1000) {
            return 0;
        }
        else if(touched_time > 500){
            return 5;
        }
        else if(touched_time > 200){
            return 10;
        }
        else{
            return 30;
        }
    }

    @Override
    public void update() {
        super.update();

        if (touch_timing - MainScene.song_play_time < -1.f) {
            BaseScene.getTopScene().remove(MainScene.Layer.hit_mark, this);
            BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(x, y, 0));
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

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

}
