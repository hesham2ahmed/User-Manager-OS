import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by hesham on 4/27/19.
 */



public class Main {
    public static void main(String[] args) {
        UserManager um = new UserManager();
        String hpath = "help/help.txt";
        if (args.length != 0 && (args[0].equals("-h") || args[0].equals("--h") || args[0].equals("--help"))) {

            //TODO
            System.out.println(um.help(hpath));


        } else {

            String username, password, roomnum, fullname, worknum, homenum, groupname;
            ArrayList<String> groups = new ArrayList<>();

            String selection;
            Scanner input = new Scanner(System.in);

            System.out.println("\n\n\t<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<USER MANAGER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            do {

                System.out.println("\nPress 'A' to add a new user\n" +
                        "Press 'B' to delete user.\n" +
                        "Press 'C' to assign a specific user to specific groups.\n" +
                        "Press 'D' to Change information of user.\n" +
                        "Press 'E' to Create group.\n" +
                        "Press 'F' to delete group.\n" +
                        "Enter 'exist' to exist :) .\n");
                selection = input.next().toUpperCase();

                switch (selection) {
// case a
                    case "A":
                        System.out.println("Press '1' to create just a user");
                        System.out.println("Press '2' to create user to specific group note! the group must be Existing");
                        int option = input.nextInt();

                        switch (option) {
                            case 1:
                                System.out.println("\nEnter username");
                                username = input.next();
                                System.out.println("\n" + um.addUser(username));
                                break;

                            case 2:
                                System.out.println("\nEnter username");
                                username = input.next();
                                System.out.println("\nEnter group name");
                                groupname = input.next();
                                System.out.println("\n" + um.createUserWithGroupName(username, groupname));
                                break;

                            default:
                                System.out.println("Error!");
                        }
                        break;
// case b
                    case "B":
                        System.out.println("\nEnter username ");
                        username = input.next();
                        System.out.println("\n" + um.delUser(username));
                        break;
// case c
                    case "C":
                        System.out.println("\nEnter the number of groups");
                        int g = input.nextInt();

                        System.out.println("\nEnter group name or names if more than 1 :) ");
                        while (g > 0) {
                            groups.add(input.next());
                            g--;
                        }

                        System.out.println("\nEnter username");
                        username = input.next();
                        System.out.println("\n" + um.assignSpecific(username, groups));
                        break;

                    case "D":
                        System.out.println("Press '1' to change fullname for specific user");
                        System.out.println("Press '2' to change home phone for specific user");
                        System.out.println("Press '3' to change room number for specific user");
                        System.out.println("Press '4' to change work phone for specific user");
                        System.out.println("Press '5' to change passord for specific user");
                        option = input.nextInt();

                        switch (option) {
                            case 1:
                                System.out.println("Enter username");
                                username = input.next();
                                System.out.println("Enter a new fullname");
                                fullname = input.next();
                                System.out.println(um.changeFullName(username, fullname));
                                break;

                            case 2:
                                System.out.println("Enter username");
                                username = input.next();
                                System.out.println("Enter a new home phone");
                                homenum = input.next();
                                System.out.println(um.changeHomePhone(username, homenum));
                                break;

                            case 3:
                                System.out.println("Enter username");
                                username = input.next();
                                System.out.println("Enter a new room number");
                                roomnum = input.next();
                                System.out.println(um.changeRoomNum(username, roomnum));
                                break;

                            case 4:
                                System.out.println("Enter username");
                                username = input.next();
                                System.out.println("Enter a new work number");
                                worknum = input.next();
                                System.out.println(um.changeWorkNum(username, worknum));
                                break;

                            case 5:
                                System.out.println("Enter username");
                                username = input.next();
                                System.out.println("Enter a new password number");
                                password = input.next();
                                System.out.println(um.changePassword(username, password));
                                break;


                            default:
                                System.out.println("\nError!");
                        }
                        break;


                    case "E":
                        System.out.println("\nEnter the group name you wanna to create !");
                        groupname = input.next();
                        System.out.println("\n" + um.creatGroup(groupname));
                        break;

                    case "F":
                        System.out.println("\nEnter the group name you wanna to delete !");
                        groupname = input.next();
                        System.out.println("\n" + um.delGroup(groupname));
                        break;


                    case "EXIST":
                        break;

                    default:
                        System.out.println("\nError!");
                }
            } while (!selection.equals("EXIST"));

        }
    }
}
