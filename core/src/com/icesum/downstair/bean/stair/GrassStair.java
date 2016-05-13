package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 13/5/2016.
 */
public class GrassStair extends BaseStair {
    private static final float DAMAGE_DELAY_TIME = 1.5f; // seconds
    private boolean isFirstTimeCollide;

    public GrassStair(float x, float y, float y_speed) {
        super(x, y, y_speed);
        mTexture = new Texture("bg_stair_grass.png");
        mBounds = new Rectangle(x, y, WIDTH, HEIGHT);
        type = BaseStair.TYPE_GRASS;
        isFirstTimeCollide = true;
    }

    @Override
    public void collideMotion(Player player) {
        // Stand On the stair
        // Second condition is for high speed mode. If we don't use it, player with go through the stair
        if (player.getY() > mBounds.getY() || (-player.getYSpeed())+mVelocity.y > HEIGHT) {
            switch (player.getType()) {
                case Player.FIRE_TYPE:
                    // Disappear
                    mTexture = new Texture("bg_stair_transparent.png");
                    break;
                case Player.WATER_TYPE:
                    if (isFirstTimeCollide) {
                        player.subLife();
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                isFirstTimeCollide = true;
                            }
                        }, DAMAGE_DELAY_TIME);
                    }
                    player.setY(mBounds.getY() + HEIGHT);
                    player.updateBounds();
                    break;
                default:
                    if (isFirstTimeCollide) {
                        player.addLife();
                    }
                    player.setY(mBounds.getY() + HEIGHT);
                    player.updateBounds();
            }
            player.setYSpeed(0);
            isFirstTimeCollide = false;
        }
    }
}