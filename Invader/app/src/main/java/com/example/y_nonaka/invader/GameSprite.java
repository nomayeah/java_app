package com.example.y_nonaka.invader;

import android.graphics.Rect;

import com.eaglesakura.lib.android.game.graphics.gl11.SpriteManager;
import com.eaglesakura.lib.android.game.math.Vector2;
import com.eaglesakura.lib.android.game.util.GameUtil;
import com.example.y_nonaka.invader.scene.GameSceneBase;
import com.eaglesakura.lib.android.game.graphics.ImageBase;
import com.eaglesakura.lib.android.game.graphics.Sprite;

/**
 * Created by y_nonaka on 2017/07/13.
 */

public abstract class GameSprite {
    /**
     * 描画に利用する画像
     */
    protected Sprite sprite = null;

    /**
     * 描画位置
     */
    protected Vector2 position = new Vector2();

    /**
     * このスプライトを保持しているシーン
     */
    protected GameSceneBase scene = null;

    protected GameSprite(GameSceneBase scene) {
        this.scene = scene;
    }

    /**
     * 描画対象のスプライトを取得する
     * @return
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * drawableの画像からスプライトを作成する
     * @param drawableId
     * @return
     */
    protected Sprite loadSprite( int drawableId ) {
        ImageBase image = scene.loadImageDrawable(drawableId);
        Sprite result = new Sprite(image);
        return result;
    }

    /**
     * スプライト描画クラスを取得する
     * @return
     */
    protected SpriteManager getSpriteManager() {
        return scene.getSpriteManager();
    }

    /**
     * 毎フレームの更新処理を行う
     */
    public abstract void update();

    /**
     * 描画を行う。
     */
    public void draw() {
        if (sprite != null) {
            getSpriteManager().draw(sprite);
        }
    }

    /**
     * 描画位置を指定する。
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        position.set(x, y);

        // スプライトに現在位置を伝える
        sprite.setSpritePosition((int) position.x, (int) position.y);
    }

    /**
     * X座標を取得する
     * 画面左端が０となる
     * @return
     */
    public float getPositionX() {
        return position.x;
    }

    /**
     * Y座標を取得する
     * 画面上端が０となる
     * @return
     */
    public float getPostionY() {
        return position.y;
    }

    /**
     * 現在の位置から指定ピクセル数だけ位置をずらす
     * 絶対位置でなく相対位置で制御したい場合に利用する
     * @param x
     * @param y
     */
    public void offsetPosition(float x, float y) {
        setPosition(getPositionX() + x, getPostionY() + y); //　指定ピクセル位置をずらす
    }

    /**
     * ゲームエリアからはみ出していたらfalseを返し、プレイエリア内に収まっていればtrueを返す
     * @return
     */

    public boolean isPlayArea() {
        if (getPostionY() < 0) {
            return false; // 画面の上方向にはみ出している
        }
        if (getPostionY() > Define.VIRTUAL_DISPLAY_HEIGHT) {
            return false;
        }
        if (getPositionX() < Define.PLAY_AREA_LEFT) {
            return false;
        }
        if (getPositionX() > Define.PLAY_AREA_RIGHT) {
            return false;
        }

        return true;
    }

    /**
     * プレイエリアからはみ出していたら、プレイエリア内に引き戻す
     */
    public void correctPosition() {
        if (!isPlayArea()) {
            // x座標は最小PLAY_AREA_LEFT?最大PLAY_AREA_RIGHTに収まらなければならない
            // y座標は最小0?最大VIRTUAL_DISPLAY_HEIGHTに収まらないといけない
            setPosition(GameUtil.minmax(Define.PLAY_AREA_LEFT, Define.PLAY_AREA_RIGHT, getPositionX()), GameUtil.minmax(0, Define.VIRTUAL_DISPLAY_HEIGHT, getPostionY()));
        }
    }

    /**
     * ディスプレイ範囲内に存在する場合はtrueを返す
     * @return
     */
    public boolean isAppeaedDisplay() {
        Rect mySpriteArea = sprite.getDstRect();
        return mySpriteArea.intersects(0, 0, Define.VIRTUAL_DISPLAY_WIDTH, Define.VIRTUAL_DISPLAY_HEIGHT);
    }
}
