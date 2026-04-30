package com.mycompany.aimlab_painversion;

import org.lwjgl.glfw.GLFW;
import org.joml.Vector3f;

public class Controls {
    
    public static void checkControls(long window){
        // --- GESTIONE MOVIMENTO (Normalizzato) ---
        Vector3f moveDir = new Vector3f(0, 0, 0);
        
        // Ignoriamo la Y della mira per non volare o affondare muovendoci
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            moveDir.add(Camera.cameraFront.x, 0, Camera.cameraFront.z);
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            moveDir.sub(Camera.cameraFront.x, 0, Camera.cameraFront.z);
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            moveDir.add(Camera.right.x, 0, Camera.right.z);
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            moveDir.sub(Camera.right.x, 0, Camera.right.z);
        }
        
        if (moveDir.lengthSquared() > 0) {
            moveDir.normalize();
            
            float speedScale = 1.0f;
            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
                speedScale = 1.5f; // Corsa
            }
            
            Camera.moveCam(moveDir, speedScale);
        }

        // --- GESTIONE SALTO ---
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            Gravity.jump();
        }
    }
    
    
}
