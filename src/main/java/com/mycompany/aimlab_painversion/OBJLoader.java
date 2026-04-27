package com.mycompany.aimlab_painversion;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {
    
    public List<float[]> vertices = new ArrayList<>();
    public List<int[][]> faces = new ArrayList<>(); // Indici dei vertici che formano i triangoli
    public List<float[]> normals = new ArrayList<>(); // NUOVO: Lista delle normali (luci)

    public void loadModel(String filePath) {
        
        InputStream is = getClass().getResourceAsStream("/" + filePath);
        if (is == null) {
            System.err.println("File non trovato: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                if (line.startsWith("v ")) {
                    String[] tokens = line.split("\\s+");
                    vertices.add(new float[]{Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])});
                } 
                // NUOVO: Leggiamo le Normali
                else if (line.startsWith("vn ")) {
                    String[] tokens = line.split("\\s+");
                    normals.add(new float[]{Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])});
                }
                // AGGIORNATO: Leggiamo la faccia collegando i vertici alle normali
                else if (line.startsWith("f ")) {
                    String[] tokens = line.split("\\s+");
                    int[][] face = new int[3][2]; // 3 punti del triangolo, 2 indici ciascuno (V e VN)
                    
                    for (int i = 0; i < 3; i++) {
                        // Un token tipico è "1/1/1" o "1//1" (Vertice/Texture/Normale)
                        String[] parts = tokens[i + 1].split("/");
                        
                        face[i][0] = Integer.parseInt(parts[0]) - 1; // Indice Vertice
                        
                        // Controlliamo se la normale esiste in questo file OBJ
                        if (parts.length == 3 && !parts[2].isEmpty()) {
                            face[i][1] = Integer.parseInt(parts[2]) - 1; // Indice Normale
                        } else {
                            face[i][1] = -1; // Niente normale
                        }
                    }
                    faces.add(face);
                }
            }
        } catch (Exception e) {
            System.err.println("Errore caricamento OBJ: " + e.getMessage());
        }
    }
}
