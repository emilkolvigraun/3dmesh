/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import AI.Brain;
import Common.TextureModel;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class SimpleObject implements WorldObject {
    
    private ModelInstance groundInstance;
    private TextureModel textureModel;
    private Model model;
    private float x;
    private float y;
    private Brain brain;
    
    public SimpleObject(TextureModel model, float x, float y, float z){
        this.model = model.getModel();
        textureModel = model;
        groundInstance = new ModelInstance(this.model, x, y, z);
        
        this.x = x;
        this.y = z;
    }
    
    public SimpleObject assignAI(Brain brain){
        this.brain = brain;
        return this;
    }
    
    @Override
    public void update() {
        if (brain != null) {
            brain.function(this);
        }
        
        groundInstance.transform.translate(x, 0, y);
    }
    
    @Override
    public ModelInstance getModelInstance() {
        return this.groundInstance;
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
    public SimpleObject setAnimation(String path, int loop) {
        return this;
    }

    @Override
    public TextureModel getTextureModel() {
        return textureModel;
    }
}
