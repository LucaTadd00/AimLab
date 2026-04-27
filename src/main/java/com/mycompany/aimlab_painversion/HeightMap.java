package com.mycompany.aimlab_painversion;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HeightMap {
    
    public float[][] heightMap = new float[200][200];
    private float playerHigh = 1.8f;
    private int dimFilex = 0;
    private int dimFilez = 0;
    
    public HeightMap(int c) {
        switch(c) {
            case 1:
                loadFromFile("Map1.txt");                                                
            break;
            case 2:
            break;
        }
    }
    
    private void loadFromFile(String fileName) {
        // 1. Inizializziamo la mappa a 0 (per evitare valori "sporchi")
        for (int x = 0; x < 200; x++) {
            for (int z = 0; z < 200; z++) {
                heightMap[x][z] = 0.0f;
            }
        }

        // 2. Chiediamo a Java di cercare il file nella cartella "resources"
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            
            // Se il file non esiste, ci fermiamo
            if (is == null) {
                System.err.println("Errore: Impossibile trovare il file " + fileName + " nella cartella resources.");
                return;
            }
            
            // 3. Prepariamo i lettori per scorrere il file di testo riga per riga
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            int z = 0; // Usiamo z per scorrere le righe
            
            // 4. Leggiamo finché ci sono righe E finché non superiamo la grandezza della mappa (200)
            while ((line = reader.readLine()) != null && z < 200) {
                String[] values = line.trim().split("\\s+");
                if (z == 0) dimFilex = values.length;
                for (int x = 0; x < values.length && x < 200; x++) {
                    heightMap[x][z] = Float.parseFloat(values[x]);
                }
                z++;
            }
            
            dimFilez = z;
            
            System.out.println("Mappa " + fileName + " caricata con successo!");
            
        } catch (Exception e) {
            System.err.println("Errore critico durante la lettura della mappa " + fileName);
            e.printStackTrace();
        }
    }
    
    public float getHeightAt(float worldX, float worldZ) {
        int arrayX = 0;
        int arrayZ = 0;
        
        if(worldX < 0) {
            arrayX = Math.round(Math.abs(worldX) + 101);
        } 
        if(worldZ < 0) {
            arrayZ = Math.round(Math.abs(worldZ) + 101);
        } 
        if(worldX >= 0) {
            arrayX = Math.round(worldX);
        }
        if (worldZ >= 0) {
            arrayZ = Math.round(worldZ);
        }
        
        
    return heightMap[arrayX][arrayZ];
}
    
}
