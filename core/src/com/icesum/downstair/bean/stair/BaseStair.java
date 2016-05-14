package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icesum.downstair.bean.player.Player;

import java.util.Random;

/**
 * Created by Hei on 11/5/2016.
 */
public abstract class BaseStair {
    public static final int WIDTH = 120;
    public static final int HEIGHT = 20;

    public static final int TOTAL_TYPE_COUNT = 6;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_WATER = 1;
    public static final int TYPE_GRASS = 2;
    public static final int TYPE_FIRE = 3;
    public static final int TYPE_GO_LEFT = 4;
    public static final int TYPE_GO_RIGHT = 5;

    //private Random rand;
    protected Vector2 mPosition;
    protected Vector2 mVelocity;
    protected int type;
    protected Rectangle mBounds;
    protected Texture mTexture;

    public BaseStair(float x, float y, float y_speed) {
        mPosition = new Vector2(x,y);
        mVelocity = new Vector2(0,y_speed);
        mBounds = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void update(float dt) {
        mPosition.add(mVelocity);
        updateBounds();
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

    public void setXSpeed(float x_speed) {
        mVelocity.x = x_speed;
    }

    public void setYSpeed(float y_speed) {
        mVelocity.y = y_speed;
    }

    public Texture getTexture() {
        return mTexture;
    }

    /*****     Collision Checking     *****/
    public boolean isHit(Rectangle player) {
        return mBounds.overlaps(player);
    }

    /*****     Abstract  Methods     *****/
    public abstract void hitMotion(Player player);

    /*****     Static Methods     *****/
    public static BaseStair getStair(int stairType, float x, float y, float y_speed) {
        switch (stairType) {
            case TYPE_NORMAL:
                return new NormalStair(x, y, y_speed);
            case TYPE_WATER:
                return new WaterStair(x, y, y_speed);
            case TYPE_GRASS:
                return new GrassStair(x, y, y_speed);
            case TYPE_FIRE:
                return new FireStair(x, y, y_speed);
            case TYPE_GO_LEFT:
                return new GoLeftStair(x, y, y_speed);
            case TYPE_GO_RIGHT:
                return new GoRightStair(x, y, y_speed);
        }
        return null;
    }

}
