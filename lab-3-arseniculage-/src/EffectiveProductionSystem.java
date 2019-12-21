package com.arseniculage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
            choice = "";

            do {
                choice = scan.nextLine();
            }
            while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("usr") && !choice.equals("Exit"));


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

                case("2"): {

                    int num = 0, time = 0, sel = 0;

                    System.out.println("Parts amount in order:");
                    num = scan.nextInt();
                    Request request = new Request();
                    for (int i = 0; i < num; i++) {
                        System.out.println("Instrument list:");
                        for (int j = 0; j < instruments.size(); j++) {
                            System.out.printf(j + 1 + " " + instruments.get(j).getName() + "  Amount: " + instruments.get(j).getCount() + "\n");
                        }
                        while (sel <= 0 || sel > instruments.size()) {
                            System.out.println("Choose instrument:");
                            sel = scan.nextInt();
                        }

                        System.out.println("Enter usage time: ");

                        time = scan.nextInt();
                        Instrument inst = new Instrument(instruments.get(sel - 1).name,instruments.get(sel - 1).count);
                        request.addPart(inst, time);
                        request.getParts();

                    }
                    requests.add(request);
                    break;
                }
                case("3"):
                    int curtime = 1;
                    List<RequestPart> pending = new ArrayList<>(requests.size());
                    boolean[] gate = new boolean[requests.size()];
                    boolean[] fullfill = new boolean[requests.size()];
                    boolean ini_skipper = Boolean.FALSE;
                    int ini_reqests_size = requests.size();
                    for (int i=0; i<requests.size();i++) {
                        gate[i] = Boolean.TRUE;
                        fullfill[i] = Boolean.FALSE;
                    }
                    while (!requests.isEmpty()||!pending.isEmpty()) {

                        for (int reqid =0; reqid<requests.size(); reqid++)
                        {
                            if (requests.get(reqid).getParts().isEmpty())//Check if request is fullfilled
                                fullfill[reqid]= Boolean.TRUE;

                        }

                        for (int pend_id = 0; pend_id < ini_reqests_size; pend_id++)//checking if anyone finished
                        {
                            if (!gate[pend_id]&&ini_skipper&&(pending.get(pend_id).getStartTime() + pending.get(pend_id).getTime() == curtime)) {


                                gate[pend_id] = Boolean.TRUE;//remove a pend gate

                                for (Instrument instrument : instruments) {
                                    if (instrument.getName().equals(pending.get(pend_id).getInstrument().getName())) //find instrument by name
                                    {
                                        instrument.setCount(instrument.getCount() + 1); // return the instrument
                                    }
                                }
                                pending.remove(pend_id); // remove a pend

                            }
                        }
                        for (int request_id = 0; request_id < requests.size(); request_id++) {

                            //Pending requests instrument reserving

                            if (!fullfill[request_id]&&gate[request_id]) {
                                ini_skipper = Boolean.TRUE; // first time skipper to avoid iob exception
                                gate[request_id] = Boolean.FALSE;//Gate closed

                                RequestPart curpart = requests.get(request_id).getPart();//Part is now used
                                requests.remove(curpart);//remove part from request
                                curpart.setStartTime(curtime);
                                pending.add(request_id, curpart);
                                //Need to remove one part from avail. list
                                for (Instrument instrument : instruments) {
                                    if (instrument.getName().equals(curpart.getInstrument().getName())) //find instrument by name
                                    {
                                        instrument.setCount(instrument.getCount() - 1); // take the instrument
                                    }
                                }
                                System.out.println("Instrument: " + curpart.getInstrument().getName() + " assigned to process " + request_id + " at " + curtime + " until " + (curtime + curpart.getTime()));
                            }
                        }

                        Boolean chkr = Boolean.TRUE;
                        for (int i=0; i<requests.size();i++) {
                            chkr = chkr&&(gate[i]&&fullfill[i]);
                        }
                        if (chkr) break;

                        curtime++;
                    }
                    System.out.println("Total time = "+curtime);
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
        return;
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
                    int curtime = 1;
                    List<RequestPart> pending = new ArrayList<>(requests.size());
                    boolean[] gate = new boolean[requests.size()];
                    boolean[] fullfill = new boolean[requests.size()];
                    boolean ini_skipper = Boolean.FALSE;
                    int ini_reqests_size = requests.size();
                    for (int i=0; i<requests.size();i++) {
                        gate[i] = Boolean.TRUE;
                        fullfill[i] = Boolean.FALSE;
                    }
                    while (!requests.isEmpty()||!pending.isEmpty()) {

                        for (int reqid =0; reqid<requests.size(); reqid++)
                        {
                            if (requests.get(reqid).getParts().isEmpty())//Check if request is fullfilled
                                fullfill[reqid]= Boolean.TRUE;

                        }

                        for (int pend_id = 0; pend_id < ini_reqests_size; pend_id++)//checking if anyone finished
                        {
                            if (!gate[pend_id]&&ini_skipper&&(pending.get(pend_id).getStartTime() + pending.get(pend_id).getTime() == curtime)) {


                                gate[pend_id] = Boolean.TRUE;//remove a pend gate

                                for (Instrument instrument : instruments) {
                                    if (instrument.getName().equals(pending.get(pend_id).getInstrument().getName())) //find instrument by name
                                    {
                                        instrument.setCount(instrument.getCount() + 1); // return the instrument
                                    }
                                }
                                pending.remove(pend_id); // remove a pend

                            }
                        }
                        for (int request_id = 0; request_id < requests.size(); request_id++) {

                            //Pending requests instrument reserving

                            if (!fullfill[request_id]&&gate[request_id]) {
                                ini_skipper = Boolean.TRUE; // first time skipper to avoid iob exception
                                gate[request_id] = Boolean.FALSE;//Gate closed

                                RequestPart curpart = requests.get(request_id).getPart();//Part is now used
                                requests.remove(curpart);//remove part from request
                                curpart.setStartTime(curtime);
                                pending.add(request_id, curpart);
                                //Need to remove one part from avail. list
                                for (Instrument instrument : instruments) {
                                    if (instrument.getName().equals(curpart.getInstrument().getName())) //find instrument by name
                                    {
                                        instrument.setCount(instrument.getCount() - 1); // take the instrument
                                    }
                                }
                                System.out.println("Instrument: " + curpart.getInstrument().getName() + " assigned to process " + request_id + " at " + curtime + " until " + (curtime + curpart.getTime()));
                            }
                        }

                        Boolean chkr = Boolean.TRUE;
                        for (int i=0; i<requests.size();i++) {
                            chkr = chkr&&(gate[i]&&fullfill[i]);
                        }
                        if (chkr) break;

                        curtime++;
                    }
                    System.out.println("Total time = "+curtime);
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
