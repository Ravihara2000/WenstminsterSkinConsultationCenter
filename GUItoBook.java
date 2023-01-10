package CW2022;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class GUItoBook extends JFrame {
    private JLabel labelName;
    private JTextField txt_date;
    private JTextField txt_time;

    public GUItoBook(){
        checkAvailable();
        setLocationRelativeTo(null);
    }

    public GUItoBook(String dL){
        this();
        labelName.setText(dL);
    }

    private void checkAvailable() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        BorderLayout borderLayout = new BorderLayout();

        JLabel label1 = new JLabel("Doctor Name :");
        label1.setBounds(5,5,200,20);
        add(label1);

        JLabel label2 = new JLabel("Enter consultation Date and Time to Check Availability of the Doctor,");
        label2.setBounds(5,5,400,80);
        add(label2);

        Date initialDate = new Date();
        Date minimumDate = new Date(0);
        Date maximumDate = new Date(Long.MAX_VALUE);
        SpinnerDateModel dateModel = new SpinnerDateModel(initialDate, minimumDate, maximumDate, Calendar.MINUTE);

        // Create a JSpinner with the SpinnerDateModel
        JSpinner spinner = new JSpinner(dateModel);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy HH:mm");
        spinner.setEditor(editor);

        // Create a SpinnerNumberModel
        SpinnerNumberModel numberModel = new SpinnerNumberModel(0.0, 0.0, null, 0.1);

        // Create a JSpinner with the SpinnerNumberModel
        JSpinner costSpinner = new JSpinner(numberModel);

        JButton bookDoctor = new JButton("Book Consultation");
        bookDoctor.setBounds(60,240,150,40);
        add(bookDoctor);
        bookDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookDoctor(e);
            }
        });

        JButton checkDoctor = new JButton("Check Availability");
        checkDoctor.setBounds(10,140,150,20);
        add(checkDoctor);

/*        JLabel label5 = new JLabel("Your Doctor is Available");
        label5.setBounds(5,5,200,400);
        add(label5);*/

        labelName = new JLabel();
        labelName.setBounds(210,5, 200,20);
        add(labelName);


        JLabel label3 = new JLabel("Consultation Time :");
        label3.setBounds(5,50,200,120);
        txt_date = new JTextField(20);
        add(label3);
        add(txt_date);

        JLabel label4 = new JLabel("Consultation Date :");
        label4.setBounds(5,5,200,160);
        txt_time = new JTextField(20);
        add(label4);
        add(txt_time);

    }
    public static void main(String[] args) {
        GUItoBook availableDoctorGUI = new GUItoBook();
        availableDoctorGUI.setVisible(true);
    }
    private void bookDoctor(ActionEvent e) {
        PatientDetailsGUI patientDetailsGUI = new PatientDetailsGUI();
        patientDetailsGUI.setVisible(true);
        this.dispose();
    }


}
