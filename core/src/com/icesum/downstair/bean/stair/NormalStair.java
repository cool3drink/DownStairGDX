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
        mBounds = new Rectangle(x, y, WIDTH, HEIGHT);
        type = BaseStair.TYPE_NORMAL;
    }

    @Override
    public void collideMotion(Player player) {
        // Stand On the stair
        if (player.getY() > mBounds.getY()) {
            //Gdx.app.log("NormalStair", "onAbove");
            player.setYSpeed(mVelocity.y);
            player.setY(mBounds.getY()+HEIGHT);
            player.updateBounds();
        }
    }
}
