package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 13/5/2016.
 */
public class FireStair extends BaseElementStair {
    public FireStair(float x, float y, float y_speed) {
        super(x, y, y_speed);
        mTexture = new Texture("bg_stair_fire.png");
        type = BaseStair.TYPE_FIRE;
    }
}
