package CW2022;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SkinConsultGUI extends JFrame {

    Consultation man = new Consultation();
    SecretKey key;
    JButton ascendingOrderButton;
    JButton  buttonAssignDoctor;
    JButton showConsult;
    JTable docListTable;
    JScrollPane pane;
    JPanel pan1;

    public SkinConsultGUI(WestminsterSkinConsultationManager manager) {


        setTitle("Westminster Skin Consultation Center");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JLabel header = new JLabel("List Of All Doctors ");
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
        header.setBounds(200,0,600,25);
        add(header);


        String[] columnNames = {"Name", "Surname", "Medical License No", "Specialization", "Mobile number"};
        String[][] rowData = new String[manager.doctorList.size()][10];
        unSortedGuiDataTable(rowData, manager);


        docListTable = new JTable(rowData, columnNames);
        docListTable.setDefaultEditor(Object.class, null); //get an uneditable table
        pane = new JScrollPane(docListTable);
        pane.setBounds(25, 25, 550, 300);


//assign a doctor  button and open new gui
        buttonAssignDoctor = new JButton("Assign Doctor");
        buttonAssignDoctor.setBounds(25,326,200,30);
        add(buttonAssignDoctor);

        buttonAssignDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAssignDoctorActionPerformed(e);
            }
        });

        showConsult = new JButton("Show Consultations");
        showConsult.setBounds(425, 326, 150, 30);

        showConsult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayConsultationDetails();
            }
        });

        add(showConsult);




        //button to sort
        ascendingOrderButton = new JButton("Ascending order");
        ascendingOrderButton.setBounds(250, 326, 150, 30);

        ascendingOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortedGuiDataTable(rowData, manager);
                docListTable.repaint(); //refresh the table
            }
        });

        add(pane);
        add(ascendingOrderButton);
        setVisible(true);//make it visible
    }

    private void buttonAssignDoctorActionPerformed(ActionEvent e) {
        int selectedRow = docListTable.getSelectedRow();
        String dL = docListTable.getValueAt(selectedRow, 0).toString();
        GUItoBook availableDoctorGUI = new GUItoBook(dL);
        availableDoctorGUI.setVisible(true);
        this.dispose();
    }


    public void sortedGuiDataTable(String[][] rows, WestminsterSkinConsultationManager manager) {
        int i = 0;
        Collections.sort(manager.doctorList, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                return d1.getSurname().compareTo(d2.getSurname());
            }
        });
        for (Doctor temp : manager.doctorList) {
            rows[i][0] = temp.getName();
            rows[i][1] = temp.getSurname();
            rows[i][2] = String.valueOf(temp.getMedicalLicenseNumber());
            rows[i][3] = temp.getSpecialisation();
            rows[i][4] = String.valueOf(temp.getMobileNumber());
            i++;
        }
    }

    public void unSortedGuiDataTable(String[][] rows, WestminsterSkinConsultationManager manager) {
        int x = 0;
        for (Doctor temp : manager.doctorList) {
            rows[x][0] = temp.getName();
            rows[x][1] = temp.getSurname();
            rows[x][2] = String.valueOf(temp.getMedicalLicenseNumber());
            rows[x][3] = temp.getSpecialisation();
            rows[x][4] = String.valueOf(temp.getMobileNumber());
            x++;
        }
    }

    private void displayConsultationDetails() {
        // Retrieve the consultation details from the WestminsterSkinConsultationManager instance
        List<Consultation> consultations =man.getConsultations();
        // Create a new JFrame to display the consultation details
        JFrame frame = new JFrame("Consultation Details");
        // Set the size of the window
        frame.setSize(600, 400);
        // Set the layout to null so you can specify the exact position of each component
        frame.setLayout(null);
        // Set the default close operation to dispose the window when it is closed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Add a JTextArea to display the consultation details
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (Consultation c : consultations) {
            textArea.append(c.toString() + "\n");
        }
        // Set the bounds of the text area to specify its position and size
        textArea.setBounds(25, 25, 550, 300);
        // Add the text area to the frame
        frame.add(textArea);
        // Set the frame as visible
        frame.setVisible(true);
    }

    private List<Consultation> getConsultations() {
        // Retrieve the consultation list from the WestminsterSkinConsultationManager instance
        List<Consultation> consultations = getConsultations();
        // Return the consultation list
        return consultations;
    }



}




