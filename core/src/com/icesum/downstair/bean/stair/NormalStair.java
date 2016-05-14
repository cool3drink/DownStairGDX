package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 11/5/2016.
 */
public class NormalStair extends BaseStair {
    public NormalStair(float x, float y, float y_speed) {
        super(x, y, y_speed);
        mTexture = new Texture("bg_stair_normal.png");
        type = BaseStair.TYPE_NORMAL;
    }

    @Override
    public void hitMotion(Player player) {
        // Stand On the stair
        // Second condition is for high speed mode. If we don't use it, player with go through the stair
        if (player.getY() > mBounds.getY() || (-player.getYSpeed())+mVelocity.y > HEIGHT) {
            player.setYSpeed(0);
            player.setY(mBounds.getY() + HEIGHT);
            player.updateBounds();
        }
    }
}
