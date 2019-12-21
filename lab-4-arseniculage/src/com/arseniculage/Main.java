package com.arseniculage;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        String fileSeparator = System.getProperty("file.separator");
        try {/*
            //Задание 1
            //Упражнение 1. Исследовать возможности класса File по созданию file (пустых) и папок программой.
            //Применение конструктора и метода.
            System.out.println("TASK 1.1\n");
            File file1 = new File("C:/Users/User/Desktop/Developing/Java/lab-4-arseniculage/src"+fileSeparator+"MyFile1.txt");
            if (file1.createNewFile()) System.out.println(Boolean.TRUE);
            File file2 = new File("D:"+fileSeparator+"MyFile2.txt");
            if (file2.createNewFile()) System.out.println(Boolean.TRUE);
            File file3 = new File("MyFile3.txt");
            if (file3.createNewFile()) System.out.println(Boolean.TRUE);
            File file4 = new File("C:/Users/User/Desktop/Developing/Java/lab-4-arseniculage"+fileSeparator+"first");
            file4.mkdir();
            file4 = new File("C:/Users/User/Desktop/Developing/Java/lab-4-arseniculage"+fileSeparator+"first"+fileSeparator+"second");
            file4.mkdir();
            file4 = new File("C:/Users/User/Desktop/Developing/Java/lab-4-arseniculage"+fileSeparator+"first"+fileSeparator+"second"+fileSeparator+"third");
            if (file4.mkdir())
            System.out.println(Boolean.TRUE);
            //Упражнение 2. Получить параметры file методами класса File
            System.out.println("TASK 1.2\n");
            if (file1.isFile()) {
                System.out.println("File " + file1.getName() + ", file1 in catalog " + file1.getParent() + " is a file");
            } else {
                System.out.println("File1 is not a file");
            }
            if (file2.isFile()) {
                System.out.println("File " + file2.getName() + ", file2 in catalog " + file2.getParent() + " is a file");
            } else {
                System.out.println("File2 is not a file");
            }
            if (file3.isFile()) {
                System.out.println("File " + file3.getName() + ", file3 in catalog " + file3.getParent() + " is a file");
            } else {
                System.out.println("File3 is not a file");
            }

            if (file4.isDirectory()) {
                System.out.println("File " + file4.getName() + ", file4 is a catalog");
            } else {
                System.out.println("File4 is not a catalog");
            }
            System.out.println();

            String[] files = new File(new File("").getAbsolutePath()).list();
            if (Arrays.asList(files).contains("MyFile1.txt"))
                System.out.println("MyFile1.txt exists");
            else
                System.out.println("MyFile1.txt nonexistent");

            System.out.println(file1.getAbsolutePath());
            System.out.println(file4.getAbsolutePath());

            System.out.println("Sizeof file " + file1.getName() + ": " + file1.length() + " bytes");
            System.out.println("Sizeof file " + file2.getName() + ": " + file2.length() + " bytes");
            System.out.println("Sizeof file " + file3.getName() + ": " + file3.length() + " bytes");
            System.out.println("Sizeof catalog " + file4.getName() + ": " + file4.length() + " bytes");
            System.out.println();

            //Упражнение 3. Модификация fileовой структуры приложения средствами класса File.
            System.out.println("TASK 1.3\n");
            File folder = new File("C:/Users/User/Desktop/Developing/Java/lab-4-arseniculage/catalogus1");

            folder.mkdir();
            System.out.println("Catalog contains:");
            for (String o: files)
                System.out.println(o);
            System.out.println();

            int i = 0;
            File[] folders = new File(new File("").getAbsolutePath()).listFiles();
            for (File o: folders) {
                System.out.println(o.getName());
                if (o.isDirectory())
                    i++;
            }
            System.out.println("App folder contains " + i + " subfolders");

            file1.delete();
            file2.delete();
            file3.delete();
            file4.delete();
            folder.delete();

            //Задание 2
            System.out.println("TASK 2\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter file name: ");
            String fileName = sc.nextLine();
            FileManager.createFile(fileName);
            String choice = "";
            while (!choice.equals("Exit")) {
                showMenu();
                choice = sc.nextLine();

                switch(choice) {
                    case("1"):
                        FileManager.addFilm(fileName);
                        break;
                    case("2"):
                        LinkedList<Film> list = new LinkedList<>();
                        FileManager.outFilms(fileName, list);
                        break;
                    case("3"):
                        System.out.println("Enter director");
                        String director = sc.nextLine();
                        LinkedList<Film> listDir = new LinkedList<>();
                        FileManager.sameDirector(fileName, listDir, director.trim());
                        System.out.println("Director's films: " + director + ":\r\n");
                        for (Film film: listDir) {
                            System.out.println("Film: " + film.getTitle() + "\r\nStudio: " + film.getStudio() +
                                    "\r\nYear: " + film.getYear() + "\r\nDirector: " + film.getDirector() +
                                    "\r\nStarring: " + film.getLeadRole() + "\r\n");
                        }
                        break;
                    case("4"):
                        FileManager.delOldest(fileName);
                        break;
                    case("Exit"):
                        break;
                    default:
                        System.out.println("Input unresolved");
                }


            }*/
            //TASK 3.1
            System.out.println("TASK 3.1");
            FileInputStream t1 = new FileInputStream("T1.txt");
            FileOutputStream t2 = new FileOutputStream("T2.txt");
            byte[] buffer =  new byte[t1.available()];
            t1.read(buffer,0,buffer.length);
            t2.write(buffer,0,buffer.length);

            t2.write(t1.read());
            t1.close();
            t2.close();

            //TASK 3.2
            System.out.println("TASK 3.2");
            char[] buf = new char [128];
            BufferedReader inb = new BufferedReader(new FileReader("A.txt"),128);
            BufferedWriter outb = new BufferedWriter(new FileWriter(("B.txt")), 128);

            while (inb.read()!= -1)
            {
                inb.read(buf);
                outb.write(buf);

            }

            inb.close();
            outb.close();

            //TASK 3.3
            BufferedReader in_sec = new BufferedReader(new InputStreamReader(new FileInputStream("AA.txt"), Charset.forName("cp1251")));
            String s1;
            StringBuilder s1_ = new StringBuilder();
            while((s1=in_sec.readLine())!=null)
            {
                s1_.append(s1);
                s1_.append("\n");
            }
            System.out.println(s1_.toString());

            s1_ = new StringBuilder();

            BufferedReader in_sec2 = new BufferedReader(new InputStreamReader(new FileInputStream("AA.txt"), Charset.forName("UTF-8")));
            while((s1=in_sec2.readLine())!=null)
            {
                s1_.append(s1);
                s1_.append("\n");
            }
            System.out.println(s1_.toString());






        } catch (IOException e) {
            System.out.println("Wrong input");
        } catch(Exception e) {
            System.out.println("Undefined error");
        }





    }

    public static void showMenu() {
        System.out.println("1. Add film\r\n" +
                "2. Film list\r\n" +
                "3. Find films by director\r\n" +
                "4. Delete oldest film\r\n" +
                "Eixt. Exit");
    }

}