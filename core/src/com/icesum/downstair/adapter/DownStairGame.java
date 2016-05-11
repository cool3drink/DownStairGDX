package com.icesum.downstair.adapter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icesum.downstair.ui.state.GameSinglePlayerState;
import com.icesum.downstair.ui.state.GameStateManager;
import com.icesum.downstair.ui.state.HomeState;

public class DownStairGame extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Down Stair";

    private GameStateManager gsm;
    private SpriteBatch batch;

    //private Music music;
	
	@Override
	public void create() {
        gsm = new GameStateManager();
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(1, 1, 1, 0);
        // TODO: change initial sate here
        gsm.push(new GameSinglePlayerState(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}

    @Override
    public void dispose() {
        super.dispose();

    }
}
