package com.icesum.downstair.ui.state;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Hei on 10/5/2016.
 */
public abstract class BaseState {
    protected GameStateManager mGameStateManager;
    protected OrthographicCamera mCamera;
    protected Vector2 mMouse;

    protected BaseState(GameStateManager gsm){
        this.mGameStateManager = gsm;
        mCamera = new OrthographicCamera();
        //mCamera.setToOrtho(false, 240, 400);
        mMouse = new Vector2();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
