package com.mycompany.aimlab_painversion;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glVertex3f;


public class Builds {
    
    private static int displayListId = -1;

    public static void compile() {
        displayListId = GL11.glGenLists(1);
        GL11.glNewList(displayListId, GL11.GL_COMPILE);
        wall();
        ground(); 
        miniwall();
        GL11.glEndList();
        System.out.println("Muri e pavimento compilati nella Display List GPU!");
    }
    
    public static void cleanup() {
        if (displayListId != -1) {
            GL11.glDeleteLists(displayListId, 1);
        }
    }
    
    public static void buildAll() {
        if (displayListId != -1) {
            GL11.glCallList(displayListId);
        } else {
            wall();
            ground(); 
            miniwall();
        }
    }
    
      
    public static void wall() {
        
        //muro1
        GL11.glPushMatrix();   
        // Imposta il colore del muro
        glColor3f(0.6f, 0.6f, 0.6f); // Grigio chiaro

        glBegin(GL_QUADS);
            // Vertici in senso antiorario per il fronte visibile
            glVertex3f(20.0f, 0.0f, -20.0f);   // In basso a sinistra
            glVertex3f(20.0f, 0.0f, 20.0f);  // In basso a destra
            glVertex3f(20.0f, 10.0f, 20.0f); // In alto a destra
            glVertex3f(20.0f, 10.0f, -20.0f);  // In alto a sinistra
        glEnd();

        GL11.glPopMatrix();
        GL11.glPushMatrix();
        
        // Disegna contorno del muro
        glColor3f(0.0f, 0.0f, 0.0f); // Nero per i bordi
        glLineWidth(3.0f);
        
        glBegin(GL_LINE_LOOP);
            glVertex3f(20.0f, 0.0f, -20.0f);   // In basso a sinistra
            glVertex3f(20.0f, 0.0f, 20.0f);  // In basso a destra
            glVertex3f(20.0f, 10.0f, 20.0f); // In alto a destra
            glVertex3f(20.0f, 10.0f, -20.0f);  // In alto a sinistra
        glEnd();
        
        GL11.glPopMatrix();
        
        //muro2
        
        GL11.glPushMatrix();   
        // Imposta il colore del muro
        glColor3f(0.6f, 0.6f, 0.6f); // Grigio chiaro

        glBegin(GL_QUADS);
            // Vertici in senso antiorario per il fronte visibile
            glVertex3f(-20.0f, 0.0f, -20.0f);   // In basso a sinistra
            glVertex3f(20.0f, 0.0f, -20.0f);  // In basso a destra
            glVertex3f(20.0f, 10.0f, -20.0f); // In alto a destra
            glVertex3f(-20.0f, 10.0f, -20.0f);  // In alto a sinistra
        glEnd();

        GL11.glPopMatrix();
        GL11.glPushMatrix();
        
        // Disegna contorno del muro
        glColor3f(0.0f, 0.0f, 0.0f); // Nero per i bordi
        glLineWidth(3.0f);
        
        glBegin(GL_LINE_LOOP);
            glVertex3f(-20.0f, 0.0f, -20.0f);   // In basso a sinistra
            glVertex3f(20.0f, 0.0f, -20.0f);  // In basso a destra
            glVertex3f(20.0f, 10.0f, -20.0f); // In alto a destra
            glVertex3f(-20.0f, 10.0f, -20.0f);  // In alto a sinistra
        glEnd();
        
        GL11.glPopMatrix();
        
        //muro3
        
        GL11.glPushMatrix();   
        // Imposta il colore del muro
        glColor3f(0.6f, 0.6f, 0.6f); // Grigio chiaro

        glBegin(GL_QUADS);
            // Vertici in senso antiorario per il fronte visibile
            glVertex3f(-20.0f, 0.0f, 20.0f);   // In basso a sinistra
            glVertex3f(-20.0f, 0.0f, -20.0f);  // In basso a destra
            glVertex3f(-20.0f, 10.0f, -20.0f); // In alto a destra
            glVertex3f(-20.0f, 10.0f, 20.0f);  // In alto a sinistra
        glEnd();

        GL11.glPopMatrix();
        GL11.glPushMatrix();
        
        // Disegna contorno del muro
        glColor3f(0.0f, 0.0f, 0.0f); // Nero per i bordi
        glLineWidth(3.0f);
        
        glBegin(GL_LINE_LOOP);
            glVertex3f(-20.0f, 0.0f, 20.0f);   // In basso a sinistra
            glVertex3f(-20.0f, 0.0f, -20.0f);  // In basso a destra
            glVertex3f(-20.0f, 10.0f, -20.0f); // In alto a destra
            glVertex3f(-20.0f, 10.0f, 20.0f);  // In alto a sinistra
        glEnd();
        
        GL11.glPopMatrix();
        
    }
    
