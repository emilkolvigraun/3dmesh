/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import AI.LowIQ;
import Common.Sphere;
import Objects.WorldObject;
import Common.SimpleFloor;
import Common.Figure;
import Objects.Animals.Wolf;
import Objects.SimpleObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.UBJsonReader;
import java.util.ArrayList;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class GameWorld {
    
    private ModelBuilder builder;
    private ArrayList<WorldObject> worldObjects;
    
    private float WIDTH;
    private float HEIGHT;
    
    
    public GameWorld() {
        WIDTH = 500f;
        HEIGHT = 600f;
        
        UBJsonReader jsonReader = new UBJsonReader();
        G3dModelLoader modelLoader = new G3dModelLoader(jsonReader);
        worldObjects = new ArrayList<>();
        builder = new ModelBuilder();
        
        
        SimpleFloor floor = new SimpleFloor(400f, 400f, new Color(189 / 255f, 195 / 255f, 199 / 255f, 255 / 255f), builder);
        Sphere ball = new Sphere(10, new Color(241 / 255f, 196 / 255f, 15 / 255f, 255 / 255f), builder);
        Figure wolf = new Figure(jsonReader, modelLoader, "res/wolf/Wolf.g3db");
        
        
        worldObjects.add(new SimpleObject(floor, 0, 0, 0));
        worldObjects.add(new SimpleObject(ball, -20, 50, -75).assignAI(new LowIQ()));
        worldObjects.add(new Wolf(wolf, 0, 0, 0));
    }
    
    public ArrayList<WorldObject> getWorldObjects(){
        return this.worldObjects;
    }
    
}
