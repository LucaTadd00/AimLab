package com.mycompany.aimlab_painversion;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_EXTENSIONS;
import static org.lwjgl.opengl.GL11.GL_MAX_TEXTURE_SIZE;
import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.opengl.GL11.GL_VENDOR;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetInteger;
import static org.lwjgl.opengl.GL11.glGetString;

public class Game {
    
        public static void loop(long window) {
        System.out.println("----------------------------");
        System.out.println("OpenGL Version : " + glGetString(GL_VERSION));
        System.out.println("OpenGL Max Texture Size : " + glGetInteger(GL_MAX_TEXTURE_SIZE));
        System.out.println("OpenGL Vendor : " + glGetString(GL_VENDOR));
        System.out.println("OpenGL Renderer : " + glGetString(GL_RENDERER));
        System.out.println("OpenGL Extensions supported by your card : ");
        String extensions = glGetString(GL_EXTENSIONS);
        String[] extArr = extensions.split("\\ ");
        for (int i = 0; i < extArr.length; i++) {
            System.out.println(extArr[i]);
        }
        System.out.println("----------------------------");
 
        
        OBJLoader gunData = new OBJLoader();
        gunData.loadModel("blaster-n.obj"); //e, l
        
        Renderer gunRenderer = new Renderer();
        gunRenderer.compileGunModel(gunData); // Qui invii i dati alla GPU
        
        while (!glfwWindowShouldClose(window)) {
            
            
            Camera.updateCamera();
            
            Gravity.gravity();
            
            GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                      
            Builds.buildAll();
            
            Builds.drawGun(gunRenderer);
           
            HUD.init(1920, 1080);
            
            //tasti configurati per il momento
            Controls.checkControls(window);
            
            glfwSwapBuffers(window);
 
            glfwPollEvents();  //input di bottoni e tasti
        }
        
        gunRenderer.cleanup();
    }
}
