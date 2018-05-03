package AutoFill;

import java.io.*;

public class History {

    PrintWriter writer;
    public DLB historyDLB;
    private String fileName =  "user_history.txt";
    File file = new File(fileName);
    BufferedWriter bw = null;
    FileWriter fw = null;

    public History()
    {
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
                historyDLB = new DLB();
            }
            else
            {
                historyDLB = new DLB(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add(String word)
    {
        try
        {
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            PrintWriter out = null;
            if ( file.exists() && !file.isDirectory() ) {
                out = new PrintWriter(new FileOutputStream(new File(fileName), true));
            }
            else {
                out = new PrintWriter(fileName);
            }
            out.append(word);
            out.println();
            out.close();
            historyDLB.incorporate(word);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}
