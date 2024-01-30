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

                    // Check the counter of the bottom gate
                    file.seek(0);
                    count = file.read();
                    System.out.println("Bottom file counter: " + count);

                    // Increment the bottom gate counter
                    count += 1;
                    Thread.sleep(200);

                    // Check the counter of top gate
                    file.seek(1);
                    int getTopCounter = file.read();
                    System.out.println("getTopCounter: " + getTopCounter);

                    // Has the counter advanced since the last iteration?
                    if (getTopCounter > count){
                        // Set the value of count to be getBottomCounter and increment it
                        count = (getTopCounter + 1);
                    }

                    // Write the bottom gate counter to file
                    file.seek(0);
                }
                else if(gate.equals("gate_top")){

                    // Check the counter of the top gate
                    file.seek(1);
                    count = file.read();
                    System.out.println("Top file counter: " + count);

                    // Increment the top gate counter
                    count += 1;
                    Thread.sleep(200);

                    // Check the counter of the bottom gate
                    file.seek(0);
                    int getBottomCounter = file.read();
                    System.out.println("getBottomCounter: " + getBottomCounter);

                    // Has the counter advanced since the last iteration?
                    if (getBottomCounter > count){
                        // Set the value of count to be getBottomCounter and increment it
                        count = (getBottomCounter + 1);
                    }

                    // Write the top gate counter to file
                    file.seek(1);
                }


                file.write((byte) count);

                // Close file
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
