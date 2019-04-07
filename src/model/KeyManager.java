/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class KeyManager implements InputProcessor {

    private int dragX, dragY;
    private int rotX, rotY;
    private PerspectiveCamera camera;
    private boolean rotating = false;
    private boolean dragging = false;
    private boolean shift = false;

    public KeyManager(PerspectiveCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.SHIFT_LEFT) {
            shift = true;
        }
        return true;

    }

    @Override
    public boolean keyUp(int i) {
        if (i == Input.Keys.SHIFT_LEFT) {
            shift = false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int i2, int btn) {
        dragX = x;
        dragY = y;
        rotX = x;
        rotY = y;

        if (btn == 1) {
            rotating = true;
        }
        if (btn == 0) {
            dragging = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int btn) {
        if (btn == 1) {
            rotating = false;
        }
        if (btn == 0) {
            dragging = false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int i2) {
        if (dragging) {
            float dX = (float) (x - dragX) / (float) Gdx.graphics.getWidth();
            float dY = (float) (dragY - y) / (float) Gdx.graphics.getHeight();
            dragX = x;
            dragY = y;
            if (shift) {
                camera.position.add(-dX * 1000f, 0f, dY * 1000f);
            } else {
                camera.position.add(-dX * 80f, 0f, dY * 80f);
            }
        }
        if (rotating) {

            float dX = (float) (x - rotX) / (float) Gdx.graphics.getWidth();
            float dY = (float) (rotY - y) / (float) Gdx.graphics.getHeight();
            rotX = x;
            rotY = y;

            if (shift) {
                camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(1f, 0f, 0f), dY * 80f);
            } else {
                camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), dX * 80f);
            }
        }

        camera.update();
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        float delta;
        if (camera.position.y <= 40f) {
            delta = 1f;
        } else if (camera.position.y <= 100f) {
            delta = 5f;
        } else {
            delta = 30f;
        }

        camera.position.add(0f, i * delta, 0f);
        camera.update();
        return true;
    }

}
