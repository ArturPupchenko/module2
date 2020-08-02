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

        //Initialize all the distance
        for (int i = 0; i < numberOfVertices; i++) {
            distance[i] = 20000;
        }

        //start from the vertex 0
        distance[sourceVertexIndex] = 0;

        //create SPT
        for (int i = 0; i < numberOfVertices; i++) {

            //get the vertex with the minimum distance
            int vertex_U = getMinimumVertex(spt, distance);

            //include this vertex in SPT
            spt[vertex_U] = true;

            //iterate through all the adjacent vertices of above vertex and update the keys
            for (int vertex_V = 0; vertex_V < numberOfVertices; vertex_V++) {
                //check of the edge between vertex_U and vertex_V
                if (matrix[vertex_U][vertex_V] > 0) {
                    //check if this vertex 'vertex_V' already in spt and
                    // if distance[vertex_V]!=Infinity

                    if (spt[vertex_V] == false && matrix[vertex_U][vertex_V] != INFINITY) {
                        //check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance

                        int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
                        if (newKey < distance[vertex_V])
                            distance[vertex_V] = newKey;
                    }
                }
            }
        }
        //print shortest path between vertices
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

