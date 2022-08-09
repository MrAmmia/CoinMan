package com.thebookofcode.coinman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class CoinMan extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture[] man;
    int manState = 0;
    int pause = 0;
    float velocity = 0;
    float gravity = 0.2f;
    int manY = 0;
    int manX = 0;
    Random coinRandom;
    Random bombRandom;
    ArrayList<Integer> bombXs = new ArrayList<>();
    ArrayList<Integer> bombYs = new ArrayList<>();
    ArrayList<Rectangle> bombRectangles = new ArrayList<>();
    ArrayList<Integer> coinXs = new ArrayList<>();
    ArrayList<Integer> coinYs = new ArrayList<>();
    ArrayList<Rectangle> coinRectangles = new ArrayList<>();
    Rectangle manRectangle;
    BitmapFont font;
    Texture coin;
    Texture bomb;
    Texture dizzyMan;
    int coinCount;
    int bombCount;
    int score = 0;
    int gameState;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        man = new Texture[4];
        dizzyMan = new Texture("dizzy-1.png");
        man[0] = new Texture("frame-1.png");
        man[1] = new Texture("frame-2.png");
        man[2] = new Texture("frame-3.png");
        man[3] = new Texture("frame-4.png");

        manX = Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2;
        manY = Gdx.graphics.getHeight() / 2;
        coin = new Texture("coin.png");
        bomb = new Texture("bomb.png");
        coinRandom = new Random();
        bombRandom = new Random();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);

    }

    public void makeCoin() {
        float height = coinRandom.nextFloat() * Gdx.graphics.getHeight();
        coinYs.add((int) height);
        coinXs.add(Gdx.graphics.getWidth());
    }

    public void makeBomb() {
        float height = coinRandom.nextFloat() * Gdx.graphics.getHeight();
        bombYs.add((int) height);
        bombXs.add(Gdx.graphics.getWidth());
    }
    /*
    public void reset(){
        manX = Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2;
        manY = Gdx.graphics.getHeight() / 2;
        score = 0;
        velocity = 0;
        coinYs.clear();
        coinXs.clear();
        bombYs.clear();
        bombXs.clear();
        coinRectangle.clear();
        bombRectangle.clear();
        coinCount = 0;
        bombCount = 0;
    }*/
    /*
    public void makeSprite(){
        if (coinCount < 5) {
            coinCount++;
        } else {
            coinCount = 0;
            makeCoin();
        }
        coinRectangle.clear();
        for (int i = 0; i < coinXs.size(); i++) {
            batch.draw(coin, coinXs.get(i), coinYs.get(i));
            coinXs.set(i, coinXs.get((i)) - 10);
            coinRectangle.add(new Rectangle(coinXs.get(i), coinYs.get(i), coin.getWidth(), coin.getHeight()));
        }

        // BOMBS
        if (bombCount < 10) {
            bombCount++;
        } else {
            bombCount = 0;
            makeBomb();
        }

        bombRectangle.clear();
        for (int i = 0; i < bombXs.size(); i++) {
            batch.draw(bomb, bombXs.get(i), bombYs.get(i));
            bombXs.set(i, bombXs.get((i)) - 20);
            bombRectangle.add(new Rectangle(bombXs.get(i), bombYs.get(i), bomb.getWidth(), bomb.getHeight()));
        }
    }


     */

    /*  @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (gameState == 1) {
            // Game is Live
            makeSprite();

            if (Gdx.input.justTouched()) {
                velocity = -15;
            }

            if (manState < 3) {
                manState++;
            } else {
                manState = 0;
            }

        if (pause < 8) {
            pause++;
        } else {
            pause = 0;
            if (manState < 3) {
                manState++;
            } else {
                manState = 0;
            }
        }
            velocity += gravity;
            manY -= velocity;

            if (manY <= 0) {
                manY = 0;
            }
            int check = Gdx.graphics.getHeight();
            int checkHeight = man[manState].getHeight();
            if (manY >= Gdx.graphics.getHeight()- man[manState].getHeight()) {
                manY = check - checkHeight;
            }
        } else if (gameState == 0) {
            // Waiting for game to start
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {
            // Game Over
            if (Gdx.input.justTouched()) {
                gameState = 1;
                reset();
            }
        }

        if(gameState == 2){
            batch.draw(dizzyMan, manX, manY);
        }else{
            batch.draw(man[manState], manX, manY);
        }


        manRectangle = new Rectangle(manX, manY, man[manState].getWidth(), man[manState].getHeight());
        for (int i = 0; i < coinRectangle.size(); i++) {
            if (Intersector.overlaps(manRectangle, coinRectangle.get(i))) {
                score++;
                coinRectangle.remove(i);
                coinXs.remove(i);
                coinYs.remove(i);
                break;
            }
        }
        for (int i = 0; i < bombRectangle.size(); i++) {
            if (Intersector.overlaps(manRectangle, bombRectangle.get(i))) {
                gameState = 2;
            }
        }

        font.draw(batch, String.valueOf(score), 100, 200);

        batch.end();
    }*/

