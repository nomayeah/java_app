package com.example.y_nonaka.invader.bullet;

import com.example.y_nonaka.invader.R;
import com.example.y_nonaka.invader.figther.FighterBase;
import com.example.y_nonaka.invader.scene.GameSceneBase;
import com.example.y_nonaka.invader.scene.PlayScene;

/**
 * Created by y_nonaka on 2017/07/24.
 */

public class PlayerBullet extends BulletBase {
    public PlayerBullet(GameSceneBase scene, FighterBase shooter) {
        super(scene, shooter);
        sprite = loadSprite(R.drawable.bullet_player);
        setPosition(shooter.getPositionX(), shooter.getPostionY()); // 位置を発射した戦闘機に合わせる
    }

    @Override
    public void update() {
        offsetPosition(0, -10); // 上に向かって移動

        if (((PlayScene) scene).intersectsEnemy(this) != null) {
            enable = false;
        }
    }
}
