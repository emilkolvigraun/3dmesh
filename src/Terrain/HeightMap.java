/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrain;

import java.util.ArrayList;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class HeightMap {
        
    private float[] vertices;
    private short[] indices;
    
    
    public HeightMap(ArrayList<Float> vertices, ArrayList<Short> indices){
        this.vertices = new float[vertices.size()];
        this.indices = new short[indices.size()];
        
        for (int i = 0; i < vertices.size(); i++){
            this.vertices[i] = vertices.get(i);
        }
        
        for (int i = 0; i < indices.size(); i++){
            this.indices[i] = indices.get(i);
        }
    }

    public float[] getVertices() {
        return vertices;
    }

    public short[] getIndices() {
        return indices;
    }    
}
