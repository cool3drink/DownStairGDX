package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 13/5/2016.
 */
public class GoLeftStair extends BaseStair {
    public static final int DRIVE_SPEED = -5;

    public GoLeftStair(float x, float y, float y_speed) {
        super(x, y, y_speed);
        mTexture = new Texture("bg_stair_go_left.png");
        type = BaseStair.TYPE_GO_LEFT;
    }

    @Override
    public void hitMotion(Player player) {
        // Stand On the stair
        // Second condition is for high speed mode. If we don't use it, player with go through the stair
        if (player.getY() > mBounds.getY() || (-player.getYSpeed())+mVelocity.y > HEIGHT) {
            player.setYSpeed(0);
            player.addXSpeed(DRIVE_SPEED);
            player.setY(mBounds.getY() + HEIGHT);
            player.updateBounds();
        }
    }
}
