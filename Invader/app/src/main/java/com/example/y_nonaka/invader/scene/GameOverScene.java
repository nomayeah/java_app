package com.example.y_nonaka.invader.scene;

import com.eaglesakura.lib.android.game.graphics.Color;
import com.eaglesakura.lib.android.game.graphics.Sprite;
import com.eaglesakura.lib.android.game.scene.SceneBase;
import com.eaglesakura.lib.android.game.scene.SceneManager;
import com.example.y_nonaka.invader.Define;
import com.example.y_nonaka.invader.InvaderGame;
import com.example.y_nonaka.invader.R;

/**
 * Created by y_nonaka on 2017/07/25.
 */

public class GameOverScene extends GameSceneBase {
    /**
     * ゲームオーバー用画像
     */
    Sprite gameoverImage = null;

    public GameOverScene(InvaderGame game) {
        super(game);
    }

    @Override
    public void onSceneStart(SceneManager manager, SceneBase before) {
        // ゲームオーバー画像を読み込む
        gameoverImage = new Sprite(loadImageDrawable(R.drawable.gameover));

        // 画像を真ん中へ移動させる
        gameoverImage.setSpritePosition(Define.VIRTUAL_DISPLAY_WIDTH / 2, Define.VIRTUAL_DISPLAY_HEIGHT / 2);
    }

    @Override
    public void onSceneExit(SceneManager manager, SceneBase next) {

    }
    @Override
    public void onFrameBegin(SceneManager manager) {

    }

    @Override
    public void onFrameDraw(SceneManager manager) {
        // 画面を黒背景にする
        int backgroundColor = Color.BLACK;

        // 背景色で塗りつぶす
        getSpriteManager().draw(gameoverImage);

        // ゲームオーバー用画像を描画する
        getSpriteManager().draw(gameoverImage);
    }
    @Override
    public void onFrameEnd(SceneManager manager) {

    }

    @Override
    public void onGamePause(SceneManager manager) {

    }

    @Override
    public void onGameResume(SceneManager manager) {

    }
}
