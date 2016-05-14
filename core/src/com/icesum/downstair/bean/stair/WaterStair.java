package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 12/5/2016.
 */
public class WaterStair extends BaseElementStair {
    public WaterStair(float x, float y, float y_speed) {
        super(x, y, y_speed);
        mTexture = new Texture("bg_stair_water.png");
        type = BaseStair.TYPE_WATER;
    }
}
