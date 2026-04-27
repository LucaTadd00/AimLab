package com.mycompany.aimlab_painversion;


import org.lwjgl.opengl.GL11;

import org.joml.Vector3f;


public class Gravity {

    static float velocityY = 0.0f;    // velocità verticale
    static final float gravity = -9.8f;     // accelerazione di gravità
    static final float jumpStrength = 6.0f;
    static float deltaTime = 0.0066f;  // tempo per frame (assumiamo 60 FPS = 1/60 ≈ 0.016)
    static float groundLevel = 1.8f;  // livello del pavimento
    static boolean isGrounded = false;
    
    public static HeightMap n1 = new HeightMap(1);
    
    public static int valuex = (int)Camera.camX;
    public static int valuez = (int)Camera.camZ;
    
    public static void gravity() {
        // 1. Applica la gravità 
        velocityY += gravity * deltaTime; 
        Camera.camY += velocityY * deltaTime;
    
        // Partiamo dal presupposto che stiamo cadendo, a meno che non troviamo il suolo
        isGrounded = false; 
        
        int arrayX = Math.round(Camera.camX);
        int arrayZ = Math.round(Camera.camZ);
        
        if (arrayX < -100 || arrayX > 99|| arrayZ < -100 || arrayZ > 99) {
            System.out.println("Sei caduto fuori dall'arena! Respawn al centro.");
        
            // Riportiamo il giocatore esattamente al centro del mondo 3D
            Camera.camX = 0.0f;
            Camera.camZ = 0.0f;
        
            // Lo mettiamo un po' in alto rispetto al pavimento centrale per farlo atterrare
            Camera.camY = n1.getHeightAt(0, 0) + 5.0f; 
        
            // Azzeriamo la velocità di caduta altrimenti si schianta a terra alla velocità della luce
            velocityY = 0.0f; 
        
            return; // Fermiamo l'esecuzione di questo metodo per questo frame
        }

        // 2. Traduzione pulita delle coordinate nel mondo verso l'array (Offset)
        float floorHeight = n1.getHeightAt(Camera.camX, Camera.camZ);

        // 5. Collisione verticale (Suolo)
        if (Camera.camY <= floorHeight && Camera.camY >= floorHeight - 0.2) {
            // Se la telecamera è scesa sotto o esattamente sul pavimento:
            Camera.camY = floorHeight; // "Incolla" il giocatore sopra il blocco
            velocityY = 0.0f;          // Azzera l'energia della caduta
            isGrounded = true;         // Tocchiamo terra, possiamo saltare!
        }
        
       
    }

    public static void jump() {
        if (isGrounded) {
            velocityY = jumpStrength; // jumpStrength deve essere un valore positivo (es. 5.0f)
            isGrounded = false;       // Appena saltiamo, non siamo più a terra
        }
    }
      
}

/*
    public static void check_groundLevel() {
        
        Vector3f origin = new Vector3f(Camera.camX, Camera.camY, Camera.camZ);
        Vector3f direction = new Vector3f(0, -1, 0); // raggio verso il basso

    float step = 0.1f;
    float maxDistance = 5.0f;

    for (float d = 0; d < maxDistance; d += step) {
        Vector3f point = new Vector3f(direction).mul(d).add(origin);

    // Controlla se c'è un oggetto solido a queste coordinate
        if (isSolidBlockAt(point.x, point.y, point.z)) {
            System.out.println("Oggetto sotto a distanza: " + d);
            break;
        }
    }
    }
    
    private static boolean isSolidBlockAt(float x, float y, float z) {
        return true;
    }
    */
