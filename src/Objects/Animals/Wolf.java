/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects.Animals;

import Common.TextureModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import Objects.WorldObject;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class Wolf implements WorldObject {
    private ModelInstance instance;
    private TextureModel textureModel;
    private AnimationController controller;
    private Model model;
    private float x;
    private float y;
    
    public Wolf(TextureModel figure, float x, float y, float z){
        model = figure.getModel();
        instance = new ModelInstance(model);
        instance.transform.translate(x, y, z);
        
        controller = new AnimationController(instance); 
        setAnimation("Wolf_Skeleton|Wolf_Idle_", -1);
                
        this.x = x;
        this.y = z;
    }
    
        @Override
    public void update() {
        controller.update(Gdx.graphics.getDeltaTime());
    }
    
    @Override
    public ModelInstance getModelInstance() {
        return this.instance;
    }

    @Override
    public Model getModel() {
        return this.model;
    }
    
    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    } 

    @Override
    public Wolf setAnimation(String path, int loop) {
        controller.setAnimation(path, loop, new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {
                Gdx.app.log("INFO","Animation Ended");
            }
        });  
        return this;
    }

    @Override
    public TextureModel getTextureModel() {
        return textureModel;
    }
}
