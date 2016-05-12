package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Hei on 11/5/2016.
 */
public abstract class BaseStair {
    // TODO: scale the texture
    public final static int WIDTH = 150;
    public final static int HEIGHT = 30;

    public static final int STAIR_TYPE_NORMAL = 0;

    //private Random rand;
    private Vector2 mPosition;
    private Vector2 mVelocity;
    protected int type;
    protected Rectangle mBounds;
    protected Texture mTexture;

    public BaseStair(int x, int y, int y_speed) {
        //rand = new Random();
        //mPosition = new Vector2(rand.nextInt(), y);
        mPosition = new Vector2(x,y);
        mVelocity = new Vector2(0,y_speed);
    }

    public void update(float dt) {
        mPosition.add(mVelocity);
        mBounds.setPosition(mPosition.x, mPosition.y);
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

    public int getType() {
        return type;
    }

    public void updateBounds() {
        mBounds.setPosition(mPosition.x, mPosition.y);
    }

    public void setXSpeed(int x_speed) {
        mVelocity.x = x_speed;
    }

    public void setYSpeed(int y_speed) {
        mVelocity.y = y_speed;
    }

    public Texture getTexture() {
        return mTexture;
    }

}
