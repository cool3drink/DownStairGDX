package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 13/5/2016.
 */
public class FireStair extends BaseStair {
    public FireStair(float x, float y, float y_speed) {
        super(x, y, y_speed);
        mTexture = new Texture("bg_stair_fire.png");
        mBounds = new Rectangle(x, y, WIDTH, HEIGHT);
        type = BaseStair.TYPE_FIRE;
    }

    @Override
    public void collideMotion(Player player) {
        //Gdx.app.log("WaterStair", "isCollide");
    }
}
