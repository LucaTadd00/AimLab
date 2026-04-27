package com.mycompany.aimlab_painversion;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.glfw.GLFW;

import java.nio.FloatBuffer;

public class Camera {
    
    public static float camX = 0.0f, camY = 1.8f, camZ = 5.0f;
    
    static Matrix4f view = new Matrix4f();
    static Matrix4f projection = new Matrix4f();
    
    private static float lastX = 400, lastY = 300; 
    private static boolean firstMouse = true;
    private static float yaw = -90.0f; 
    private static float pitch = 0.0f; 
    private static float sensitivity = 0.2f;
    
    public static Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
    public static Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
    public static Vector3f right = new Vector3f();
    
    
    // ==========================================
    // CHIAMALO SOLO 1 VOLTA ALL'AVVIO (o se ridimensioni la finestra)
    // ==========================================
    public static void setupProjection() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        projection.identity().perspective((float) Math.toRadians(90.0f), 1400f / 900f, 0.1f, 200.0f);
        
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            projection.get(fb);
            GL11.glLoadMatrixf(fb);
        }
        
        // Finito con la proiezione, torniamo alla ModelView per il resto del gioco
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    
    // ==========================================
    // CHIAMALO A OGNI FRAME (Game Loop)
    // ==========================================
    public static void updateCamera() {
        // Ripuliamo la matrice del mondo
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        
        // TRUCCO JOML: Passiamo direttamente le primitive (float) a lookAt.
        // Zero "new Vector3f()", zero spreco di memoria!
        view.identity().lookAt(
            camX, camY, camZ,                                      // Posizione (Eye)
            camX + cameraFront.x, camY + cameraFront.y, camZ + cameraFront.z,  // Dove guarda (Center)
            cameraUp.x, cameraUp.y, cameraUp.z                     // Alto (Up)
        );

        // Carichiamo la View in OpenGL
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            view.get(fb);
            GL11.glLoadMatrixf(fb);
        }
    }
    
    public static void moveCam(Vector3f dir, float scale) {
        float speed = 0.05f;
        float nextX = camX + dir.x * speed * scale;
        float nextZ = camZ + dir.z * speed * scale;

        if(checkMove(nextX, nextZ)){
            camX = nextX;
            camZ = nextZ;
        
            // Limiti mappa
            if(camX > 99) camX = 99;
            if(camX < -99) camX = -99;
            if(camZ > 99) camZ = 99;
            if(camZ < -99) camZ = -99;
        }   
    }
    
    public static boolean checkMove(float nextX, float nextZ) {
        float altezzaAttuale = camY;
        int targetX = Math.round(nextX);
        int targetZ = Math.round(nextZ);

        float altezzaTarget = Gravity.n1.getHeightAt(targetX, targetZ);
        float dislivello = altezzaTarget - altezzaAttuale;

        return dislivello <= 0.2f; 
    }
    
    public static void start(long window) {
        setupProjection(); // Chiamiamo la proiezione qui, una volta sola!
        capture(window); 
    }
    
    public static void capture(long window) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        
        GLFW.glfwSetCursorPosCallback(window, (win, xpos, ypos) -> {
            if (firstMouse) {
                lastX = (float)xpos;
                lastY = (float)ypos;
                firstMouse = false;
            }
                
            float xoffset = (float)xpos - lastX;
            float yoffset = lastY - (float)ypos; 
            lastX = (float)xpos;
            lastY = (float)ypos;

            xoffset *= sensitivity;
            yoffset *= sensitivity;

            yaw += xoffset;
            pitch += yoffset;

            if (pitch > 89.0f) pitch = 89.0f;
            if (pitch < -89.0f) pitch = -89.0f;

            // Aggiorniamo cameraFront, right e up direttamente qui quando il mouse si muove
            cameraFront.x = (float)Math.cos(Math.toRadians(yaw)) * (float)Math.cos(Math.toRadians(pitch));
            cameraFront.y = (float)Math.sin(Math.toRadians(pitch));
            cameraFront.z = (float)Math.sin(Math.toRadians(yaw)) * (float)Math.cos(Math.toRadians(pitch));
            cameraFront.normalize();
            
            // Ricalcoliamo right e up aggiornando i vettori esistenti
            cameraFront.cross(0.0f, 1.0f, 0.0f, right).normalize();
            right.cross(cameraFront, cameraUp).normalize();
        });
    }      
}