package com.mycompany.aimlab_painversion;

import org.lwjgl.glfw.GLFW;

public class Controls {
    
    public static void checkControls(long window){
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
                Camera.moveCam(Camera.cameraFront, +1.5F);
            } else {
                Camera.moveCam(Camera.cameraFront, +1);
            }
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            Camera.moveCam(Camera.cameraFront, -1);  // Muovi la camera indietro
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            Camera.moveCam(Camera.right, +1); 
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            Camera.moveCam(Camera.right, -1);  
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            //Camera.moveCam(Camera.cameraUp, +2); 
            Gravity.jump();
        }
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS) {
            Camera.moveCam(Camera.cameraUp, -1); 
        }
    }
    
    
}
