package com.icesum.downstair.ui.state;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icesum.downstair.adapter.DownStairGame;

/**
 * Created by Hei on 10/5/2016.
 */
public class HomeState extends BaseState {
    private Texture background;
    private Texture fire, water, grass;
    private Texture title;

    public HomeState(GameStateManager gsm) {
        super(gsm);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);
        background = new Texture("bg.png");
        fire = new Texture("bg_char_fire.png");
        water = new Texture("bg_char_water.png");
        grass = new Texture("bg_char_grass.png");
        title = new Texture("bg_title.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(background, 0, 0, DownStairGame.WIDTH, DownStairGame.HEIGHT);
        sb.draw(title, 0, DownStairGame.HEIGHT/3*2, DownStairGame.WIDTH, DownStairGame.HEIGHT/5);
        sb.draw(water, 0 ,  mCamera.position.y/2, DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        sb.draw(fire, DownStairGame.WIDTH/3,mCamera.position.y/2,DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        sb.draw(grass, DownStairGame.WIDTH/3*2, mCamera.position.y/2,DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        fire.dispose();
        water.dispose();
        grass.dispose();

    }
}
