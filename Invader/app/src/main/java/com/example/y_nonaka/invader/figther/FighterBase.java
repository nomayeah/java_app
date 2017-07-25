package com.example.y_nonaka.invader.figther;

import android.graphics.Rect;

import com.example.y_nonaka.invader.GameSprite;
import com.example.y_nonaka.invader.bullet.BulletBase;
import com.example.y_nonaka.invader.scene.GameSceneBase;

/**
 * Created by y_nonaka on 2017/07/13.
 */

public abstract class FighterBase extends GameSprite {

    /**
     * 戦闘機のヒットポイント
     * デフォルトは１
     */
    int hp = 1;

    public FighterBase(GameSceneBase scene){
        super(scene);
    }

    /**
     * ２つのスプライトが衝突している場合、trueを返す
     */
    public boolean isIntersect(GameSprite other) {
        if (isDead()) {
            // 撃墜済みの戦闘機にあたり判定は発生しない
            return false;
        }
        Rect mySpriteArea = getSprite().getDstRect();
        Rect otherSpriteArea = other.getSprite().getDstRect();
        return Rect.intersects(mySpriteArea, otherSpriteArea);
    }
    /**
     * 弾が当たったらこのメソッドに通知される
     */
    public void onDamage(BulletBase bullet) {
        --hp;
    }

    /**
     * この機体が撃墜されていたらtrueを返す
     */
    public boolean isDead() {
        return hp <= 0;
    }
}
