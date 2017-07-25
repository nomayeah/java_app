package com.example.y_nonaka.invader.figther.enemy;

import com.example.y_nonaka.invader.figther.FighterBase;
import com.example.y_nonaka.invader.scene.GameSceneBase;

/**
 * Created by y_nonaka on 2017/07/19.
 */

public abstract class EnemyFigtherBase extends FighterBase {
    /**
     * 生成されてからのフレームを記録する
     */
    protected int frameCount = 0;

    public EnemyFigtherBase(GameSceneBase scene) {
        super(scene);
    }

    /**
     * フレーム数のカウンターを０に戻す
     */
    protected void resetFrameCount() {
        frameCount = 0;
    }

    @Override
    public void update() {
        ++frameCount;
    }

    @Override
    public void draw() {
        if (isDead()) {
            return;
        }
        super.draw();
    }
}
