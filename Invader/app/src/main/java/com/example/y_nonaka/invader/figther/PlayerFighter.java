package com.example.y_nonaka.invader.figther;

import com.eaglesakura.lib.android.game.math.Vector2;
import com.example.y_nonaka.invader.Define;
import com.example.y_nonaka.invader.R;
import com.example.y_nonaka.invader.bullet.PlayerBullet;
import com.example.y_nonaka.invader.input.AttackButton;
import com.example.y_nonaka.invader.input.JoyStick;
import com.example.y_nonaka.invader.scene.GameSceneBase;
import com.example.y_nonaka.invader.scene.PlayScene;

/**
 * Created by y_nonaka on 2017/07/13.
 */

public class PlayerFighter extends FighterBase {

    /**
     * 操作用のジョイスティック
     */
    JoyStick joyStick;

    /**
     * 攻撃ボタン
     */
    AttackButton shotButton;

    public PlayerFighter(GameSceneBase scene, JoyStick joyStick, AttackButton shotButton) {
        super(scene);
        this.joyStick = joyStick;
        this.shotButton = shotButton;

        /**
         * プライヤー画像の読み込み
         */
        sprite = loadSprite(R.drawable.player);

        /**
         * 初期位置を画面の下側中央にする
         */
        setPosition(Define.VIRTUAL_DISPLAY_WIDTH / 2, Define.VIRTUAL_DISPLAY_HEIGHT - 100);

    }

    /**
     * プレイヤー位置を更新する
     */
    void updatePosition() {
        // 移動させたい向きのベクトルを取得する
        Vector2 move = joyStick.getMoveVector();

        // 1フレームで最大５ピクセル移動するようにする
        move.mul(5.0f);

        // その方向へ移動させる
        offsetPosition(move.x, move.y);

        //! 位置をプレイエリア内に補正する
        correctPosition();
    }

    void fire() {
        PlayerBullet bullet = new PlayerBullet(scene, this);
        ((PlayScene) scene).addBullet(bullet);
    }

    @Override
    public void update() {
        updatePosition();
        if (shotButton.isAttack()) {
            fire();
        }
    }
}
