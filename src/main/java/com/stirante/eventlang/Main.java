package com.stirante.eventlang;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            InputStreamReader isReader = new InputStreamReader(System.in);
            BufferedReader bufReader = new BufferedReader(isReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String inputStr;
                if ((inputStr = bufReader.readLine()) != null) {
                    sb.append(inputStr).append("\n");
                }
                else {
                    System.out.println(new EventCompiler().compile(sb.toString()));
                    return;
                }
            }
        }
        else {
            File f = new File(args[0]);
            if (!f.exists()) {
                System.err.println("File '" + args[0] + "' not found!");
            }
            System.out.println(new EventCompiler().compile(readFile(f.getAbsolutePath(), StandardCharsets.UTF_8)));
        }
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
