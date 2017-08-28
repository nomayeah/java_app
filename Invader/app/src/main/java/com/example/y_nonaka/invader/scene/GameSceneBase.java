package com.example.y_nonaka.invader.scene;

import com.eaglesakura.lib.android.game.input.MultiTouchInput;
import com.example.y_nonaka.invader.InvaderGame;
import com.eaglesakura.lib.android.game.graphics.ImageBase;
import com.eaglesakura.lib.android.game.graphics.gl11.SpriteManager;
import com.eaglesakura.lib.android.game.scene.SceneBase;

/**
 * Created by y_nonaka on 2017/07/13.
 */

public abstract class GameSceneBase extends SceneBase {
    /**
     * ゲームメインクラス
     */
    protected InvaderGame game = null;

    protected GameSceneBase(InvaderGame game) {
        this.game = game;
    }

    /**
     * マルチタッチ解析クラスを取得
     */

    public MultiTouchInput getMultiTouchInput() {
        return game.getMultiTouchInput();
    }

    /**
     * スプライトマネージャを取得する
     * @return
     */

    public SpriteManager getSpriteManager() {
        return game.getSpriteManager();
    }

    /**
     * drawableのIDを指定して画像を読み込む
     * @param drawableId
     * @return
     */

    public ImageBase loadImageDrawable(int drawableId) {
        return game.loadImageDrawable(drawableId);
    }
}
