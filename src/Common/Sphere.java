/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class Sphere implements TextureModel {
    private Model model;
    private float w, h;
    
    public Sphere(float r, Color color, ModelBuilder builder){
        this.w = r;
        this.h = r;
        
        model = builder.createSphere(r*2, r*2, r*2,
                50,50,
                new Material(ColorAttribute.createDiffuse(color)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
        );
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public float getWidth() {
        return this.w;
    }

    @Override
    public float getHeight() {
        return this.h;
    }
    
}
