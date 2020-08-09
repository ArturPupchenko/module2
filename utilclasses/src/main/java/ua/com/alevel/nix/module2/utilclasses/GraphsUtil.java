package ua.com.alevel.nix.module2.utilclasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphsUtil {

    public static int[] getGraphEdgeFromString(String str) {
        String[] strs = str.trim().split("\\s+");
        int[] arr = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
        return arr;
    }


    public static void buildGraphFromTextFile(String fileName) {
        File textFile = new File(fileName);
        String textFilePath = textFile.getPath();
        List<String> linesAsArrayList = new ArrayList<>();
        String lineFromFile;
        int numberOfCities = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath))) {
            if ((lineFromFile = bufferedReader.readLine()) != null) {
                numberOfCities = Integer.valueOf(lineFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath))) {
            while ((lineFromFile = bufferedReader.readLine()) != null) {
                linesAsArrayList.add(lineFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!linesAsArrayList.isEmpty()) {
            File fileTo = new File("output.txt");
            try {
                fileTo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graph graph = new Graph(numberOfCities);
            for (int l = 1; l < linesAsArrayList.size(); ) {
                for (int i = 0; i < numberOfCities; i++) {
                    graph.setVertexName(i, linesAsArrayList.get(l));
                    int numberOfNeighbours = Integer.valueOf(linesAsArrayList.get(++l));
                    for (int j = 0; j < numberOfNeighbours; j++) {
                        int[] edgeInfo = getGraphEdgeFromString(linesAsArrayList.get(++l));
                        graph.addEdge(i, edgeInfo[0] - 1, edgeInfo[1]);
                   }
                    l++;
                }
                int numberOfShortestDistantionCounts = Integer.valueOf(linesAsArrayList.get(l));
                l++;
                for (int i = 0; i < numberOfShortestDistantionCounts; i++) {
                    String[] citiesToCountDistanceFor = linesAsArrayList.get(l++).split("\\s+");
                    String sourseVertex = citiesToCountDistanceFor[0];
                    String targerVertex = citiesToCountDistanceFor[1];
                    graph.dijkstra_GetMinDistances(sourseVertex, targerVertex);
                }
            }
        }
    }
}

