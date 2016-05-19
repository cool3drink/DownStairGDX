package com.icesum.downstair.bean.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Hei on 15/5/2016.
 */
public class TextureButton {
    private Sprite skin;

    public TextureButton(Texture texture, float x, float y, float width, float height) {
        skin = new Sprite(texture);
        skin.setPosition(x, y);
        skin.setSize(width, height);
    }

    public void render(SpriteBatch batch) {
        //isClicked(input_x, input_y);
        skin.draw(batch);
    }

    public boolean isClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                // the button was clicked, perform an action
                //System.out.println("Button clicked !");
                return true;
            }
        }
        return false;
    }

    public float convertToPercents_width (float p) {
        return Gdx.graphics.getWidth()*p/100;
    }

    public float convertToPercents_height (float p) {
        return Gdx.graphics.getHeight()*p/100;
    }
}
