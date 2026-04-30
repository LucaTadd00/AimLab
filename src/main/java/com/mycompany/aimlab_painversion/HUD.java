package com.mycompany.aimlab_painversion;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBEasyFont;
import java.nio.ByteBuffer;

public class HUD {
    
    static int size = 4;
    
    public static void init(int x, int y) {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();

    // Setup proiezione ortografica 2D
        GL11.glOrtho(0, x, y, 0, -1, 1);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();

    /* Esempio: disegna un rettangolo blu in alto a sinistra
        GL11.glColor3f(1f, 1f, 1f); 
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(10, 10);
        GL11.glVertex2f(110, 10);
        GL11.glVertex2f(110, 110);
        GL11.glVertex2f(10, 110);
        GL11.glEnd();
    */
        
    //Puntatore centrale
        GL11.glColor3f(0.5f, 0.5f, 0.5f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x/2 - size, y/2 - size);
        GL11.glVertex2f(x/2 + size, y/2 - size);
        GL11.glVertex2f(x/2 + size, y/2 + size);
        GL11.glVertex2f(x/2 - size, y/2 + size);
        GL11.glEnd();
        
    //coordinate a schermo
        String text = "Coordinate:\n X:" + (int)Camera.camX + "\n Y:" + (int)Camera.camY + "\n Z:" + (int)Camera.camZ;
        
        try (org.lwjgl.system.MemoryStack stack = org.lwjgl.system.MemoryStack.stackPush()) {
            // STBEasyFont richiede un buffer di memoria per disegnare le lettere
            // 270 byte per carattere sono sufficienti
            ByteBuffer charBuffer = stack.malloc(text.length() * 270);
            
            // Genera i quadrati per le lettere e ottieni quanti "quads" sono stati creati
            int quads = STBEasyFont.stb_easy_font_print(0, 0, text, null, charBuffer);
            
            // Colore del testo (Bianco)
            GL11.glColor3f(1f, 1f, 1f);
            
            // Sposta il testo dove vuoi sullo schermo (es. x=10, y=10)
            GL11.glPushMatrix();
            GL11.glTranslatef(15, 15, 0);
            // Scala il testo se ti sembra troppo piccolo (STBEasyFont è piccolino di default)
            GL11.glScalef(2.0f, 2.0f, 1.0f); 
            
            // Disegna letteralmente la memoria che STB ha preparato
            GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, charBuffer);
            GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);
            GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    
            // Ripristina la matrice
            GL11.glColor3f(1f, 1f, 1f);
            GL11.glPopMatrix();
        }
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Riattiva il depth test
    }
}
