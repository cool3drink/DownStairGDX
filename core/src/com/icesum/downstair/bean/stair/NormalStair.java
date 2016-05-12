package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Hei on 11/5/2016.
 */
public class NormalStair extends BaseStair {
    public NormalStair(int x, int y, int y_speed) {
        super(x, y, y_speed);
        mTexture = new Texture("bg_stair_normal.png");
        mBounds = new Rectangle(x, y, WIDTH, HEIGHT);
        type = BaseStair.STAIR_TYPE_NORMAL;
    }
}
