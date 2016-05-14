package com.icesum.downstair.ui.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
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
public class GameStateSinglePlayer extends BaseGameState {
    private static final int PLAYER_INIT_Y_POSITION = 700;

    private static final int STAIR_INIT_Y_POSITION = 200;
    private static final int STAIR_MAX_NUM_COUNT = 7;
    private static final int STAIR_SPACING_MIN = 80;
    private static final int STAIR_SPACING_RANGE = 120;

    private static final int GAME_STATE_READY = 0;
    private static final int GAME_STATE_PLAYING = 1;
    private static final int GAME_STATE_GAMEOVER = 2;

    private static final int INPUT_FROM_SCREEN = 0;
    private static final int INPUT_FROM_ACC_METER = 1;

    // Game state
    //private boolean isGameOver;
    private int gameState;
    private int level;
    private float speed;
    private float distance;
    private int levelUpDistance;

    // Font
    private BitmapFont font;

    // Objects
    private Player player;
    private IntArray stairsGenKey;
    private Array<BaseStair> stairsActive;

    // Setting
    private int inputMethod;
    private float accMeterSensitive;

    public GameStateSinglePlayer(GameStateManager gsm) {
        super(gsm);
        mCamera.setToOrtho(false, DownStairGame.WIDTH, DownStairGame.HEIGHT);

        // Game state
        gameState = GAME_STATE_READY;
        level = 1;
        levelUpDistance = 100;
        speed = 3;
        distance = 0;

        // Font
        font = new BitmapFont(Gdx.files.internal("Segoe_Print.fnt"));

        // Generate player
        int playerInitXPosition = (int) (Math.random()*(DownStairGame.WIDTH-Player.CHAR_WIDTH));
        player = new Player(playerInitXPosition, PLAYER_INIT_Y_POSITION, Player.TYPE_FIRE);

        // Generate stairs
        stairsGenKey = new IntArray(BaseStair.TOTAL_TYPE_COUNT);
        stairsActive = new Array<BaseStair>(STAIR_MAX_NUM_COUNT);
        initStairs(new int[]{8,1,1,1,2,2}); // Size of array must be larger than TOTAL_TYPE_COUNT

        // Ssettings
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
            inputMethod = INPUT_FROM_ACC_METER;
            accMeterSensitive = 0.3f;
        } else {
            inputMethod = INPUT_FROM_SCREEN;
        }
    }

    private void initStairs(int[] genKey) {
        stairsActive.clear();
        setStairsGenKey(genKey);
        for (int i=0; i<STAIR_MAX_NUM_COUNT; i++) {
            genStair();
        }
    }

    @Override
    public void handleInput(float dt) {
        switch (inputMethod) {
            case INPUT_FROM_ACC_METER:
                handleInputAccMeter(dt);
                break;
            default:
                handleInputScreen(dt);
        }
    }

    private void handleInputScreen(float dt) {
        switch (gameState) {
            case GAME_STATE_READY:
                break;
            case GAME_STATE_PLAYING:
                if (Gdx.input.isTouched()) {
                    float acc = Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
                    player.moveRight(acc / 1000);
                } else {
                    player.stand();
                }
                break;
            case GAME_STATE_GAMEOVER:
                handleStateTransfer();
                break;
        }
    }

    private void handleInputAccMeter(float dt) {
        switch (gameState) {
            case GAME_STATE_READY:
                break;
            case GAME_STATE_PLAYING:
                float acc = Gdx.input.getAccelerometerX();
                if (Math.abs(acc) > accMeterSensitive) {
                    player.moveLeft(acc * dt);
                } else {
                    player.stand();
                }
                break;
            case GAME_STATE_GAMEOVER:
                handleStateTransfer();
                break;
        }
    }

    private void handleStateTransfer() {
        if (Gdx.input.isTouched()) {
            Gdx.graphics.setContinuousRendering(true);
            mGameStateManager.set(new GameStateSinglePlayer(mGameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput(dt);

        switch (gameState) {
            case GAME_STATE_READY:
                gameState = GAME_STATE_PLAYING;
                break;
            case GAME_STATE_PLAYING:
                updateLevel(dt);
                updateStairs(dt);
                updatePlayer(dt);
                break;
            case GAME_STATE_GAMEOVER:
                if (!player.isFallOut()) {
                    player.fall(dt);
                    player.update(dt);
                } else {
                    Gdx.graphics.setContinuousRendering(false);
                }
                break;
        }
    }

    private void updatePlayer(float dt) {
        if (player.isDead()) {
            player.dead();
            gameState = GAME_STATE_GAMEOVER;
        }
        player.fall(dt);
        player.update(dt);
    }

    private void updateLevel(float dt) {
        distance += dt * speed;
        //player.setScore((int) distance/10);
        //Gdx.app.log("Distance:", String.valueOf(distance));
        if (distance > levelUpDistance) {
            speed += 1.5f;
            level++;
            levelUpDistance = levelUpDistance*(level+1);
            Gdx.app.log("Speed:", String.valueOf(speed));
            for (BaseStair stair : stairsActive) {
                stair.setYSpeed(speed);
            }
        }
    }

    private void updateStairs(float dt) {
        // Dispose stairs out of screen
        if (stairsActive.get(0).getY() > DownStairGame.HEIGHT) {
            stairsActive.get(0).dispose();
            stairsActive.removeIndex(0);
            genStair();
        }
        for (BaseStair stair : stairsActive) {
            stair.update(dt);
        }
        for (BaseStair stair : stairsActive) {
            if (stair.isHit(player.getBounds())) {
                stair.hitMotion(player);
                break;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        // Render stairs
        renderStairs(sb);
        // Render score
        renderGameState(sb);
        // Render player
        sb.draw(player.getTexture(), player.getX(), player.getY(), Player.CHAR_WIDTH, Player.CHAR_HEIGHT);
        sb.end();
    }

    private void renderStairs(SpriteBatch sb) {
        for (BaseStair stair: stairsActive) {
            sb.draw(stair.getTexture(), stair.getX(), stair.getY(), stair.WIDTH, stair.HEIGHT);
        }
    }

    private void renderGameState(SpriteBatch sb) {
        font.setColor(Color.RED);
        font.getData().setScale(0.6f);
        font.draw(sb, String.valueOf((int) distance), 450, 680, 0, Align.right, false);
        font.draw(sb, "Lv.  "+String.valueOf(level), 450, 730, 0, Align.right, false);
        font.draw(sb, "Life  "+String.valueOf(player.getLife()), 450, 780, 0, Align.right, false);
    }

    @Override
    public void dispose() {
        player.dispose();
        for (BaseStair stair : stairsActive) {
            stair.dispose();
        }
    }

    /*****     Generating Stairs     *****/
    // Set generation key of stairs
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

    // Patterns of generating stairs
    //private void genStairScript() {}

    // Generate one stair
    private void genStair() {
        Random rand = new Random();
        int pick;

        pick = rand.nextInt(stairsGenKey.peek());
        //Gdx.app.log("Pick", String.valueOf(pick));
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


}
