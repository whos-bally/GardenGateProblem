import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Gate {

    final private int MAX_PEOPLE = 50;
    private RandomAccessFile file;


    public Gate() throws FileNotFoundException {
        file = new RandomAccessFile("admin.txt", "rw");
    }
    public void counting(){

        byte[] init = {0};

        for (int i = 0; i < MAX_PEOPLE; i++) {
            System.out.println("Internal counter: " + (i+1));

            try {

                file.seek(0);
                int count = file.read();

                System.out.println("File counter: " + count);
                count += 1;

                Thread.sleep(200);

                file.seek(0);
                file.write((byte) count);




                if (i == MAX_PEOPLE){
                    file.close();
                }
            }
            catch (InterruptedException e) {
                System.out.println("Something went wrong");
            }
            catch (IOException e) {
                System.out.println("File access error");
            }
        }
    }


}
