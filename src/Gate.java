import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Gate {

    final private int MAX_PEOPLE = 50;
    private String gate;
    private RandomAccessFile file;


    public Gate(String location) throws FileNotFoundException {
        file = new RandomAccessFile("admin.txt", "rw");
        this.gate = location;
    }
    public void counting(){

        for (int i = 0; i < MAX_PEOPLE; i++) {
            System.out.println("\nGate counter: " + (i+1));
            try {
                int count = 0;
                if (gate.equals("gate_bottom")){
                    file.seek(0);
                    count = file.read();
                    System.out.println("File counter: " + count);

                    count += 1;
                    Thread.sleep(200);
                    file.seek(0);
                }
                else if(gate.equals("gate_top")){
                    file.seek(1);
                    count = file.read();
                    System.out.println("File counter: " + count);

                    count += 1;
                    Thread.sleep(200);
                    file.seek(1);
                }

                file.write((byte) count);

                if (i == (MAX_PEOPLE-1)){
                    file.close();
                }
            }
            catch (IOException e) {
                System.out.println("File access error");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
