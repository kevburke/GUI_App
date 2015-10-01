package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Kev on 29/09/2015.
 */
public class GUI_2 extends JPanel {

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

    public GUI_2() {
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
        loadField.setBounds(5, 50, 260, 565);
        reverseField.setBounds(275, 50, 260, 565);
        revWordField.setBounds(555, 50, 250, 565);
        countField.setBounds(815, 50, 260, 565);

        /****************************************************************************************
         Load a given file
         ****************************************************************************************/
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                f = chooser.getSelectedFile();

                SwingWorker<String, String> worker = new SwingWorker<String, String>() {

                    protected String doInBackground() throws Exception {
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String nextLine = null;

                        while ((nextLine = br.readLine()) != null) {
                            publish(nextLine);
                            // nextLine = br.readLine();
                        }
                        br.close();
                        return nextLine;
                    }
                   /* protected void done(){
                        try {
                            String test = get();
                            loadField.append(test + "\n");
                            System.out.println(test + " ");
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        } catch (ExecutionException e1) {
                            e1.printStackTrace();
                        }
                    }*/
                    protected void process(List<String> line) {
                        for (int i = 0; i < line.size(); i++) {
                            loadField.append(line.get(i) + "\n"); //
                            // System.out.println(line.get(2));
                        }
                    }
                };
                worker.execute();
            }
        });
        /****************************************************************************************
         Reverse the giving file
         ****************************************************************************************/
        reverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<String[], String>() {
                    protected String[] doInBackground() throws Exception {
                        BufferedReader bbr = new BufferedReader(new FileReader(f));
                        String nextLine;                                    //string to store line
                        String inside = "";                                  //string to send input to publish!!
                        String[] words = new String[0];                     //string array to hold contents nextline
                        while ((nextLine = bbr.readLine()) != null) {       //keep reading lines till end of file
                            words = nextLine.split(" ");                    //split line on spaces

                            for (int i = words.length - 1; i >= 0; i--) {   //read the line backwards
                                // System.out.println(words[i]);
                                inside += words[i] + " ";

                            }
                            publish(inside);                                //send to publish

                            inside = "";                                     //clear string

                        }
                        bbr.close();
                        return words;                                       //never gets returned!!!
                    }
                    protected void process(List<String> bb)                 //
                    {
                        for (int i = 0; i < bb.size(); i++)
                            reverseField.append(bb.get(i) + "\n"); //

                    }

                    protected void done() {
                        reverseTwoWords = reverseField.getText();       //USED IN NEXT FUNCTION
                    }
                }
                        .execute();
            }
        });
        /*********************************************************************************
         *              Reverse every two words of the given file
         ********************************************************************************/
        revWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<Void, String>() {
                    protected Void doInBackground() throws Exception {
                        InputStream is = new ByteArrayInputStream(reverseTwoWords.getBytes());
                        BufferedReader bbbr = new BufferedReader(new InputStreamReader(is));
                        String nextLine;
                       // System.out.println("Here1");
                        while ((nextLine = bbbr.readLine()) != null) {
                           // System.out.println("Here3");
                            String[] words = nextLine.split(" ");
                           // System.out.println("Here4");
                            String temp;
                            int i = 0;

                            while (i < words.length) {
                               // System.out.println("Here5");
                                //System.out.println("i is" + i);
                                if (words.length % 2 == 0) {
                                    //even length array
                                    if (i >= words.length - 1) {      //value of i = last value in array
                                        String aa = "\n";
                                        publish(aa);
                                        i = +2;
                                    }
                                    {
                                        //System.out.println("HereNext");
                                        temp = words[i];                        //store value i in temp
                                        words[i] = words[i + 1];                //store value next i value in i
                                        words[i + 1] = temp;                    //but value i (stored in temp) into i +1
                                        String bb = words[i] + " " + words[i + 1] + " ";
                                        publish(bb);
                                    }
                                    i = i + 2;                          //i gets moved on two places
                                    if (i >= words.length - 1) {      //value of i = last value in array
                                        publish("\n");
                                    }
                                } else {
                                    if (words.length % 2 != 0) {
                                        if (words[i] == words[words.length - 1]) {      //value of i = last value in array
                                            //revWordField.append(words[i] + "\n");
                                            String dd = words[i] + "\n";         //last value in array just publish it out
                                            publish(dd);
                                            i = i + 2;
                                        } else {
                                            temp = words[i];                     //store value i in temp
                                            words[i] = words[i + 1];             //store value next i value in i
                                            words[i + 1] = temp;                 //but value i (stored in temp) into i +1
                                            String cc = words[i] + " " + words[i + 1] + " ";//
                                            publish(cc);
                                            cc = "";
                                            i = i + 2;                              //i gets moved on two places
                                        }
                                    }
                                }
                            }

                        } is.close();                                             //close inputSream
                        bbbr.close();                                           //close buffeeredReader
                        return null;


                    }
                    protected void process(List<String> pp) {
                        for (int i = 0; i < pp.size(); i++)
                            revWordField.append(pp.get(i));
                    }
                }.execute();
            }
        });
        /****************************************************************************************
         *              Count Occurances of Words
         ****************************************************************************************/
        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<Void, String>() {
                    protected Void doInBackground() throws Exception {
                        BufferedReader bbr = new BufferedReader(new FileReader(f));
                        String nextLine;
                        String completeFile = "";

                        while ((nextLine = bbr.readLine()) != null) {
                            //System.out.println("Here");
                            completeFile += nextLine + " ";

                        }
                        //System.out.println(completeFile); //total file
                        String[] fileSplit = completeFile.split(" ");//split on space full
                        int count = 0;                                                              //stop return

                        List<String> list = new ArrayList<>();
                        for (String s : fileSplit) {
                            if (!list.contains(s)) {
                                list.add(s);
                                System.out.println(s);
                            }
                            bbr.close();
                        }
                        //System.out.println(list.size());

                        for (int ii = 0; ii < list.size()-1; ii++) {          //-1 doesn't print the hidden character in the file
                            for (int j = 0; j < fileSplit.length; j++) {
                                if (list.get(ii).equalsIgnoreCase(fileSplit[j])) {
                                    count++;
                                }
                            }
                            publish(list.get(ii) + ":" + " " + count + "\n");
                            count = 0;
                        }
                        return null;
                    }
                    protected void process(List<String> jj) {
                        for (int i = 0; i < jj.size(); i++)
                            countField.append(jj.get(i));
                    }
                }.execute();
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI_2());
        frame.pack();
        frame.setVisible(true);

    }

}