package com.arseniculage;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File in =  new File("Code.java");
        if (in.exists())
        {
            String fileName = "Code.java";
            String content = Files.lines(Paths.get(fileName));
            System.out.println(content);
	    double x = Math.pow(4, 1.0/2); 
            x = Math.pow(4, 2); 
            x = Math.sqrt(x); 
            x = Math.abs(-4);
            int borya = Math.abs(-2);
            int vitya = Integer.parseInt(\"5862\");
            String s = Integer.toString(vitya);
        }
    }
}
