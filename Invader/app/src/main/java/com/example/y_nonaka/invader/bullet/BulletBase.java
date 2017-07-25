package com.example.y_nonaka.invader.bullet;

import com.example.y_nonaka.invader.GameSprite;
import com.example.y_nonaka.invader.figther.FighterBase;
import com.example.y_nonaka.invader.scene.GameSceneBase;

/**
 * Created by y_nonaka on 2017/07/24.
 */

public abstract class BulletBase extends GameSprite {
    /**
     * この弾を打った機体を保持
     */
    FighterBase shooter = null;

    /**
     * 弾が有効な場合、true
     */
    protected boolean enable = true;

    public BulletBase(GameSceneBase scene, FighterBase shooter) {
        super(scene);
        this.shooter = shooter;
    }

    /**
     * 弾が有効ならtrue
     * @return
     */
    public boolean isEnable() {
        return enable;
    }

    @Override
    public void draw() {
        // 弾が有効でないなら描画しない
        if (!isEnable()) {
            return;
        }
        super.draw();
    }
}
