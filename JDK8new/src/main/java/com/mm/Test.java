package com.mm;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("compare-20180820.data");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        while (reader.read()!=-1){
            String s = reader.readLine();
        }

    }
}
