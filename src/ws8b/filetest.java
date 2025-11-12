package ws8b;

import java.io.*;

public class filetest {
    public static void main(String[]args){
        File file = new File("hello.txt");
        try(var writer = new BufferedWriter(new FileWriter(file))){
            writer.write("hello");
            writer.newLine();
            writer.write("world\n");
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        try(var reader = new BufferedReader(new FileReader(file))){
            for(String line = reader.readLine(); line!= null ; line = reader.readLine()){
                System.out.println(line);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
