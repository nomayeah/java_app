package com.example.y_nonaka.invader.scene;

import com.eaglesakura.lib.android.game.graphics.Color;
import com.example.y_nonaka.invader.Define;
import com.example.y_nonaka.invader.InvaderGame;
import com.eaglesakura.lib.android.game.scene.SceneBase;
import com.eaglesakura.lib.android.game.scene.SceneManager;
import com.example.y_nonaka.invader.bullet.BulletBase;
import com.example.y_nonaka.invader.figther.PlayerFighter;
import com.example.y_nonaka.invader.figther.enemy.EnemyFigtherBase;
import com.example.y_nonaka.invader.figther.enemy.Frisbee;
import com.example.y_nonaka.invader.input.AttackButton;
import com.example.y_nonaka.invader.input.JoyStick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by y_nonaka on 2017/07/13.
 */

public class PlayScene extends GameSceneBase {

    /**
     * プレイヤー機
     */
    PlayerFighter player = null;

    /**
     * ジョイスティック管理
     * @param game
     */
    JoyStick joyStick = null;

    /**
     * 発射ボタン
     */
    AttackButton shotButton = null;

    /**
     * 敵一覧
     */
    List<EnemyFigtherBase> enemies = new ArrayList<EnemyFigtherBase>();

    /**
     * 弾一覧
     */
    List<BulletBase> bullets = new ArrayList<BulletBase>();

    public PlayScene(InvaderGame game) {
        super(game);
    }

    /**
     * プレイヤー機のアクセス
     * @return
     */
    public PlayerFighter getPlayer() {
        return player;
    }

    /**
     *  弾をステージへ追加する
     * @param bullet
     */
    public void addBullet(BulletBase bullet) {
        bullets.add(bullet);
    }

    public EnemyFigtherBase intersectsEnemy(BulletBase bullet) {
        for (EnemyFigtherBase enemy : enemies) {
            if (enemy.isIntersect(bullet)) {
                enemy.onDamage(bullet);
                return enemy;
            }
        }
        return null;
    }

    @Override
    public void onSceneStart(SceneManager manager, SceneBase before) {
        joyStick = new JoyStick(this); // ジョイスティックを生成
        shotButton = new AttackButton(this); // 攻撃ボタンを生成する
        player = new PlayerFighter(this, joyStick, shotButton); // プレイヤーを生成
        // 敵を全て生成する
        initializeEnemy();
    }

    /**
     * 敵の初期値を行う
     */
    protected void initializeEnemy() {
        // 敵の配置テーブルを作成する
        final EnemyFigtherBase[][] enemyTable = {
            // 後列
            {
                new Frisbee(this), new Frisbee(this), new Frisbee(this), new Frisbee(this)
            },
            {
                new Frisbee(this), new Frisbee(this), new Frisbee(this), new Frisbee(this)
            },
            {
                new Frisbee(this), new Frisbee(this), new Frisbee(this), new Frisbee(this)
            },
        };

        // プレイエリアの左右から幅を取得する
        final int PLAY_AREA_WIDTH = Define.PLAY_AREA_RIGHT - Define.PLAY_AREA_LEFT;
        int y = 100;

        // 全ラインをfor文で見る
        for (int line = 0; line < enemyTable.length; ++line) {
            final int lineEnemyNum = enemyTable[line].length;

            // １ラインの横方向をfor文で見る
            for (int index = 0; index < lineEnemyNum; ++index) {
                //　配置対象の敵をテーブルから取り出す
                EnemyFigtherBase enemy = enemyTable[line][index];

                // 配置する敵が存在する場合、位置を設定する
                if (enemy != null) {
                    // プレイエリアを等分して、現在のindexに割り当てる
                    int x = PLAY_AREA_WIDTH / lineEnemyNum * index;
                    x += PLAY_AREA_WIDTH / lineEnemyNum / 2;
                    enemy.setPosition(Define.PLAY_AREA_LEFT + x, y);

                    // 敵リストへ登録する
                    enemies .add(enemy);
                }
            }
            // Y位置を１ライン前線へ下げる
            y += 100;
        }
    }

    @Override
    public void onSceneExit(SceneManager manager, SceneBase next) {

    }

    @Override
    public void onFrameBegin(SceneManager manager) {
        // プレイヤーが死んでいなければ更新処理を行う
        if (!player.isDead()) {
            player.update();
        }
        shotButton.update(); //攻撃ボタンの状態を更新する
        joyStick.update();

        //　敵を全て更新する
        {
            Iterator<EnemyFigtherBase> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                EnemyFigtherBase enemy = iterator.next();
                enemy.update();
                if (enemy.isDead() || !enemy.isAppeaedDisplay()) {
                    iterator.remove();
                }
            }
        }

        // 弾を全て更新する
        {
            Iterator<BulletBase> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                BulletBase bullet = iterator.next();
                bullet.update();
                if (!bullet.isEnable() || !bullet.isAppeaedDisplay()) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void onFrameDraw(SceneManager manager) {
        getSpriteManager().clear(0, 0, 64, 255);
        // プレイヤーが死んでいなければ描画処理を行う
        if (!player.isDead()) {
            player.draw(); //　プレイヤーを描画する
        }
        joyStick.draw();

        // 敵を全て描画する
        for (EnemyFigtherBase enemy : enemies) {
            enemy.draw();
        }

        // 弾を全て描画する
        for (BulletBase bullet : bullets) {
            bullet.draw();
        }

        // 画面のプレイエリア外を塗りつぶす
        {
            // 左端
            getSpriteManager().fillRect(
                //起点の座標
                0,0,

                //幅高さ
                Define.PLAY_AREA_LEFT, Define.VIRTUAL_DISPLAY_HEIGHT,

                //　描画する色
                Color.toColorRGBA(255, 255, 255, 255));

            //　右端
            getSpriteManager().fillRect(
                //起点
                Define.PLAY_AREA_RIGHT, 0,

                // 幅高さ
                Define.VIRTUAL_DISPLAY_WIDTH - Define.PLAY_AREA_RIGHT, Define.VIRTUAL_DISPLAY_HEIGHT,

                // 描画する色
                Color.toColorRGBA(255, 255, 255, 255));
        }

        joyStick.draw(); //ジョイスティックを表示
        shotButton.draw(); // 攻撃ボタンを表示
    }

    /**
     * ゲームオーバー条件を満たしたらtrueを返す
     * @param
     */
    public boolean isGameover() {
        return player.isDead(); // プレイヤーが撃墜されたらゲームオーバー
    }

    /**
     * 条件を満たしたらシーンを切り替える
     */
    @Override
    public void onFrameEnd(SceneManager manager) {
        if (isGameover()) {
            // ゲームオーバーを条件を満たしたから、ゲームオーバーシーンへ切り替える
            GameOverScene nextScene = new GameOverScene(game);
            manager.setNextScene(nextScene);
        }
    }

    @Override
    public void onGamePause(SceneManager manager) {

    }

    @Override
    public void onGameResume(SceneManager manager) {

    }
}
