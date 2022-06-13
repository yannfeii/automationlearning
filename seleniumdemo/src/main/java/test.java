import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class test {

    public static class Main{

        static Scanner cin = new Scanner(System.in);
        static PrintWriter out = new PrintWriter(System.out);

        public static void main(String[] args) throws IOException {
            Vector<Integer> vector = new Vector<Integer>();
            Vector<Integer> vectorCopy = new Vector<Integer>();

            vector.addElement(4);
            vector.addElement(1);
            vector.addElement(5);
            vector.addElement(7);
            vector.addElement(5);
            vector.addElement(1);
            vector.addElement(2);
            vector.addElement(6);
            vector.addElement(3);
            vector.addElement(6);


            int input = cin.nextInt();
            for (int i=0; i< 50; i++) {
                int  value= vector.get(input);
                if (!vectorCopy.contains(value)){
                    vectorCopy.add(value);
                }

                int mod = (input + value) % 10;
                System.out.print("->"+mod);
                input = mod;
                if (vectorCopy.containsAll(vector) && mod == 0) {
                    System.out.println("return true in 50 foreach");
                    break;
                }
            }
        }

    }
}
