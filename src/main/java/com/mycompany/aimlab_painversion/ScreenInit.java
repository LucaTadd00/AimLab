package com.mycompany.aimlab_painversion;
 
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;

import org.lwjgl.Version;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.BufferUtils;

import java.nio.*;




public class ScreenInit {

    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private long window;
    
      

    private void init() {
        
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        
        if (!glfwInit())
           throw new IllegalStateException("Impossibile inizializzare GLFW");

        // Crea la finestra
        
        glfwDefaultWindowHints();  //impostazioni di default per la finestra
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);  //Finestra visibile, falso
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);  //Finestra Modificabile, falso
        
        window = glfwCreateWindow(1920, 1080, "Finestra LWJGL", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Errore nella creazione della finestra");
        
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
        }
        });
        
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - 1920) / 2, (vidmode.height() - 1080) / 2);
        

         
        // Imposta il contesto OpenGL
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);  // V-Sync
        glfwShowWindow(window);

        // Inizializza OpenGL
        GL.createCapabilities();
        
        Camera.start(window);
        
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        
        
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        
        GL11.glEnable(GL11.GL_LIGHTING); // Attiva l'illuminazione
        GL11.glEnable(GL11.GL_LIGHT0); // Usa la luce 0 (la luce principale)

        
        
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4);

        // Poi lo riempi e lo usi
        buffer.clear();
        buffer.put(new float[] { 0.0f, 5.0f, -3.0f, 1.0f }).flip();
        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, buffer);

        
        buffer.clear();
        buffer.put(new float[] { 1.0f, 1.0f, 1.0f, 1.0f }).flip();
        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, buffer);

        buffer.clear();
        buffer.put(new float[] { 1.0f, 1.0f, 1.0f, 1.0f }).flip();
        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_SPECULAR, buffer);
        

        buffer.clear();
        buffer.put(new float[] { 0.2f, 0.2f, 0.2f, 1.0f }).flip();
        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT, buffer);
        
        buffer.clear();
        buffer.put(new float[] { 0.8f, 0.8f, 0.8f, 1.0f }).flip();
        GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, buffer);

        // Materiale speculare
        buffer.clear();
        buffer.put(new float[] { 1.0f, 1.0f, 1.0f, 1.0f }).flip();
        GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, buffer);

        // Lucentezza
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 50.0f);
        
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE);
        
        GL11.glClearColor(0.8f, 0.90f, 1.0f, 1.0f);

    
       
    }
    
    
    
    
    
    public void run() {
        System.out.println("Hello LWJGL3 " + Version.getVersion() + "!");
  
        try {
            init();
            Game.loop(window);
            glfwDestroyWindow(window);
            
        } finally {
            glfwTerminate();
        }    
    }
}
