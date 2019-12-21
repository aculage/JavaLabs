package com.arseniculage;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

public class FileManager {

    public static void createFile(String fileName) {
        String[] files = new File(new File("").getAbsolutePath()).list();
        if (Arrays.asList(files).contains(fileName))
            System.out.println("File " + fileName + " already exists.");
        else {
            try {
                File file1 = new File(fileName);
                file1.createNewFile();
                System.out.println("File created");
            } catch (IOException e) { System.out.println(e.toString()); }
        }
    }

    public static void addFilm(String fileName) {
        try {
            File file1 = new File(fileName);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file1.getAbsolutePath(), true));
            Scanner in = new Scanner(System.in);
            System.out.print("Film name: ");
            String title = in.nextLine();
            System.out.print("Studio name: ");
            String studio = in.nextLine();
            System.out.print("Year of release: ");
            int year = Integer.parseInt(in.nextLine());
            System.out.print("Director's name: ");
            String director = in.nextLine().trim();
            System.out.print("Lead role's surname: ");
            String leadRole = in.nextLine();

            out.writeUTF(title);
            out.writeUTF(studio);
            out.writeInt(year);
            out.writeUTF(director);
            out.writeUTF(leadRole);

            out.flush();
            out.close();

            System.out.println("\r\nFilm added\r\n");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void outFilms(String fileName, LinkedList<Film> list) {
        try {
            File file1 = new File(fileName);
            DataInputStream inp = new DataInputStream(new FileInputStream(file1.getAbsolutePath()));
            while (true) {
                Film film = new Film(inp.readUTF(), inp.readUTF(), inp.readInt(), inp.readUTF(), inp.readUTF());
                list.add(film);
                System.out.println("Film : " + film.getTitle() + "\r\nStudio : " + film.getStudio() +
                        "\r\nYear : " + film.getYear() + "\r\nDirector : " + film.getDirector() +
                        "\r\nLead role : " + film.getLeadRole() + "\r\n");
            }
        } catch (IOException e) {}
    }

    public static void sameDirector(String fileName, LinkedList<Film> list, String director) {
        try {
            File file1 = new File(fileName);
            DataInputStream inp = new DataInputStream(new FileInputStream(file1.getAbsolutePath()));
            while (true) {
                String title = inp.readUTF();
                String studio = inp.readUTF();
                int year = inp.readInt();
                String Director = inp.readUTF();
                String leadRole = inp.readUTF();
                if (Director.equals(director)) {
                    Film film = new Film(title, studio, year, Director, leadRole);
                    list.add(film);
                }
            }
        } catch (IOException e) {}
    }

    public static void delOldest(String fileName) {
        LinkedList<Film> list = new LinkedList<>();
        int i = 0, maxpos = 0, max = 10000;
        try {
            File file1 = new File(fileName);
            DataInputStream inp = new DataInputStream(new FileInputStream(file1.getAbsolutePath()));
            while (true) {
                String title = inp.readUTF();
                String studio = inp.readUTF();
                int year = inp.readInt();
                String Director = inp.readUTF();
                String leadRole = inp.readUTF();
                list.add(new Film(title, studio, year, Director, leadRole));
                if (year <= max) {
                    max = year;
                    maxpos = i;
                }
                i++;
            }
        } catch (IOException e) {}
        Film movie = list.remove(maxpos);
        try {
            File file1 = new File(fileName);
            file1.delete();
            file1.createNewFile();
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file1.getAbsolutePath()));
            for (Film film: list) {
                out.writeUTF(film.getTitle());
                out.writeUTF(film.getStudio());
                out.writeInt(film.getYear());
                out.writeUTF(film.getDirector());
                out.writeUTF(film.getLeadRole());
            }
            out.flush();
            out.close();
            System.out.println("Film \"" + movie.getTitle() + "\" deleted");
        } catch (IOException e) {}
    }
}
