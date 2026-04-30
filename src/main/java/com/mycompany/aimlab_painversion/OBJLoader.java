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
                    java.util.StringTokenizer st = new java.util.StringTokenizer(line);
                    st.nextToken(); // salta "f"
                    int[][] face = new int[3][2]; // 3 punti del triangolo, 2 indici ciascuno (V e VN)
                    
                    for (int i = 0; i < 3; i++) {
                        String token = st.nextToken();
                        int firstSlash = token.indexOf('/');
                        int secondSlash = token.indexOf('/', firstSlash + 1);
                        
                        if (firstSlash == -1) {
                            face[i][0] = Integer.parseInt(token) - 1;
                            face[i][1] = -1;
                        } else {
                            face[i][0] = Integer.parseInt(token.substring(0, firstSlash)) - 1;
                            
                            if (secondSlash != -1 && secondSlash + 1 < token.length()) {
                                face[i][1] = Integer.parseInt(token.substring(secondSlash + 1)) - 1;
                            } else {
                                face[i][1] = -1;
                            }
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
