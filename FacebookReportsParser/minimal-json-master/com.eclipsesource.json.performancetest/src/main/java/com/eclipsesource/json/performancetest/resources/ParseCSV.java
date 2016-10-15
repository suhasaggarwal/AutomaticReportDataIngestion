import java.io.BufferedReader;
import java.io.FileReader;

public class ParseCSV{

    public static void main(String[] args) throws Exception {
                String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader("test.csv"));
        String line = br.readLine();
        while(line!=null){
             String[] b = line.split(splitBy);
             System.out.println(b[0]);
        }
        br.close();

  }
}