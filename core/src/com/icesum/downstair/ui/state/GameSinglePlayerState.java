package com.icesum.downstair.ui.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.icesum.downstair.adapter.DownStairGame;
import com.icesum.downstair.bean.player.Player;
import com.icesum.downstair.bean.stair.BaseStair;
import com.icesum.downstair.bean.stair.NormalStair;

import java.util.Random;

/**
 * Created by Hei on 10/5/2016.
 */
public class GameSinglePlayerState extends BaseState {
    private static final int STAIR_INIT_Y_POSITION = 200; // DO WE NEED?
    private static final int STAIR_MAX_NUM_COUNT = 10;
    private static final int STAIR_SPACING_MIN = 70;
    private static final int STAIR_SPACING_RANGE = 100;

    private float level;
    private float speed;
    private boolean isStairCollide;

    private Player player;
    private IntArray stairsGenKey;
    private Array<BaseStair> stairsActive;

    public GameSinglePlayerState(GameStateManager gsm) {
        super(gsm);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);
        isStairCollide = false;
        level = 1;
        speed = 5;

        // Generate player
        player = new Player(50, 300, Player.FIRE_TYPE);

        // Generate stairs
        stairsGenKey = new IntArray(BaseStair.TOTAL_TYPE_COUNT);
        stairsActive = new Array<BaseStair>(STAIR_MAX_NUM_COUNT);
        initStairs(new int[]{7, 3});
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < (Gdx.graphics.getWidth() / 2)) {
                player.moveLeft(0);
            } else {
                player.moveRight(0);
            }
        }
    }

    @Override
    public void update(float dt) {
        if (stairsActive.size!=0) {
            if (stairsActive.get(0).getY() > DownStairGame.HEIGHT) {
                stairsActive.get(0).dispose();
                stairsActive.removeIndex(0);
                genStair();
            }
        }
        player.fall(dt);
        for (BaseStair stair: stairsActive) {
            stair.update(dt);
            if (stair.isCollide(player.getBounds())) {
                Gdx.app.log("GSPS", "isCollide");
                stair.collideMotion(player);
            }
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getX(), player.getY(), player.CHAR_WIDTH, player.CHAR_HEIGHT);
        renderStairs(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        player.dispose();
        for (BaseStair stair : stairsActive) {
            stair.dispose();
        }
    }

    private void initStairs(int[] genKey) {
        stairsActive.clear();
        setStairsGenKey(genKey);
        for (int i=0; i<STAIR_MAX_NUM_COUNT; i++) {
            genStair();
        }
    }

    private void setStairsGenKey(int[] genKey) {
        stairsGenKey.clear();
        int sum = 0;

        for (int i=0; i<BaseStair.TOTAL_TYPE_COUNT; i++) {
            // Compute the discrete cumulative density function
            // We will pick a random number from 0 to sum of all genKey[i] to choose the stair type to display
            // Sum of all genKey[i] == last element of stairsGenKey
            sum += genKey[i];
            stairsGenKey.add(sum);
        }
    }

    // Generate one stair
    private void genStair() {
        Random rand = new Random();
        int pick;

        pick = rand.nextInt(stairsGenKey.peek());
        Gdx.app.log("Pick", String.valueOf(pick));
        for (int j=0; j<BaseStair.TOTAL_TYPE_COUNT; j++) {    // j indicates the stair type
            if (pick<stairsGenKey.get(j)) {
                // Generate a stair with random spacing
                int stairSpacing = rand.nextInt(STAIR_SPACING_RANGE) + STAIR_SPACING_MIN;

                // Generate a stair according the location of previous stair if exists
                float genX = rand.nextInt(DownStairGame.WIDTH - NormalStair.WIDTH);
                float genY = (stairsActive.size!=0) ?
                        stairsActive.peek().getY() - (NormalStair.HEIGHT + stairSpacing) :
                        STAIR_INIT_Y_POSITION;
                stairsActive.add(
                        BaseStair.getStair(j, genX, genY, speed)
                );
                break;
            }
        }
    }

    private void renderStairs(SpriteBatch sb) {
        for (BaseStair stair: stairsActive) {
            sb.draw(stair.getTexture(), stair.getX(), stair.getY(), stair.WIDTH, stair.HEIGHT);
        }
    }
}