    public static void miniwall() {
        GL11.glPushMatrix();   
        // Imposta il colore del muro
        glColor3f(0.7f, 0.7f, 0.7f); // Grigio chiaro

        glBegin(GL_QUADS);
            // Vertici in senso antiorario per il fronte visibile
            glVertex3f(-20.0f, 0.0f, 0.0f);   // In basso a sinistra
            glVertex3f(20.0f, 0.0f, 0.0f);  // In basso a destra
            glVertex3f(20.0f, 1.0f, 0.0f);   // In basso a sinistra
            glVertex3f(-20.0f, 1.0f, 0.0f);  // In basso a destra
        glEnd();

        GL11.glPopMatrix();
        GL11.glPushMatrix();
        
        // Disegna contorno del muro
        glColor3f(0.0f, 0.0f, 0.0f); // Nero per i bordi
        glLineWidth(3.0f);
        
        glBegin(GL_LINE_LOOP);
            glVertex3f(-20.0f, 0.0f, 0.0f);   // In basso a sinistra
            glVertex3f(20.0f, 0.0f, 0.0f);  // In basso a destra
            glVertex3f(20.0f, 1.0f, 0.0f);   // In basso a sinistra
            glVertex3f(-20.0f, 1.0f, 0.0f);  // In basso a destra
        glEnd();
        
        GL11.glPopMatrix();
    }
    
    public static void ground() {
    
    GL11.glDepthMask(false);  // 🔒 Non scrive nel depth buffer
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    
    
    GL11.glPushMatrix();

    
    glColor4f(0.5f, 0.5f, 0.5f, 0.9f); // Grigio opaco    
// Inizia a disegnare il quadrilatero (piano)
    glBegin(GL_QUADS);
// Definisci i vertici del pavimento (un piano orizzontale)
        glVertex3f(-20.0f, 0.0f, -20.0f);
        glVertex3f(20.0f, 0.0f, -20.0f);
        glVertex3f(20.0f, 0.0f, 20.0f);
        glVertex3f(-20.0f, 0.0f, 20.0f);
    glEnd();
    
    GL11.glPopMatrix();
    
    GL11.glDepthMask(true);
    }
    
    public static void drawGun(Renderer gun) {

        // 0. FONDAMENTALE: Assicurati di operare sulla matrice del mondo/telecamera
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // 1. Pulisci il buffer di profondità per farla renderizzare SOPRA ai muri
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT); 

        // 2. Salva la matrice attuale (quella del mondo 3D)
        GL11.glPushMatrix(); 

        // 3. Resetta la telecamera: ora sei a 0,0,0 e guardi in avanti
        GL11.glLoadIdentity(); 

        // 4. Posiziona la pistola (spostala in basso a destra e in avanti)
        GL11.glTranslatef(0.5f, -0.5f, -1.0f); 

        // 5. Ruota leggermente la pistola per darle un'angolazione realistica
        GL11.glRotatef(-10f, 0f, 1f, 0f); 

        // NUOVO: Riduci la scala del modello se è troppo grande! 
        GL11.glScalef(1.5f, 1.5f, 1.5f); 
        
        try (org.lwjgl.system.MemoryStack stack = org.lwjgl.system.MemoryStack.stackPush()) {
            java.nio.FloatBuffer matAmbient = stack.floats(0.0f, 0.1f, 0.2f, 1.0f); 
            java.nio.FloatBuffer matDiffuse = stack.floats(0.1f, 0.5f, 0.8f, 1.0f); // Azzurro metallico
            java.nio.FloatBuffer matSpecular = stack.floats(1.0f, 1.0f, 1.0f, 1.0f); // Riflessi forti
            
            GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, matAmbient);
            GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, matDiffuse);
            GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, matSpecular);
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 100.0f); // Molto lucida
        }
        
        GL11.glColor3f(0.3f, 0.3f, 0.3f);

        // 6. Disegna il modello
        GL11.glCallList(gun.getgunDisplayListId());
        
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        
        try (org.lwjgl.system.MemoryStack stack = org.lwjgl.system.MemoryStack.stackPush()) {
            java.nio.FloatBuffer defaultSpec = stack.floats(0.0f, 0.0f, 0.0f, 1.0f);
            GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, defaultSpec);
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 0.0f);
        }

        // 7. Ripristina la matrice per tornare al mondo 3D normale
        GL11.glPopMatrix();
    }
        
}
