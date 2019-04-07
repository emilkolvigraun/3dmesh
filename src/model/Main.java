/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class Main {

    public static void main(String args[]) {

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        cfg.title = "Random Universe Destruction Engine";
        //cfg.width = 1000;
        //cfg.height = 680;
        new LwjglApplication(new GameModel(), cfg);

    }

}
