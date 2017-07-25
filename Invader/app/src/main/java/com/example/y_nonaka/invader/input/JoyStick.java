package com.example.y_nonaka.invader.input;

import android.graphics.Rect;

import com.eaglesakura.lib.android.game.math.Vector2;
import com.example.y_nonaka.invader.Define;
import com.example.y_nonaka.invader.GameSprite;
import com.example.y_nonaka.invader.R;
import com.example.y_nonaka.invader.scene.GameSceneBase;
import com.eaglesakura.lib.android.game.graphics.Sprite;
import com.eaglesakura.lib.android.game.input.MultiTouchInput;
import com.eaglesakura.lib.android.game.input.MultiTouchInput.TouchPoint;

import java.util.Vector;

/**
 * Created by y_nonaka on 2017/07/16.
 */

public class JoyStick extends GameSprite {

    /**
     * タッチに反応するエリア
     */
    Rect touchArea = new Rect(0, 500, 200, Define.VIRTUAL_DISPLAY_HEIGHT);

    /**
     * ジョイスティック取っ手のスプライト
     */
    Sprite stick = null;

    public JoyStick(GameSceneBase scene) {
        super(scene);

        //ジョイスティックの土台を読み込む
        sprite = loadSprite(R.drawable.ui_stickbase);
        stick = loadSprite(R.drawable.ui_stick); //　スティック本体の読み込み
        setPosition(70, Define.VIRTUAL_DISPLAY_HEIGHT - 120); // スプライトの位置を調整
    }

    /**
     * ジョイスティックが示す方向を取得する
     * ベクトルは正規化されているため、必ず１．０になる
     * @return
     */
    public Vector2 getMoveVector() {
        //　位置の差　＝　ジョイスティックの向けている方向ベクトルを取得する
        Vector2 result = new Vector2(stick.getDstCenterX() - sprite.getDstCenterX(), stick.getDstCenterY() - sprite.getDstCenterY());

        result.normalize();

        return result;
    }

    /**
     * ジョイスティックとっての描画を行うメソッド
     */
    void drawStick() {
        getSpriteManager().draw(stick); // スティック本体の描画を行う
    }

    /**
     *  毎フレームの更新処理を行う
     */
    @Override
    public void update() {
        MultiTouchInput touch = scene.getMultiTouchInput();
        TouchPoint touchPoint = touch.getEnableTouchPoint(touchArea); // 指定したエリアに指があるか調べる

        if(touchPoint != null) {
            stick.setSpritePosition(touchPoint.getCurrentX(), touchPoint.getCurrentY()); // 指が置かれているため、その位置へジョイスティックを移動
        } else {
            stick.setSpritePosition((int) position.x, (int) position.y); //　指が置かれていないため、ジョイスティックを土台と同じ位置に戻す
        }
    }

    /**
     * オブジェクトの描画
     */
    @Override
    public void draw() {
        super.draw();
        drawStick();
    }
}
