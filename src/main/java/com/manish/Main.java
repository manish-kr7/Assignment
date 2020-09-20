package com.manish;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        JsonComparator jsonComparator = new JsonComparator();
        jsonComparator.compareTwoFile("emptyFile1.txt", "file2.txt");

    }
}
