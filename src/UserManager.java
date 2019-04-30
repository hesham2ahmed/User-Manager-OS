import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by hesham on 4/27/19.
 */




public class UserManager {
    private Runtime runtime;
    private Process process;
    private BufferedReader input;
    private String result;
    private String line;

    public UserManager() {
        runtime = Runtime.getRuntime();
        process = null;
        line = null;
        input = null;
    }

    public String addUser(String name) {
        if (!searchInFile(name, "users.txt")) {
            try {
                writeInTextFile(name, "users.txt");
                process = runtime.exec("sudo adduser " + name);
                result = "----------The user is created successfully----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }

            return result;
        } else
            return "Sorry the user is exsited before";
    }

    public String createUserWithGroupName(String username, String groupname) {

        if (!searchInFile(username, "users.txt") &&  searchInFile(groupname,"groups.txt")) {
            try {
                writeInTextFile(username, "users.txt");
                process = runtime.exec("sudo useradd -G "+ groupname + " "+ username);
                result = "---------- The user is created successfully ----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }

            return result;
        } else
            return "---------- Sorry! the user is exsited before or the group does not exist ----------";
    }

    public String delUser(String username) {

        if(searchInFile(username,"users.txt")) {
            try {
                removeLine(username, "users.txt");
                process = runtime.exec("sudo deluser " + username);
                result = "---------- The user is deleted successfully ---------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }
            return result;
        }else
            return "---------- Sorry! user doesn't exist to delete ----------";
    }

    public String assignSpecific(String username, ArrayList<String> groups) {
        try {
            String groupname = " ";

            for (int i = 0; i < groups.size(); i++) {
                groupname = groupname + groups.get(i);

                if (i < groups.size() - 1)
                    groupname += ",";
            }
            process = runtime.exec("sudo usermod -a -G" + groupname + " " + username);
            result = "----------The user is addedd successfully to " + groupname + " group----------";
        } catch (Exception e) {
            result = "sorry!";
            return result;
        }
        return result;
    }

    public String creatGroup(String groupname) {
        if (!searchInFile(groupname, "groups.txt")) {
            try {
                writeInTextFile(groupname, "groups.txt");
                process = runtime.exec("sudo groupadd " + groupname);
                result = "----------The group is created successfully----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }
            return result;
        } else
            return "---------- Sorry! the group is exsited before ----------";

    }

    public String delGroup(String groupname) {

        if(searchInFile(groupname,"groups.txt")) {
            try {
                removeLine(groupname,"groups.txt");
                process = runtime.exec("sudo delgroup " + groupname);
                result = "---------- The group is deleted successfully ----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }
            return result;
        }else
            return "---------- Sorry! group doesn't exist to delete ---------- ";
    }


    public String changePassword(String username, String password) {
        if(searchInFile(username,"users.txt")) {
            try {
                process = runtime.exec("echo -e \'" + password + "\\n" + password + "\' | passwod " + username);
                result = "----------The password is updated ----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }
            return result;
        }else
            return "-------- Sorry user doesn't exist ";
    }

    public String changeFullName(String username, String fullname) {
        if(searchInFile(username,"users.txt")) {
            try {
                process = runtime.exec("sudo chfn -f " + fullname + " " + username);
                result = "----------Fullname is updated successfully ----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }
            return result;
        }else
            return "-------- Sorry user doesn't exist ";
    }

    public String changeHomePhone(String username, String homephone) {
        if (searchInFile(username, "users.txt")) {
            try {
                process = runtime.exec("sudo chfn -h " + homephone + " " + username);
                result = "----------Home number is updated successfully ----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }
            return result;
        }else
            return "-------- Sorry user doesn't exist ";
    }


    public String changeRoomNum(String username, String roomnum) {

        try {
            process = runtime.exec("sudo chfn -r " + roomnum + " " + username);
            result = "----------Room number is updated successfully ----------";
        } catch (Exception e) {
            result = "sorry!";
            return result;
        }
        return result;
    }

    public String changeWorkNum(String username, String worknum) {
        if (searchInFile(username, "users.txt")) {
            try {
                process = runtime.exec("sudo chfn -w " + worknum + " " + username);
                result = "----------Work number is updated successfully ----------";
            } catch (Exception e) {
                result = "sorry!";
                return result;
            }
            return result;
        }
        else
            return "-------- Sorry user doesn't exist ";
    }


    public String help(String fpath) {
        try {
            process = runtime.exec("less " + fpath);

            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            result = "";
            while ((line = input.readLine()) != null)
                result += "\n" + line;

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.equals(""))
            result = "no such directory";

        return result;
    }


    public static boolean writeInTextFile(String username, String path) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter((new FileWriter(new File(path), true)));
            writter.println(username);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            writter.close();
        }

    }

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> infile = new ArrayList<>();
        Scanner reader = null;
        try {
            reader = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            return infile;
        }
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            infile.add(line);
        }
        reader.close();
        return infile;
    }

    public static boolean searchInFile(String data, String path) {
        ArrayList<String> infile = null;
        infile = readFile(path);
        for (int i = 0; i < infile.size(); i++) {
            if (infile.get(i).equals(data))
                return true;
        }
        return false;
    }

    public static boolean removeLine(String data, String path) {
        Scanner reader = null;
        PrintWriter writter = null;
        File file = new File(path);
        String line;
        ArrayList<String> infile = new ArrayList<>();

        try {
            reader = new Scanner(file);

            while (reader.hasNext()) {

                line = reader.next();
                if (line.equals(data)){
                    continue;
                }
                infile.add(line);

            }
            reader.close();
            writeInTextFileAfterRemove(infile, path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }private static void writeInTextFileAfterRemove(ArrayList<String> infile, String path) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(new FileWriter(new File(path), false));
            for (int i = 0; i < infile.size(); i++) {
                writter.println(infile.get(i));
            }
        } catch (IOException e) {
        } finally {
            writter.close();
        }
    }


}



