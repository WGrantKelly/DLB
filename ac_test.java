package AutoFill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ac_test
{
        public static void main(String[] args)
        {
            long startTime = 0;
            boolean exit = false;
            int choice = 0;
            ArrayList<String> comboList = new ArrayList();
            Scanner in = new Scanner(System.in);
            File file = new File("C:\\Users\\Grant\\Documents\\1501\\Project1\\dictionary.txt");
            DLB trie = new DLB(file);
            History hist = new History();
            StringBuilder mainSB = new StringBuilder();

            //System.out.println("Trie Created!");
            //trie.print();

            System.out.print("***\nInstructions:\nEnter a character to get word suggestions.\n" +
                    "Enter a number from 1-5 to make a autofill selection\n" +
                    "Enter '$' to denote that the current word is what you wanted to type\n" +
                    "Enter '!' to terminate the program \n***\n\n");
            long estimatedTime = 0;
            while(!exit)
            {
                // ... the code being measured ...

                System.out.print("Please enter input: ");
                String symbIn = in.nextLine();
                int asciiVal = (int)symbIn.charAt(0);
                switch(asciiVal)
                {
                    case(33):
                        startTime = System.nanoTime();
                        //Exclamation point
                        exit = true;
                        printTime(startTime);

                        break;
                    case(36):
                        startTime = System.nanoTime();
                        //Dollar Sign
                        hist.add(mainSB.toString());
                        mainSB = new StringBuilder();
                        printTime(startTime);


                        break;
                    case(49):
                        startTime = System.nanoTime();
                        choice = 1;
                        mainSB = new StringBuilder();
                        hist.add(picked(--choice, comboList));
                        printTime(startTime);


                        break;
                    case(50):
                        startTime = System.nanoTime();
                        choice = 2;
                        mainSB = new StringBuilder();
                        hist.add(picked(--choice, comboList));
                        printTime(startTime);


                        break;
                    case(51):
                        startTime = System.nanoTime();
                        choice = 3;
                        mainSB = new StringBuilder();
                        hist.add(picked(--choice, comboList));
                        printTime(startTime);


                        break;
                    case(52):
                        startTime = System.nanoTime();
                        choice = 4;
                        mainSB = new StringBuilder();
                        hist.add(picked(--choice, comboList));
                        printTime(startTime);
                        break;
                    case(53):
                        startTime = System.nanoTime();
                        choice = 5;
                        mainSB = new StringBuilder();
                        hist.add(picked(--choice, comboList));
                        printTime(startTime);


                        break;
                    default:
                        startTime = System.nanoTime();
                        //any letter
                        mainSB.append(symbIn);
                        ArrayList<String> histList = hist.historyDLB.suggest(mainSB.toString());
                        ArrayList<String> regList = trie.suggest(mainSB.toString());
                        comboList.clear();
                        for(String string: histList)
                        {
                            if(!comboList.contains(string))
                            {
                                comboList.add(string);
                            }
                        }
                        for (String string: regList)
                        {
                            if(!comboList.contains(string))
                            {
                                comboList.add(string);
                            }
                        }
                        printSuggestions(comboList);
                        printTime(startTime);

                        break;
                }
            }
            System.exit(0);
        }
        public static void printSuggestions(ArrayList<String> list)
        {
            System.out.println("\nPredictions: ");
            if(list.size()<5&&list.size()>0)
            {
                for (int i = 0; i < list.size();i++) {
                    System.out.print("\t(" + (i+1) + ") " + list.get(i));
                }
            }
            else if(list.size() >= 5)
            {
                for (int i = 0; i < 5; i++) {
                    System.out.print("\t(" + (i+1) + ") " + list.get(i));
                }
            }
            else if(list.size()<1)
            {
                System.out.print("\nNo Predictions Available");
            }
            System.out.println("\n");
        }
        public static void printTime(long start)
        {
            long estimatedTime = System.nanoTime() - start;
            double time = estimatedTime/1000000000.0;

            System.out.println("("+time+" s)");
        }
        public static String picked(int choice, ArrayList<String> comboList)
        {
            String strChoice = comboList.get(choice);
            System.out.println("\nWORD COMPLETED:  "+comboList.get(choice)+"\n");
            comboList.clear();
            return strChoice;
        }

}
