package com.icesum.downstair.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.icesum.downstair.adapter.DownStairGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = DownStairGame.WIDTH;
        config.height = DownStairGame.HEIGHT;
        config.title = DownStairGame.TITLE;
		new LwjglApplication(new DownStairGame(), config);
	}
}
