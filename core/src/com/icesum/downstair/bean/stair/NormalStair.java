package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Hei on 11/5/2016.
 */
public class NormalStair extends BaseStair {
    public NormalStair(int x, int y) {
        super(x, y);
        mTexture = new Texture("bg_stair_normal.png");
        mBounds = new Rectangle(x, y, mTexture.getWidth(), mTexture.getHeight());
    }
}