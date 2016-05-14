package com.icesum.downstair.ui.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.icesum.downstair.adapter.DownStairGame;


/**
 * Created by Hei on 10/5/2016.
 */
public class HelloState extends BaseState {
    private Texture ust;
    public HelloState(GameStateManager gsm) {
        super(gsm);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);
        ust = new Texture("bg_ust.jpg");
    }

    @Override
    public void handleInput(float dt) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(ust, DownStairGame.WIDTH / 2 - 50, DownStairGame.HEIGHT / 2 - 50, 100, 100);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
