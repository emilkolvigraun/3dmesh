/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Objects.WorldObject;
import Terrain.Terrain;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class GameModel extends ApplicationAdapter {

    private PerspectiveCamera camera;       // as things gets furhter away, they get smaller
    private ModelBatch batch;          // for polygons
    private GameWorld gameWorld;
    private Environment environment;        // rendering lightning etc.
    private Terrain terrain;

    @Override
    public void create() {
        camera = new PerspectiveCamera(75, getWidth(), getHeight());
        camera.position.set(10f, 50f, 10f);
        camera.lookAt(10f, 0f, 10f);
        camera.near = 0.1f;
        camera.far = 1000f;
        batch = new ModelBatch();
        gameWorld = new GameWorld();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.7f, 0.7f, 0.7f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, 2f, -2.5f, -0.2f));

        Gdx.input.setInputProcessor(new KeyManager(camera));

        terrain = new Terrain(100.0f);        
        terrain.generateTerrain();
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camera.update();
        batch.begin(camera);
        batch.render(terrain.getTerrain(), environment);
        //for (WorldObject obj : gameWorld.getWorldObjects()) {
        //     obj.update();
        //    batch.render(obj.getModelInstance(), environment);
        //}
        batch.end();
    }

    @Override
    public void dispose() {
        for (WorldObject obj : gameWorld.getWorldObjects()) {
            obj.getModel().dispose();
        }
        gameWorld.getWorldObjects().clear();
    }

    private int getWidth() {
        return Gdx.graphics.getWidth();
    }

    private int getHeight() {
        return Gdx.graphics.getHeight();
    }
}
