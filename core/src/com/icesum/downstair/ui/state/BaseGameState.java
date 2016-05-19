package com.icesum.downstair.ui.state;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.icesum.downstair.bean.player.Player;
import com.icesum.downstair.bean.stair.BaseStair;

/**
 * Created by Hei on 15/5/2016.
 */
public abstract class BaseGameState extends BaseState {
    protected static final int PLAYER_INIT_Y_POSITION = 700;

    protected static final int STAIR_INIT_Y_POSITION = 200;
    protected static final int STAIR_MAX_NUM_COUNT = 7;
    protected static final int STAIR_SPACING_MIN = 80;
    protected static final int STAIR_SPACING_RANGE = 120;

    protected static final int GAME_STATE_READY = 0;
    protected static final int GAME_STATE_PLAYING = 1;
    protected static final int GAME_STATE_GAMEOVER = 2;

    protected static final int INPUT_FROM_SCREEN = 0;
    protected static final int INPUT_FROM_ACC_METER = 1;

    // Game state
    protected int gameState;
    protected int level;
    protected float speed;
    protected int levelUpDistance;

    // Objects
    protected IntArray stairsGenKey;
    protected Array<BaseStair> stairsActive;
    protected Player player;

    // Setting
    protected Preferences prefs;
    protected int inputMethod;
    protected float accMeterSensitive;

    protected BaseGameState(GameStateManager gsm) {
        super(gsm);
    }
}
