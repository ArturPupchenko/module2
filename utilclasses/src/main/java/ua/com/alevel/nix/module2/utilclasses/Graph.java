package ua.com.alevel.nix.module2.utilclasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    int numberOfVertices;
    Map<Integer, String> vertices;
    int matrix[][];

    public Graph(Integer numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        vertices = new HashMap<>(numberOfVertices);
        matrix = new int[numberOfVertices][numberOfVertices];
    }

    public void setVertexName(Integer index, String name) {
        vertices.put(index, name);
    }

    public int getVertexIndex(String name) {
        int vertexIndex = 0;
        for (Map.Entry<Integer, String> entry : vertices.entrySet()) {
            if (entry.getValue().equals(name)) {
                vertexIndex = entry.getKey();
            }
        }
        return vertexIndex;
    }

    public void addEdge(int source, int destination, int weight) {
        matrix[source][destination] = weight;
        matrix[destination][source] = weight;
    }

    public int getMinimumVertex(boolean[] mst, int[] key) {
        int minKey = 20000;
        int vertex = -1;
        for (int i = 0; i < numberOfVertices; i++) {
            if (mst[i] == false && minKey > key[i]) {
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

    public void dijkstra_GetMinDistances(String sourceVertexName, String targetVertexName) {
        int sourceVertexIndex = getVertexIndex(sourceVertexName);
        boolean[] spt = new boolean[numberOfVertices];
        int[] distance = new int[numberOfVertices];
        int INFINITY = 20000;

        
        for (int i = 0; i < numberOfVertices; i++) {
            distance[i] = 20000;
        }

        
        distance[sourceVertexIndex] = 0;

        
        for (int i = 0; i < numberOfVertices; i++) {

        
            int vertex_U = getMinimumVertex(spt, distance);

            
            spt[vertex_U] = true;

    
            for (int vertex_V = 0; vertex_V < numberOfVertices; vertex_V++) {
               
                if (matrix[vertex_U][vertex_V] > 0) {
                    

                    if (spt[vertex_V] == false && matrix[vertex_U][vertex_V] != INFINITY) {
                    

                        int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
                        if (newKey < distance[vertex_V])
                            distance[vertex_V] = newKey;
                    }
                }
            }
        }
        
        printResultToTextFile(distance, targetVertexName);
    }

    public void printResultToTextFile(int[] key, String targetVertexName) {

        int targetVertexIndex = getVertexIndex(targetVertexName);

        String text = String.valueOf(key[targetVertexIndex]);
        File outputFile = new File("output.txt");
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(outputFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

