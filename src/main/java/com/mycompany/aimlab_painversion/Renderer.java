package com.mycompany.aimlab_painversion;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    private int gunDisplayListId;

    // =======================================================
    // 1. INIZIALIZZAZIONE (Da chiamare UNA SOLA VOLTA all'avvio)
    // =======================================================
    public void compileGunModel(OBJLoader gunModel) {
        
        // Chiediamo a OpenGL di generarci 1 ID libero per una nuova lista
        gunDisplayListId = glGenLists(1);

        // Iniziamo a "registrare". 
        // GL_COMPILE dice: "Memorizza questi comandi, ma NON disegnarli ora."
        glNewList(gunDisplayListId, GL_COMPILE);

        // --- INIZIO REGISTRAZIONE DEI COMANDI ---
        glBegin(GL_TRIANGLES);
        for (int[][] face : gunModel.faces) {
        
            // Per ogni punta del triangolo
            for (int i = 0; i < 3; i++) {
                int vIndex = face[i][0];
                int nIndex = face[i][1];

                // FONDAMENTALE: La normale deve essere chiamata PRIMA del vertice
                if (nIndex != -1) {
                    float[] n = gunModel.normals.get(nIndex);
                    glNormal3f(n[0], n[1], n[2]);
                }

                // Disegna il vertice
                float[] v = gunModel.vertices.get(vIndex);
                glVertex3f(v[0], v[1], v[2]);
            }
        }
        glEnd();
        // --- FINE REGISTRAZIONE ---

        // Diciamo a OpenGL di chiudere e salvare la lista.
        glEndList();
        
        System.out.println("Modello della pistola compilato e inviato alla GPU!");
    }
    
    
    public void cleanup() {
        // Le display list occupano memoria nella scheda video.
        // È buona norma dire alla GPU di cancellarla quando non serve più.
        glDeleteLists(gunDisplayListId, 1);
        System.out.println("Lista eliminata, Memoria GPU pulita!");
        
    }
    
    public int getgunDisplayListId() {
        return gunDisplayListId;
    }
}
