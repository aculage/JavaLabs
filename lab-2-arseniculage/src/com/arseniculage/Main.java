package com.arseniculage;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Task 1\n2. Task 2\n3. Task 3");
        int task_switcher = in.nextInt();


        switch (task_switcher) {
            case (1)://read code, find if math included, which func used, etc.
                String fileName = "Code.java";
                String input = "";
                try (FileReader reader = new FileReader(fileName)) {
                    // читаем посимвольно
                    int c;
                    while ((c = reader.read()) != -1) {

                        input += ((char) c);
                    }
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
                //24 строки кода на считывание файла, в рот оно еб**ь
                Task1 task1 = new Task1(input);

                //IMPLEMENT MENU
                System.out.println("1. Task 1.1\n2. Task 1.2\n3. Task 1.3\n4. Task 1.4");
                int switch_ = in.nextInt();
                switch(switch_) {
                    case 1: System.out.println(task1.chk_for_math()); break;
                    case 2: System.out.println(task1.locate_all_mthfuncs()); break;
                    case 3: System.out.println(task1.variables()); break;
                    case 4: System.out.println(task1.locate_operators()); break;

                    default: System.out.println("Input error");
                }

                break;
            case(2):
                String text = "Uranus' atmosphere is similar to Jupiter's and Saturn's in its primary composition of hydrogen and helium, but it contains more ices such as water, ammonia, and methane, along with traces of other hydrocarbons. " +
                        "It has the coldest planetary atmosphere in the Solar System, with a minimum temperature of 49 K ( −224 °C; −371 °F), and has a complex, layered cloud structure with water thought to make up the lowest clouds and methane the uppermost layer of clouds." +
                        " The interior of Uranus is mainly composed of ices and rock.";
                Task2 task2 = new Task2(text);
                System.out.println("1. Task 2.1\n2. Task 2.2\n3. Task 2.3");
                int switch__ = in.nextInt();

                switch(switch__) {

                    case 1 : System.out.println(task2.t1_insert()); break;

                    case 2 : System.out.println(task2.t2_palindrome()); break;

                    case 3 :
                        in.nextLine();
                        System.out.println("Input a sentence");
                        String str = in.nextLine();
                        System.out.println(task2.t3_append(str));
                        break;

                    default: System.out.println("Input error");

                 }
                break;

            case(3):
                String str = "2aaa'3'bbb'4'";
                Task3 task3 = new Task3(str);
                System.out.println("1. Task 3.1\n2. Task 3.2");
                int switch___ = in.nextInt();
                switch(switch___) {
                    case 1 : System.out.println(task3.t1_check()); break;
                    case 2 : System.out.println(task3.t2_check()); break;
                    default: System.out.println("Input error");
                }
                break;
        }
    }
}
