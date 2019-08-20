package saini;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReaderFromFile {

    private static String PATH_NAME = "C:\\Users\\aspaliwa\\Desktop\\New folder";

    private static Map<String, List<String>> mapWithHeaderAndData = new HashMap<>();

    public static void main(String[] args) {
        File folderWithDynamicFiles = new File(PATH_NAME);
        for (File fileWithData : folderWithDynamicFiles.listFiles()) {
            if (fileWithData.isFile()) {
                readDataFromFile(fileWithData, mapWithHeaderAndData);
            }
        }

        printDatafromMap();
    }

    private static void printDatafromMap() {
        for (String header : mapWithHeaderAndData.keySet()) {
            System.out.print(header + "\t");
        }
        System.out.println();
        String firstHeader = mapWithHeaderAndData.keySet().iterator().next();
        for (int i = 0; i < mapWithHeaderAndData.get(firstHeader).size(); i++) {
            for (String header : mapWithHeaderAndData.keySet()) {
                List<String> dataValues = mapWithHeaderAndData.get(header);
                System.out.print(dataValues.get(i) + "\t");
            }
            System.out.println();
        }

    }

    private static void readDataFromFile(File file, Map<String, List<String>> mapWithHeaderAndData2) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine();
            String[] headers = header.split("\\t");
            addHeadersToMap(headers);
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\t");
                for (int i = 0; i < headers.length; i++) {
                    // get header data and add data to that list
                    mapWithHeaderAndData.get(headers[i]).add(values[i]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addHeadersToMap(String[] headers) {
        for (String header : headers) {
            mapWithHeaderAndData.putIfAbsent(header, new ArrayList<>());
        }
    }
}
