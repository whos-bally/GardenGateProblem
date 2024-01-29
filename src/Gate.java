import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class Gate {

    final private int MAX_PEOPLE = 50;
    private RandomAccessFile file;
    private FileLock lock;


    public Gate() throws FileNotFoundException {
        file = new RandomAccessFile("admin.txt", "rw");
    }
    public void counting(){

        byte[] init = {0};

        for (int i = 0; i < MAX_PEOPLE; i++) {
            System.out.println("Gate counter: " + (i+1));

            try {
                lock = file.getChannel().lock(); // enable the file lock for overwrite protection
                file.seek(0);
                int count = file.read();

                System.out.println("File counter: " + count);
                count += 1;
                Thread.sleep(200);
                file.seek(0);
                file.write((byte) count);
                lock.release(); // release the file lock so other processes can write to the file



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
