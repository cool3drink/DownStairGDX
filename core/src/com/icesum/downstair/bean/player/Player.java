package com.icesum.downstair.bean.player;

/**
 * Created by Hei on 10/5/2016.
 */
public class Player extends PlayerChar {
    public static final int CHAR_WIDTH = 50;
    public static final int CHAR_HEIGHT = 50;

    public static final int TYPE_FIRE = 0;
    public static final int TYPE_WATER = 1;
    public static final int TYPE_GRASS = 2;

    public static final int MAX_LIFE = 3;

    //private PlayerChar mPlayerChar;
    private int mCharType;
    private int mScore;
    private int mLife;

    public Player(int x, int y, int char_type) {
        super(x, y);
        mCharType = char_type;
        mScore = 0;
        mLife = MAX_LIFE;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public void resetScore() {
        mScore = 0;
    }

    public void addLife() {
        if (mLife < MAX_LIFE) {
            mLife++;
        }
    }

    public void subLife() {
        mLife--;
    }

    public boolean isDead() {
        return isFallOut() || mLife <= 0;
    }

    public void dead() {
        mLife = 0;
        setXSpeed(0);
        setYSpeed(15);
    }

    public int getLife() {
        return mLife;
    }

    public int getType() {
        return mCharType;
    }
}
