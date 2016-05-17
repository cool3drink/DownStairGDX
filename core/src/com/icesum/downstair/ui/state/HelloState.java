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
    private static final int LOGO_WIDTH_UST = 84;
    private static final int LOGO_HEIGHT_UST = 120;

    private Texture ust;

    public HelloState(GameStateManager gsm) {
        super(gsm);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);
        ust = new Texture("bg_ust.jpg");
    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            mGameStateManager.set(new HomeState(mGameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(    ust, (DownStairGame.WIDTH-LOGO_WIDTH_UST)/2,
                    (DownStairGame.HEIGHT-LOGO_HEIGHT_UST)/2,
                    LOGO_WIDTH_UST, LOGO_HEIGHT_UST);
        sb.end();
    }

    @Override
    public void dispose() {
        ust.dispose();
    }
}
