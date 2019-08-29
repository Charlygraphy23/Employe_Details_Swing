import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class EmployeeDetailsOpen extends JFrame {
    EmployeeDetailsOpen() throws Exception{
        initiate();
    }

    //INITIALIZATION
    private JPanel panel;
    private JMenuBar menuBar;
    private JLabel idLabel,nameLabel,deptLabel,nameTextare,deptTextare;
    private JTextArea idTextarea;
    private JButton showButton,deleteButton;
    private JFileChooser chooser;
    private EmployeeDetails employee;
    private ArrayList<EmployeeDetails> list;
    private Iterator listIterator;






    private void initiate() {
        setTitle("Employee Details");
        setBounds(160,160,320,300);
        panel=new JPanel();
        setResizable(false);
        panel.setBackground(Color.LIGHT_GRAY);


        menuBar=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenuItem open=new JMenuItem("Open");
        file.add(open);menuBar.add(file);
        add(menuBar,BorderLayout.NORTH);
        open.addActionListener(new openClick());

        idLabel=new JLabel("ID");
        idLabel.setBounds(25,50,80,30);
        add(idLabel);

        idTextarea=new JTextArea();
        idTextarea.setBounds(50,50,40,25);
        add(idTextarea);

        nameLabel=new JLabel("Name");
        nameLabel.setBounds(10,100,80,30);
        add(nameLabel);

        nameTextare=new JLabel();
        nameTextare.setBounds(50,100,150,50);
        nameTextare.setBorder(new LineBorder(Color.WHITE,1));
        add(nameTextare);

        deptLabel=new JLabel("Dept");
        deptLabel.setBounds(10,190,80,30);
        add(deptLabel);

        deptTextare=new JLabel();
        deptTextare.setBounds(50,190,150,50);
        deptTextare.setBorder(new LineBorder(Color.WHITE,1));
        add(deptTextare);

        showButton=new JButton("Show Details");
        showButton.setBounds(150,35,150,30);
        add(showButton);
        list=new ArrayList<>();
        showButton.addActionListener(new buttonClick());

        deleteButton=new JButton("Delete");
        deleteButton.setBounds(150,65,100,30);
        add(deleteButton);
        deleteButton.addActionListener(new deleteDetails());

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(panel);
    }

    private class openClick implements ActionListener {
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
            }
        }
    }

    private void getFile() throws IOException {

        BufferedReader reader=new BufferedReader(new FileReader(chooser.getSelectedFile().getPath()));
        String line = null;
        while ((line=reader.readLine())!=null){
            splitData(line);
        }
        reader.close();


    }





    private void getDetails() {
        listIterator=list.iterator();
        while (listIterator.hasNext()){
            employee= (EmployeeDetails) listIterator.next();
            System.out.println(employee.getId());
            if (idTextarea.getText().equals(employee.getId())){
                System.out.println(idTextarea);
                nameTextare.setText(employee.getName());
                deptTextare.setText(employee.getDept());
                 break;
            }
        }

    }




    private void splitData(String l) {
        StringTokenizer tokenizer=new StringTokenizer(l,"/");
        while (tokenizer.hasMoreTokens()){
            EmployeeDetails employeeDetails=new EmployeeDetails(tokenizer.nextToken(),tokenizer.nextToken(),tokenizer.nextToken());
            list.add(employeeDetails);

        }
    }

    private class buttonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
         if(idTextarea.getText().equals("")){
             JOptionPane.showMessageDialog(panel,"Invalid File To Open","Alert",JOptionPane.WARNING_MESSAGE);
         }
         else if(list.isEmpty()){
             JOptionPane.showMessageDialog(panel,"Invalid Id","Alert",JOptionPane.WARNING_MESSAGE);
         }
         else {
             getDetails();
         }
        }
    }

    private class deleteDetails implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(idTextarea.getText().equals("") || list.isEmpty() ){
                JOptionPane.showMessageDialog(panel,"Employee Invalid","Alert",JOptionPane.WARNING_MESSAGE);

            }
            else {
                getDetails();
                System.out.println(employee.getId());
                if (idTextarea.getText().equals(employee.getId())) {
                    listIterator.remove();
                    clearAll();

                }
            }
        }
    }


    private void clearAll() {
        idTextarea.setText("");
        nameTextare.setText("");
        deptTextare.setText("");
    }


}



