import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EmployeeDetailsLabel extends JFrame {

    EmployeeDetailsLabel() throws Exception{
        initiate();
    }



    //INITIALIZATION
    private JPanel panel;
    private JLabel textArea;
    private EmployeeDetails employeeDetails;
    private ArrayList<EmployeeDetails> list;
    private Iterator listIterator;
    private JMenuBar menuBar;
    private JFileChooser chooser;
    private String ids,names;


    private void initiate() {
        setTitle("Show Details");
        setBounds(160,160,320,450);
        panel=new JPanel();
        setResizable(false);
        panel.setBackground(Color.LIGHT_GRAY);
        list=new ArrayList<>();

        textArea=new JLabel();
        textArea.setBounds(55,30,200,350);
        textArea.setBorder(new LineBorder(Color.WHITE,2));
        add(textArea);



        menuBar=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenuItem open=new JMenuItem("Open");
        file.add(open);menuBar.add(file);
        add(menuBar,BorderLayout.NORTH);
        open.addActionListener(new OpenFileDetails());

       // getDetails();

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(panel);

    }

    private void getDetails() {
        listIterator=list.iterator();
        while (listIterator.hasNext()){
            employeeDetails= (EmployeeDetails) listIterator.next();
                if(employeeDetails.getId()!=null) {

                    ids = ids + employeeDetails.getId()+" ";
                    textArea.setText(ids);
                }
        }
    }

    private class OpenFileDetails implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            chooser=new JFileChooser();
            int dialog=chooser.showOpenDialog(panel);

            if(dialog==JFileChooser.APPROVE_OPTION){
                try {
                    getFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                getDetails();
            }
        }
    }

    private void getFile() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(chooser.getSelectedFile().getPath()));
        String line=null;
        while ((line=reader.readLine())!=null){
            splitFile(line);

        }
        reader.close();
    }

    private void splitFile(String l) {
        StringTokenizer tokenizer=new  StringTokenizer(l,"/");
        while (tokenizer.hasMoreTokens()){
            EmployeeDetails employee=new EmployeeDetails(tokenizer.nextToken(),tokenizer.nextToken(),tokenizer.nextToken());
            list.add(employee);
        }

    }
}
