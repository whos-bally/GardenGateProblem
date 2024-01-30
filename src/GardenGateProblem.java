import java.io.*;

public class GardenGateProblem {

    static public void main(String[] args) throws FileNotFoundException {
        RandomAccessFile admin;
        byte init[] = {0,0};

        if (args.length != 1)
            System.err.println("usage: java {gate_bottom,gate_top}");
        else {
            if (args[0].compareToIgnoreCase("gate_bottom") == 0) {
                System.out.println("running the bottom gate");
                try {
                    admin = new RandomAccessFile("admin.txt", "rw");
                    admin.seek(0);
                    admin.write(init);
                    admin.close();
                } catch (IOException e) {
                    System.out.println("something wrong with file access" + e);
                }
            } else
                System.out.println("running the top gate");

            Gate counter;

            if (args[0].equals("gate_bottom")) {
                counter = new Gate("gate_bottom");
                counter.counting();
            } else if (args[0].equals("gate_top")) {
                counter = new Gate("gate_top");
                counter.counting();
            }
        }
    }
}