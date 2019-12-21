package com.arseniculage;

import java.util.*;

class SortByCost implements Comparator<RequestPart> {
    public int compare(RequestPart a, RequestPart b) {
        if ( a.time < b.time ) return -1;
        else if ( a.time == b.time ) return 0;
        else return 1;
    }
}

public class EffectiveProductionSystem {
    private static List<User> users = new ArrayList<>();
    private static List<Request> requests = new ArrayList<>();
    private static List<Instrument> instruments = new ArrayList<>();
    private static User currentUser;
    public static void main(String[] args) {
        String choice = "";
        Scanner scan = new Scanner(System.in);
        String name, login, password, repeation;
        while (!choice.equals("0") && !choice.equals("1") && !choice.equals("2")) {
            System.out.println("1. Register\r\n" +
                    "2. Login\r\n");

            choice = scan.nextLine();

            switch(choice) {

                case("1"):
                    System.out.println("Username:");
                    name = scan.nextLine();
                    System.out.println("Login:");
                    login = scan.nextLine();
                    System.out.println("Password:");
                    password = scan.nextLine();
                    System.out.println("Repeat password:");
                    repeation = scan.nextLine();
                    try {
                        addUser(name, login, password, repeation);
                        currentUser = findUser(login);
                        System.out.printf("User "+name+" added\r\n");
                        if (currentUser instanceof Admin) {
                            adminMenu();
                        }
                        else {
                            clientMenu();
                        }
                    } catch (InterruptedException e) {
                        choice = "";
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        choice = "";
                    }
                    break;

                case("2"):
                    System.out.println("Login:");
                    login = scan.nextLine();
                    System.out.println("Password:");
                    password = scan.nextLine();
                    try {
                        User temp = findUser(login);
                        if (temp.enter(login, password)) {
                            currentUser = temp;
                            if (currentUser instanceof Admin) {
                                adminMenu();
                            }
                            else {
                                clientMenu();
                            }
                        }
                    } catch (InterruptedException e) {
                        choice = "";
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        choice = "";
                    }
                    break;

                case("Exit"):
                    return;


                default:
                    System.out.println("Input unresolved");
                    break;
            }
        }
    }
    private static void adminMenu() throws InterruptedException {
        String choice = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Admin mode.\nWelcome, " + currentUser.getName());

        while (!choice.equals("Exit")) {
            System.out.println("1. Add equipment\r\n" +
                    "2. Add order\r\n" +
                    "3. Create equipment load plan\r\n" +
                    "usr. Change user\r\n" );

            choice = scan.nextLine();

            switch (choice) {

                case("1"):
                    System.out.println("Enter equipment name:");
                    String instrName = scan.nextLine();
                    int count = 0;
                    while (count <= 0) {
                        System.out.println("Amount of instruments: ");
                        count = scan.nextInt();
                    }
                    instruments.add(new Instrument(instrName, count));
                    break;

                case("2"):
                    Request request = new Request();
                    int num = 0, time = 0, sel = 0;
                    while (num <= 0) {
                        System.out.println("Parts amount in order:");
                        num = scan.nextInt();
                    }
                    for (int i = 0; i < num; i++) {
                        System.out.println("Instrument list:");
                        for (int j = 0; j < instruments.size(); j++) {
                            System.out.printf(j + 1 +" "+ instruments.get(j).getName() +  "  Amount: " + instruments.get(j).getCount()+"\n");
                        }
                        while (sel <= 0 || sel > instruments.size()) {
                            System.out.println("Choose instrument:");
                            sel = scan.nextInt();
                        }
                        Instrument instr = instruments.get(sel - 1);
                        System.out.println("Enter usage time: ");
                        time = scan.nextInt();
                        request.addPart(instr,time);

                    }
                    requests.add(request);
                    break;

                case("3"):
                    int curtime = 0;
                    List<RequestPart> pending = new ArrayList<>();
                    boolean[] gate = new boolean[requests.size()];
                    for (int i=0; i<requests.size()-1;i++)
                        gate[i] = Boolean.TRUE;

                    while (!requests.isEmpty())
                        for (int i=0; i<requests.size()-1;i++)
                            if()
                            pending.add(i.getPart());


                        curtime++;
                    break;

                case("usr"):
                    throw new InterruptedException();

                case("Exit"):
                    break;

                default:
                    System.out.println("Input unresolved");
                    break;
            }
        }
    }
    private static void clientMenu() throws InterruptedException {
        String choice = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome , " + currentUser.getName());

        while (!choice.equals("0")) {
            System.out.println("1. Add order\r\n" +
                    "2. Create equipment load plan \r\n" +
                    "usr. Change user\r\n" +
                    "0. Выход");

            choice = scan.nextLine();

            switch (choice) {

                case("1"):
                    Request request = new Request();
                    int num = 0, time = 0, sel = 0;
                    while (num <= 0) {
                        System.out.println("Parts amount in order:");
                        num = scan.nextInt();
                    }
                    for (int i = 0; i < num; i++) {
                        System.out.println("Instrument list:");
                        for (int j = 0; j < instruments.size(); j++) {
                            System.out.printf(j + 1 +" "+ instruments.get(j).getName() +  "  Amount: " + instruments.get(j).getCount()+"\n");
                        }
                        while (sel <= 0 || sel > instruments.size()) {
                            System.out.println("Choose instrument:");
                            sel = scan.nextInt();
                        }
                        Instrument instr = instruments.get(sel - 1);
                        System.out.println("Enter usage time: ");
                        time = scan.nextInt();
                        request.addPart(instr,time);

                    }
                    requests.add(request);
                    break;

                case("2"):
                    break;

                case("usr"):
                    throw new InterruptedException();

                case("Exit"):
                    break;

                default:
                    System.out.println("Input unresolved");
                    break;
            }
        }
    }

    private static void addUser(String name, String login, String password, String repeation) throws Exception {
        for (User user : users)
            if (user.getLogin().equals(login))
                throw new Exception("Login already exists");
        if (!password.equals(repeation))
            throw new Exception("Password mismatch");
        if (users.isEmpty())
            users.add(new Admin(name, login, password));
        else
            users.add(new Client(name, login, password));
    }

    private static User findUser(String login) throws Exception {
        for (User user : users)
            if (user.getLogin().equals(login))
                return user;
        throw new Exception("Login not found");
    }

    private static void processRequests() {

    }
}
