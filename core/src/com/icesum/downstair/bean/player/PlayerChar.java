package com.icesum.downstair.bean.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icesum.downstair.adapter.DownStairGame;

/**
 * Created by Hei on 10/5/2016.
 */
public class PlayerChar {
    private static final int GRAVITY = -20;

    private Vector2 mPosition;
    private Vector2 mVelocity;
    private Rectangle mBounds;
    //private Animation moveAnimation;
    private Texture mTexture;
    //private Sound mSound;

    public PlayerChar(int x, int y) {
        mPosition = new Vector2(x,y);
        mVelocity = new Vector2(0,0);
        mTexture = new Texture("bg_char_fire.png");
        mBounds = new Rectangle(x, y, Player.CHAR_WIDTH, Player.CHAR_HEIGHT);
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

    public float getXSpeed() {
        return mVelocity.x;
    }

    public float getYSpeed() {
        return mVelocity.y;
    }

    public float getWidth() {
        return Player.CHAR_WIDTH;
    }

    public float getHeight() {
        return Player.CHAR_HEIGHT;
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

    public void setVelocity(float vx, float vy) {
        mVelocity.set(vx, vy);
    }

    public void setXSpeed(float vx) {
        mVelocity.x = vx;
    }

    public void addXSpeed(float vx) {
        mVelocity.x += vx;
    }

    public void setYSpeed(float vy) {
        mVelocity.y = vy;
    }

    public void addYSpeed(float vy) {
        mVelocity.y += vy;
    }

    public void setX(float x) {
        mPosition.x = x;
    }

    public void setY(float y) {
        mPosition.y = y;
    }

    public void update(float dt) {
        mPosition.add(mVelocity.x, mVelocity.y);
        if (mPosition.x < 0) {
            mPosition.x = 0;
        }
        if (mPosition.x + Player.CHAR_WIDTH > DownStairGame.WIDTH) {
            mPosition.x = DownStairGame.WIDTH - Player.CHAR_WIDTH;
        }
        updateBounds();
    }

    /*****     Motion     *****/
    public void moveLeft(float dt) {
        setXSpeed(-10);
    }

    public void moveRight(float dt) {
        setXSpeed(10);
    }

    public void fall(float dt) {
        mVelocity.add(0, GRAVITY*dt);
        //mVelocity.scl(dt);
        //mPosition.add(0, mVelocity.y*dt);
        //mVelocity.scl(1 / dt);
    }
}
