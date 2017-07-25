package com.example.y_nonaka.invader.figther.enemy;

import com.example.y_nonaka.invader.R;
import com.example.y_nonaka.invader.bullet.FrisbeeBullet;
import com.example.y_nonaka.invader.scene.GameSceneBase;
import com.example.y_nonaka.invader.scene.PlayScene;

/**
 * Created by y_nonaka on 2017/07/19.
 */

public class Frisbee extends EnemyFigtherBase {
    public Frisbee(GameSceneBase scene) {
        super(scene);
        sprite = loadSprite(R.drawable.enemy_00); // 敵画像読み込み
    }

    void fire() {
        FrisbeeBullet bullet = new FrisbeeBullet(scene, this);
        ((PlayScene) scene).addBullet(bullet);
    }

    @Override
    public void update() {
        // スーバークラスの処理を行わせる
        super.update();
        // 指定したフレームで処理を行わせる
        if (frameCount == 30 * 5) {
            // 150フレーム経過したら弾を撃つ、その後カウンターリセット
            fire(); // 毎フレーム　弾を撃つ
            resetFrameCount();
        }
    }
}
