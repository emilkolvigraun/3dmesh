/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.UBJsonReader;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class Figure implements TextureModel {

    private Model model;
    private float w, h;
    
    public Figure(UBJsonReader reader, G3dModelLoader loader, String path){
        reader = new UBJsonReader();
        loader = new G3dModelLoader(reader);
        model = loader.loadModel(Gdx.files.getFileHandle(path, Files.FileType.Internal)); 
        w = 100;
        h = 100;
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
