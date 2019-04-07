/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Common.TextureModel;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public interface WorldObject {
    
    public Model getModel();
    public ModelInstance getModelInstance();
    public WorldObject setAnimation(String path, int loop);
    public TextureModel getTextureModel();
    public void update();
    public float getX();
    public void setX(float x);
    public float getY();
    public void setY(float y);
    
}
