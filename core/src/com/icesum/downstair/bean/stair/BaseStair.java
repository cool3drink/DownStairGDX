package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Hei on 11/5/2016.
 */
public abstract class BaseStair {
    private Random rand;
    private Vector2 mPosition;
    private Vector2 mVelocity;
    protected Rectangle mBounds;
    protected Texture mTexture;

    public BaseStair(int x, int y) {
        rand = new Random();
        //mPosition = new Vector2(rand.nextInt(), y);
        mPosition = new Vector2(x,y);
        mVelocity = new Vector2(0,0);
    }

    public void update(float dt) {

    }

    public void dispose() {
        mTexture.dispose();
    }

    public float getX() {
        return mPosition.x;
    }

    public float getY() {
        return mPosition.y;
    }

    public float getWidth() {
        return mTexture.getWidth();
    }

    public float getHeight() {
        return mTexture.getHeight();
    }

    public Rectangle getBounds() {
        return mBounds;
    }

    public void updateBounds() {
        mBounds.setPosition(mPosition.x, mPosition.y);
    }

    public Texture getTexture() {
        return mTexture;
    }

}
