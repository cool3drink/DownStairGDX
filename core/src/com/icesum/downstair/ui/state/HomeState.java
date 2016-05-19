package com.icesum.downstair.ui.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.icesum.downstair.adapter.DownStairGame;
import com.icesum.downstair.bean.button.TextureButton;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 10/5/2016.
 */
public class HomeState extends BaseState {
    private Texture title;
    private Texture background;
    private Texture charWaterT, charFireT, charGrassT;
    private TextureButton charWater, charFire, charGrass;
    Preferences prefs;

    public HomeState(GameStateManager gsm) {
        super(gsm);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);
        background = new Texture("bg.png");
        title = new Texture("bg_title.png");
        charWaterT = new Texture("bg_char_water.png");
        charFireT = new Texture("bg_char_fire.png");
        charGrassT = new Texture("bg_char_grass.png");
        charWater = new TextureButton(charWaterT, 0, DownStairGame.HEIGHT/4, DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        charFire = new TextureButton(charFireT, DownStairGame.WIDTH/3,DownStairGame.HEIGHT/4,DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        charGrass = new TextureButton(charGrassT, DownStairGame.WIDTH/3*2, DownStairGame.HEIGHT/4,DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        prefs = Gdx.app.getPreferences("data_player");
    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            if (charWater.isClicked(Gdx.input.getX(), DownStairGame.HEIGHT - Gdx.input.getY())) {
                prefs.putInteger("type", Player.TYPE_WATER);
                prefs.flush();
                mGameStateManager.set(new GameStateSinglePlayer(mGameStateManager));
            } else if (charFire.isClicked(Gdx.input.getX(), DownStairGame.HEIGHT - Gdx.input.getY())) {
                prefs.putInteger("type", Player.TYPE_FIRE);
                prefs.flush();
                mGameStateManager.set(new GameStateSinglePlayer(mGameStateManager));
            } else if (charGrass.isClicked(Gdx.input.getX(), DownStairGame.HEIGHT - Gdx.input.getY())) {
                prefs.putInteger("type", Player.TYPE_GRASS);
                prefs.flush();
                mGameStateManager.set(new GameStateSinglePlayer(mGameStateManager));
            }
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
        sb.draw(background, 0, 0, DownStairGame.WIDTH, DownStairGame.HEIGHT);
        sb.draw(title, 0, DownStairGame.HEIGHT/3*2, DownStairGame.WIDTH, DownStairGame.HEIGHT/5);
        charFire.render(sb);
        charWater.render(sb);
        charGrass.render(sb);
        //sb.draw(charWater, 0 ,  mCamera.position.y/2, DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        // /sb.draw(charFire, DownStairGame.WIDTH/3,mCamera.position.y/2,DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        //sb.draw(charGrass, DownStairGame.WIDTH/3*2, mCamera.position.y/2,DownStairGame.WIDTH/4,DownStairGame.WIDTH/4);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
