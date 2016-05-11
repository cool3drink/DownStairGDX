package com.icesum.downstair.ui.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

/**
 * Created by Hei on 10/5/2016.
 */
public class GameStateManager {
    private Stack<BaseState> states;

    public GameStateManager(){
        states = new Stack<BaseState>();
    }

    public void push(BaseState state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(BaseState state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
