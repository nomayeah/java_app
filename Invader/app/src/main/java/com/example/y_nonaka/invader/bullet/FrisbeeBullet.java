package com.example.y_nonaka.invader.bullet;

import com.example.y_nonaka.invader.R;
import com.example.y_nonaka.invader.figther.FighterBase;
import com.example.y_nonaka.invader.scene.GameSceneBase;
import com.example.y_nonaka.invader.scene.PlayScene;

/**
 * Created by y_nonaka on 2017/07/24.
 */

public class FrisbeeBullet extends BulletBase {
    public FrisbeeBullet(GameSceneBase scene, FighterBase shooter) {
        super(scene, shooter);

        sprite = loadSprite(R.drawable.bullet_enemy); // 弾の画像を保持する
        setPosition(shooter.getPositionX(), shooter.getPostionY()); // 発射した弾を敵に合わせる
    }

    @Override
    public void update() {
        offsetPosition(0, 10); //　下に向かって移動

        FighterBase player = ((PlayScene) scene).getPlayer();
        // プレイヤーと弾が衝突していたらダメージ処理を行う
        if (player.isIntersect(this)) {
            enable = false;
            player.onDamage(this);
        }
    }
}
