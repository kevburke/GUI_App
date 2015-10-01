package ClientServerProg;
/**
 * Created by g00295140 on 17/09/2015.
 */
//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Object.*;
import java.util.*;
import java.util.List;

public class GUI extends JPanel {

    File f;
    private JButton load;
    private JButton reverse;
    private JButton revWord;
    private JButton count;
    private JTextArea loadField;
    private JTextArea reverseField;
    private JTextArea revWordField;
    private JTextArea countField;
    private String reverseTwoWords;

    public GUI() {
        //construct components
        load = new JButton("Load");
        reverse = new JButton("Reverse");
        revWord = new JButton("RevWord");
        count = new JButton("Count");
        loadField = new JTextArea(5, 5);
        loadField.setLineWrap(true);
        loadField.setWrapStyleWord(true);
        reverseField = new JTextArea(5, 5);
        reverseField.setLineWrap(true);
        reverseField.setWrapStyleWord(true);
        revWordField = new JTextArea(5, 5);
        revWordField.setLineWrap(true);
        revWordField.setWrapStyleWord(true);
        countField = new JTextArea(5, 5);
        countField.setLineWrap(true);
        countField.setWrapStyleWord(true);

        //adjust size and set layout
        setPreferredSize(new Dimension(1080, 567));
        setLayout(null);

        //add components
        add(load);
        add(reverse);
        add(revWord);
        add(count);
        add(loadField);
        add(reverseField);
        add(revWordField);
        add(countField);

        //set component bounds (only needed by Absolute Positioning)
        load.setBounds(0, 0, 270, 50);
        reverse.setBounds(270, 0, 270, 50);
        revWord.setBounds(540, 0, 270, 50);
        count.setBounds(810, 0, 270, 50);
        loadField.setBounds(5, 50, 270, 565);
        reverseField.setBounds(270, 50, 270, 565);
        revWordField.setBounds(540, 50, 270, 565);
        countField.setBounds(820, 50, 270, 565);

        /****************************************************************************************
         Load a given file
         ****************************************************************************************/
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    f = chooser.getSelectedFile();

                    BufferedReader br = new BufferedReader(new FileReader(f));
                    int ch;
                    while ((ch = br.read()) != -1) {
                        String nextLine = br.readLine();
                        loadField.append(String.valueOf((char) ch));
                        loadField.append(nextLine);
                        loadField.append("\n");
                    }
                    br.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        /****************************************************************************************
         Reverse the giving file
         ****************************************************************************************/
        reverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader bbr = new BufferedReader(new FileReader(f));
                    String nextLine;
                    while ((nextLine = bbr.readLine()) != null) {
                        System.out.println("Here");
                        String[] words = nextLine.split(" ");
                        System.out.println("Here2");
                        for (int i = words.length - 1; i >= 0; i--) {
                            System.out.println(words[i]);
                            reverseField.append(words[i] + " ");

                            //.append(words[i]).append(" ");
                        }
                        reverseField.append("\n");
                        reverseTwoWords = reverseField.getText();
                    }
                    bbr.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        /*********************************************************************************
         *              Reverse every two words of the given file
         ********************************************************************************/
        revWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputStream is = new ByteArrayInputStream(reverseTwoWords.getBytes());
                    BufferedReader bbbr = new BufferedReader(new InputStreamReader(is));
                    String nextLine;
                    while ((nextLine = bbbr.readLine()) != null) {
                        System.out.println("Here3");
                        String[] words = nextLine.split(" ");
                        System.out.println("Here4");
                        String temp;
                        int i=0;

                        while(i < words.length  ) {

                            System.out.println("Here5");
                            System.out.println("i is" + i);
                            if (words.length % 2 == 0) {
                                //even length array
                                if( i >= words.length-1) {      //value of i = last value in array
                                    revWordField.append("\n");   //last value in array just print it out
                                    i=+2;
                                }
                                {
                                    System.out.println("HereNext");
                                    temp = words[i];                        //store value i in temp
                                    words[i] = words[i + 1];                //store value next i value in i
                                    words[i + 1] = temp;                    //but value i (stored in temp) into i +1

                                    System.out.println("Here6");
                                    // System.out.println(words[i]);
                                    revWordField.append(words[i] + " " + words[i + 1] + " ");
                                }

                                i = i + 2;                          //i gets moved on two places
                                if( i >= words.length-1) {      //value of i = last value in array
                                    revWordField.append("\n");   //last value in array just print it out
                                    //i=+2;
                                }
                            } else {
                                if (words.length % 2 != 0) {
                                    if (words[i] == words[words.length - 1]) {      //value of i = last value in array
                                        revWordField.append(words[i] + "\n");   //last value in array just print it out
                                        i = i + 2;
                                    } else {
                                        temp = words[i];                        //store value i in temp
                                        words[i] = words[i + 1];                //store value next i value in i
                                        words[i + 1] = temp;                    //but value i (stored in temp) into i +1
                                        revWordField.append(words[i] + " " + words[i + 1] + " ");
                                        i = i + 2;                              //i gets moved on two places

                                    }
                                }
                            }
                        }
                    }
                    bbbr.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        /****************************************************************************************
         *              Count Occurances of Words
         ****************************************************************************************/
        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader bbr = new BufferedReader(new FileReader(f));
                    String nextLine;
                    String completeFile = "";

                    while ((nextLine = bbr.readLine()) != null) {
                        System.out.println("Here");
                        completeFile += nextLine + " ";
                        //String[] fileSplit = completeFile.split(" ");
                    }
                    System.out.println(completeFile); //total file
                    //completeFile = completeFile.replace("\n", " ").replace("\r", " ");
                    String[] fileSplit = completeFile.split(" ");//split on space full
                    int count =0;                                                              //stop return

                    List<String> list = new ArrayList<>();
                    for(String s:fileSplit){
                        if(!list.contains(s)){
                            list.add(s);
                            System.out.println(s);
                        }
                    }
                    System.out.println(list.size());


                    for(int ii =0; ii<list.size()-1;ii++){          //-1 doesn't print the hidden character in the file
                        for(int j=0; j<fileSplit.length;j++){
                            if(list.get(ii).equalsIgnoreCase(fileSplit[j])){
                                count++;

                            }
                        }
                        countField.append(list.get(ii) + ":" + " " + count + "\n");

                        count =0;
                    }
                }catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setVisible(true);

    }
}