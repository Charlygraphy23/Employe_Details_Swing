import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class EmployeeWindow extends JFrame {
    EmployeeWindow() throws Exception{
        initiate();
    }

    //Initialization
    private JPanel panel;
    private JMenuBar menuBar;
    private JTextArea idTextarea,nameTextare,deptTextare;
    private JLabel idLabel,nameLabel,deptLabel;
    private JButton nextButton,clearButton;
    private ArrayList<EmployeeDetails> list;
    private Iterator listItterator;
    private EmployeeDetails employee;
    private JFileChooser chooser;



    private void initiate() {
        setTitle("Employee Details");
        setBounds(160,160,320,450);
        panel=new JPanel();
        setResizable(false);
        panel.add(new JLabel(new ImageIcon("C:\\Users\\Dip\\Downloads\\img.jpg")));


        menuBar=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenuItem save=new JMenuItem("Save");
        JMenuItem newf=new JMenuItem("New");
        JMenuItem open=new JMenuItem("OpenDetails");
        JMenuItem showId=new JMenuItem("Show Id");
        file.add(newf);file.add(save);file.add(open);file.add(showId);menuBar.add(file);
        open.addActionListener(new fileOpen());
        newf.addActionListener(new fileClick());
        save.addActionListener(new saveActionListner());
        showId.addActionListener(new showeId());

        idLabel=new JLabel("ID");
        idLabel.setBounds(25,50,80,30);
        add(idLabel);

        idTextarea=new JTextArea();
        idTextarea.setBounds(50,50,40,25);
        add(idTextarea);

        nameLabel=new JLabel("Name");
        nameLabel.setBounds(10,100,80,30);
        add(nameLabel);

        nameTextare=new JTextArea();
        JScrollPane nameScrollPane=new JScrollPane(nameTextare);
        nameScrollPane.setBounds(50,100,150,50);
        nameScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        nameScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(nameScrollPane);

        deptLabel=new JLabel("Dept");
        deptLabel.setBounds(10,190,80,30);
        add(deptLabel);

        deptTextare=new JTextArea();
        deptTextare.setBounds(50,190,150,50);
        add(deptTextare);

        list=new ArrayList<>();
        nextButton=new JButton("Next");
        nextButton.setBounds(80,280,100,40);
        nextButton.setBackground(Color.GRAY);
        add(nextButton);
        nextButton.addActionListener(new nextButtonClick());



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(menuBar, BorderLayout.NORTH);
        add(panel);
    }


    private class nextButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(idTextarea.getText().equals("") || nameTextare.getText().equals("") || deptTextare.getText().equals("")){
                JOptionPane.showMessageDialog(panel,"Invalid Input","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                try {
                    employee = new EmployeeDetails(String.valueOf(idTextarea.getText()), nameTextare.getText(), deptTextare.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            list.add(employee);
            newfile();

        }
    }

    private void newfile() {
        idTextarea.setText("");
        nameTextare.setText("");
        deptTextare.setText("");
    }


    private class saveActionListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           chooser=new JFileChooser();
           employee=new EmployeeDetails(String.valueOf(idTextarea.getText()),nameTextare.getText(),deptTextare.getText());
           list.add(employee);
            int dialog=chooser.showSaveDialog(panel);

            if(dialog==JFileChooser.APPROVE_OPTION){

                try {
                    saveFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void saveFile() throws IOException {

        employee=new EmployeeDetails(String.valueOf(idTextarea.getText()),nameTextare.getText(),deptTextare.getText());
        FileWriter fileWriter=new FileWriter(new File(chooser.getSelectedFile().getPath()));
        listItterator=list.iterator();
        while (listItterator.hasNext()){
            employee= (EmployeeDetails) listItterator.next();
            fileWriter.write(employee.getId()+"/");
            fileWriter.write(employee.getName()+"/");
            fileWriter.write(employee.getDept()+"\n");
        }
        fileWriter.close();
    }


    private class fileClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newfile();
        }
    }

    private class fileOpen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                new EmployeeDetailsOpen().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new EmployeeWindow().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private class showeId implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new EmployeeDetailsLabel().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