@Override
public void render () {
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        if (gameState == 1) {
        // GAME IS LIVE
        // BOMB
        if (bombCount < 250) {
        bombCount++;
        } else {
        bombCount = 0;
        makeBomb();
        }

        bombRectangles.clear();
        for (int i=0;i < bombXs.size();i++) {
        batch.draw(bomb, bombXs.get(i), bombYs.get(i));
        bombXs.set(i, bombXs.get(i) - 16);
        bombRectangles.add(new Rectangle(bombXs.get(i), bombYs.get(i), bomb.getWidth(), bomb.getHeight()));
        }

        // COINS
        if (coinCount < 100) {
        coinCount++;
        } else {
        coinCount = 0;
        makeCoin();
        }

        coinRectangles.clear();
        for (int i=0;i < coinXs.size();i++) {
        batch.draw(coin, coinXs.get(i), coinYs.get(i));
        coinXs.set(i, coinXs.get(i) - 8);
        coinRectangles.add(new Rectangle(coinXs.get(i), coinYs.get(i), coin.getWidth(), coin.getHeight()));
        }

        if (Gdx.input.justTouched()) {
        velocity = -10;
        }

        if (pause < 4) {
        pause++;
        } else {
        pause = 0;
        if (manState < 3) {
        manState++;
        } else {
        manState = 0;
        }
        }

        velocity += gravity;
        manY -= velocity;

        if (manY <= 0) {
        manY = 0;
        }
        } else if (gameState == 0) {
        // Waitng to start
        if (Gdx.input.justTouched()) {
        gameState = 1;
        }
        } else if (gameState == 2) {
        // GAME OVER
        if (Gdx.input.justTouched()) {
        gameState = 1;
        manY = Gdx.graphics.getHeight() / 2;
        score = 0;
        velocity = 0;
        coinXs.clear();
        coinYs.clear();
        coinRectangles.clear();
        coinCount = 0;
        bombXs.clear();
        bombYs.clear();
        bombRectangles.clear();
        bombCount = 0;
        }
        }

        if (gameState == 2) {
        batch.draw(dizzyMan, Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2, manY);
        } else {
        batch.draw(man[manState], Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2, manY);
        }
        manRectangle = new Rectangle(Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2, manY, man[manState].getWidth(), man[manState].getHeight());

        for (int i=0; i < coinRectangles.size();i++) {
        if (Intersector.overlaps(manRectangle, coinRectangles.get(i))) {
        score++;

        coinRectangles.remove(i);
        coinXs.remove(i);
        coinYs.remove(i);
        break;
        }
        }

        for (int i=0; i < bombRectangles.size();i++) {
        if (Intersector.overlaps(manRectangle, bombRectangles.get(i))) {
        Gdx.app.log("Bomb!", "Collision!");
        gameState = 2;
        }
        }

        font.draw(batch, String.valueOf(score),100,200);

        batch.end();
        }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
