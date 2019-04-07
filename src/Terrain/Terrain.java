/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrain;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class Terrain {

    private int w, y;
    private ArrayList<ModelInstance> instances;
    private ModelInstance part;
    private float multiplier;

    public Terrain(int w, int y) {
        this.w = w;
        this.y = y;
        multiplier = 1;
        instances = new ArrayList<>();
    }

    public Terrain(float multiplier) {
        this.multiplier = multiplier;
        instances = new ArrayList<>();
    }

    public void generateTerrain() {
        // Create float array of vertices
        HeightMap meshData = createNoise();
        ModelBuilder builder = new ModelBuilder();
        builder.begin();
        Mesh mesh = new Mesh(true, meshData.getIndices().length, meshData.getIndices().length,
                new VertexAttribute(Usage.Position, 3, "a_position"),
                new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

        mesh.setVertices(meshData.getVertices());

        mesh.setIndices(meshData.getIndices());

        builder.part("terrain", mesh, GL20.GL_TRIANGLES, new Material());
        part = new ModelInstance(builder.end());
        instances.add(part);

    }

    private HeightMap createNoise() {
        Random rand = new Random();
        ArrayList<ArrayList<ArrayList<Float>>> columns = new ArrayList<>();

        ArrayList<Short> indices = new ArrayList<>();
        ArrayList<Float> vertices = new ArrayList<>();

        float x1 = 5f, x2 = 0f, z1 = 5f, z2 = 0f;

        Pixmap heatmap = new Pixmap(Gdx.files.getFileHandle("res/terrain/heightmap.png", Files.FileType.Internal));
        Color heatColor = new Color();
        short indice = 0;
        for (int x = 0; x < heatmap.getWidth(); x++) {
            ArrayList<ArrayList<Float>> rows = new ArrayList<>();
            float yprev5 = -1, yprev4 = -1;
            for (int y = 0; y < heatmap.getHeight(); y++) {
                ArrayList<Float> currentColumn = new ArrayList<>();
                float xprev5 = -1, xprev3 = -1;

                if (x > 0) {
                    xprev5 = columns.get(x - 1).get(y).get(5);
                    xprev3 = columns.get(x - 1).get(y).get(3);
                } else {
                    xprev3 = 0f;
                    xprev5 = 0f;
                }

                int heightmap = heatmap.getPixel(x, y);
                Color.rgba8888ToColor(heatColor, heightmap);
                int R = (int) (heatColor.r * 255f);
                int G = (int) (heatColor.g * 255f);
                int B = (int) (heatColor.b * 255f);
                int A = (int) (heatColor.a * 255f);

                double currentHeight = ((R + G + B + A) - 255);

                if (currentHeight > 0) {
                    currentHeight /= 750;
                    currentHeight *= multiplier;
                }

                if (yprev5 == -1) {
                    yprev5 = (float) currentHeight;
                    yprev4 = (float) currentHeight;
                } else if (x > 0 && y == 0){
                    yprev5 = (float) currentHeight;
                }

                //maqke sure heights fit the octagons
                float currentColor = Color.toFloatBits(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255);
                float current2Color = Color.toFloatBits(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255);

                //0
                vertices.add(x2);
                vertices.add(xprev5);
                vertices.add(z2);
                vertices.add(currentColor);
                currentColumn.add(xprev5);
                //1
                vertices.add(x2);
                vertices.add(xprev3);
                vertices.add(z1);
                vertices.add(currentColor);
                currentColumn.add(xprev3);
                //2
                vertices.add(x1);
                vertices.add(yprev5);
                vertices.add(z2);
                vertices.add(currentColor);
                currentColumn.add(yprev5);
                //5
                vertices.add(x1);
                vertices.add((float) currentHeight);
                vertices.add(z1);
                vertices.add(current2Color);
                currentColumn.add((float) currentHeight);
                //4
                vertices.add(x2);
                vertices.add(xprev3);
                vertices.add(z1);
                vertices.add(current2Color);
                currentColumn.add(xprev3);
                //3
                vertices.add(x1);
                vertices.add(yprev5);
                vertices.add(z2);
                vertices.add(current2Color);
                currentColumn.add(yprev5);

                int indicePosition = indices.size();

                indice = (short) indicePosition;

                indices.add(indice++);
                indices.add(indice++);
                indices.add(indice++);
                indice += 2;
                indices.add(indice--);
                indices.add(indice--);
                indices.add(indice--);

                z1 += 5f;
                z2 += 5f;

                rows.add(currentColumn);
                
                yprev5 = (float) currentHeight;
            }
            x1 += 5f;
            x2 += 5f;
            z1 = 5f;
            z2 = 0;
            columns.add(rows);
            
        }

        System.out.println(indices.toString());
        System.out.println(vertices.toString());
        System.out.println(columns.get(0).get(0).toString());
        return new HeightMap(vertices, indices);
    }

    public ArrayList<ModelInstance> getTerrain() {
        return instances;
    }
}
