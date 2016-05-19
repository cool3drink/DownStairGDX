package com.icesum.downstair.bean.stair;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.icesum.downstair.bean.player.Player;

/**
 * Created by Hei on 14/5/2016.
 */
public abstract class BaseElementStair extends BaseStair {
    private static final int  TOTAL_ELEMENT_TYPE_COUNT = 3;
    private static final float DAMAGE_DELAY_TIME = 1.5f; // seconds
    protected boolean isFirstTimeCollide;
    protected boolean isDestroyed;

    public BaseElementStair(float x, float y, float y_speed) {
        super(x, y, y_speed);
        isFirstTimeCollide = true;
        isDestroyed = false;
    }

    @Override
    public void hitMotion(Player player) {
        // Stand On the stair
        // Second condition is for high speed mode. If we don't use it, player with go through the stair
        if (!isDestroyed && player.getY() > mBounds.getY() || (-player.getYSpeed()) + mVelocity.y > HEIGHT) {
            if (player.getType() == (type%TOTAL_ELEMENT_TYPE_COUNT)) {  // Same type
                if (isFirstTimeCollide) {
                    player.addLife();
                }
                player.setY(mBounds.getY() + HEIGHT);
                player.updateBounds();
            } else if (player.getType()+1 == type) {                            // Stair counter player
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
            } else {                                                            // Player counter stair
                // Disappear
                mTexture = new Texture("bg_stair_transparent.png");
                isDestroyed = true;
            }
            player.setYSpeed(0);
            isFirstTimeCollide = false;
        }
    }
}