package com.example.y_nonaka.invader.scene;

import com.eaglesakura.lib.android.game.graphics.Color;
import com.eaglesakura.lib.android.game.input.MultiTouchInput;
import com.eaglesakura.lib.android.game.scene.SceneBase;
import com.eaglesakura.lib.android.game.scene.SceneManager;
import com.example.y_nonaka.invader.Define;
import com.example.y_nonaka.invader.Font;
import com.example.y_nonaka.invader.InvaderGame;

/**
 * Created by y_nonaka on 2017/07/26.
 */

public class TitleScene extends GameSceneBase {
    /**
     * 文字表示用のフォント
     */
    Font font = null;

    public TitleScene(InvaderGame game) {
        super(game);
    }

    @Override
    public void onSceneStart(SceneManager manager, SceneBase before) {
        // フォント生成する
        font = new Font(game);
    }

    @Override
    public void onSceneExit(SceneManager manager, SceneBase next) {}

    @Override
    public void onFrameBegin(SceneManager manager) {}

    @Override
    public void onFrameDraw(SceneManager manager) {
        // ひとまずわかりやすいように適当な色をつけてる
        getSpriteManager().clear(0, 0, 64, 255);

        // "INVADER"の文字を中央に描画する
        {
            float fontScale = 2.0f;
            font.drawCeneter("INVADER", Define.VIRTUAL_DISPLAY_WIDTH / 2, Define.VIRTUAL_DISPLAY_HEIGHT / 2, fontScale);
        }

        // 自分の名前を画面中央下側に描画する
        {
            float fontScale = 0.5f;
            String name = "@noma_yeah";
            font.drawCeneter(name, Define.VIRTUAL_DISPLAY_WIDTH / 2, Define.VIRTUAL_DISPLAY_HEIGHT - 50, fontScale);
        }
    }

    @Override
    public void onFrameEnd(SceneManager manager) {
        MultiTouchInput input = game.getMultiTouchInput();
        if (input.isTouchOnce()) {
            // 画面のどこかをタップしたら次の画面へ遷移
            PlayScene nextScene = new PlayScene(game);
            manager.setNextScene(nextScene);
        }
    }

    @Override
    public void onGamePause(SceneManager manager) {}

    @Override
    public void onGameResume(SceneManager manager) {}
}
