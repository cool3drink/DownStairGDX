package com.icesum.downstair.ui.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.icesum.downstair.adapter.DownStairGame;
import com.icesum.downstair.bean.player.Player;
import com.icesum.downstair.bean.stair.BaseStair;
import com.icesum.downstair.bean.stair.NormalStair;

import java.util.Queue;
import java.util.Random;

/**
 * Created by Hei on 10/5/2016.
 */
public class GameSinglePlayerState extends BaseState {
    private static final int STAIR_INIT_SPEED = 20;
    private static final int STAIR_INIT_Y_POSITION = DownStairGame.HEIGHT / 2; // DO WE NEED?
    private static final int STAIR_MAX_NUM_COUNT = 5;
    private static final int STAIR_SPACING_MIN = 200;
    private static final int STAIR_SPACING_RANGE = 200;
    private static final int STAIR_MAX_TYPE_COUNT = 1;


    private float level;

    private Player player;
    private Array<BaseStair[]> stairs;
    private IntArray stairsGenKey;
    //private Queue<int> stairsActive;

    public GameSinglePlayerState(GameStateManager gsm) {
        super(gsm);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);

        // Generate player
        player = new Player(50, 300, Player.FIRE_TYPE);

        // Generate stairs
        stairsGenKey = new IntArray(STAIR_MAX_TYPE_COUNT);
        stairs = new Array<BaseStair[]>(STAIR_MAX_TYPE_COUNT);
        genStairs(new int[]{100});
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
        handleInput();
        player.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getX(), player.getY());
        renderStairs(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        player.dispose();
    }

    private void genStairs(int[] genKey) {
        int sum = 0;

        stairsGenKey.clear();
        stairs.clear();

        for (int i=0; i<STAIR_MAX_TYPE_COUNT; i++) {
            // Compute the discrete cumulative density function
            // We will pick a random number from 0 to sum of all genKey[i] to choose the stair type to display
            // Sum of all genKey[i] == last element of stairsGenKey
            sum += genKey[i];
            stairsGenKey.add(sum);

            // If the probability of a stair type appears is 0 (genKey[i] == 0), then set to null
            stairs.insert(i, (genKey[i]==0) ? null : genStairsArray(i));
        }
    }

    private BaseStair[] genStairsArray(int stairType) {
        Random rand = new Random();
        BaseStair[] sa = new BaseStair[STAIR_MAX_NUM_COUNT];

        switch (stairType) {
            case BaseStair.STAIR_TYPE_NORMAL:
                for (int i=0; i<STAIR_MAX_NUM_COUNT; i++) {
                    // Generate a stair with random spacing
                    int stairSpacing = rand.nextInt(STAIR_SPACING_RANGE) + STAIR_SPACING_MIN;

                    // Generate a stair with random x position
                    sa[i] = new NormalStair(
                            rand.nextInt(DownStairGame.WIDTH - NormalStair.WIDTH),
                            STAIR_INIT_Y_POSITION - i*(NormalStair.HEIGHT + stairSpacing),
                            STAIR_INIT_SPEED
                    );
                }
                break;

        }

        return sa;
    }

    private void renderStairs(SpriteBatch sb) {
        Random rand = new Random();
        int pick;

        for (int i=0; i<STAIR_MAX_NUM_COUNT; i++) {
            pick = rand.nextInt(stairsGenKey.peek());
            for (int j=0; j<STAIR_MAX_TYPE_COUNT; j++) {
                if (pick<stairsGenKey.get(j)) {
                    BaseStair target = stairs.get(j)[i];
                    sb.draw(target.getTexture(), target.getX(), target.getY());
                }
            }
        }
    }

    private void updateStairs(float dt) {
        //for (int i=0; i<)
    }
}
