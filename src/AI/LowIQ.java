/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Objects.WorldObject;

/**
 *
 * @author Emil S. Kolvig-Raun
 */
public class LowIQ implements Brain {
    float alpha = 0;

    @Override
    public void function(WorldObject object) {
        float x = (float) Math.cos(Math.toRadians(alpha))* 2;
        float y = (float) (Math.sin(Math.toRadians(alpha))* 2); 
        alpha++;
        
        object.setX(x);
        object.setY(y);
    }
    
}
