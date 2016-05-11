package com.icesum.downstair.ui.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icesum.downstair.adapter.DownStairGame;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 10/5/2016.
 */
public class GameSinglePlayerState extends BaseState {
    private Player player;

    public GameSinglePlayerState(GameStateManager gsm) {
        super(gsm);
        player = new Player(50, 300, Player.FIRE_TYPE);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < (Gdx.graphics.getWidth() / 2)) {
                player.moveLeft(0);
            } else {
                player.moveRight(0);
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getX(), player.getY());
        sb.end();
    }

    @Override
    public void dispose() {
        player.dispose();
    }
}
