package com.example.y_nonaka.invader.input;

import com.eaglesakura.lib.android.game.graphics.Sprite;
import com.eaglesakura.lib.android.game.input.MultiTouchInput.TouchPoint;
import com.example.y_nonaka.invader.Define;
import com.example.y_nonaka.invader.GameSprite;
import com.example.y_nonaka.invader.R;
import com.example.y_nonaka.invader.scene.GameSceneBase;

/**
 * Created by y_nonaka on 2017/07/19.
 */

public class AttackButton extends GameSprite {
    /**
     * 押してない時のボタン画像
     */
    Sprite release = null;

    /**
     * 押している時のボタン画像
     */
    Sprite press = null;


    /**
     * 攻撃する瞬間の場合、true
     */
    boolean attack = false;

    public AttackButton(GameSceneBase scene) {
        super(scene);

        release = loadSprite(R.drawable.ui_shot); // 離している
        press = loadSprite(R.drawable.ui_shot_p); // 押している

        sprite = release; // まずは離している時の画像を表示したい

        // 適当な位置で表示する
        setPosition(Define.VIRTUAL_DISPLAY_WIDTH - 50, Define.VIRTUAL_DISPLAY_HEIGHT - 120);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);

        // release/press のスプライト位置も更新する
        release.setSpritePosition((int) x, (int) y);
        press.setSpritePosition((int) x, (int) y);
    }

    /**
     * 攻撃する場合、trueを返す
     * @return
     */
    public boolean isAttack() {
        return attack;
    }

    @Override
    public void update() {
        // 離れているか、離した瞬間のタッチ座標があるか確認する
        TouchPoint touchPoint = sprite.findIntersectReleaseOnce(scene.getMultiTouchInput());

        if (touchPoint != null) {
            // 該当ポイントがあったので、「離した瞬間」ならば攻撃タイミングとして認識する
            if (touchPoint.isReleaseOnce()) {
                sprite = release; //離しているときの画像
                attack = true; // 攻撃するタイミング
            } else {
                sprite = press; // 押しているときの画像
                attack = false; // 攻撃するタイミングではない
            }
        } else {
            sprite = release; //　離しているときの画像
            attack = false; // 攻撃するタイミングではない
        }
    }
}
