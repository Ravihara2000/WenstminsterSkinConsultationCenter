package CW2022;

import javax.crypto.*;
import javax.imageio.ImageIO;
import javax.management.openmbean.InvalidKeyException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import static CW2022.Consultation.consultations;

public class PatientDetailsGUI extends JFrame {
    static WestminsterSkinConsultationManager manager1 = new WestminsterSkinConsultationManager();
    private static BufferedImage globalImage;
    SecretKey key; // Encryption key
    // Array for encrypted image store
    byte[] encryptedImageData;
    static ArrayList<Consultation> consultations = new ArrayList<>();

    private JTextField tName;
    private JTextField tSurname;
    private JTextField tDateOfBirth;
    private JTextField tMobileNum;
    private JTextField tPatientId;
    private JTextArea tNotes;
    private JButton pDataSubmitBtn;


    Patient p = new Patient();

    public PatientDetailsGUI() {
        patUI();
        setLocationRelativeTo(null);
    }

    private void patUI() {

        setTitle("Patient Details Form");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();

        JLabel jl0 = new JLabel("PATIENT DETAILS FORM");
        jl0.setFont(new Font("Times New Roman", Font.BOLD, 16));
        jl0.setBounds(210, 10, 200, 20);
        add(jl0);

        JPanel jp1 = new JPanel();
        add(jp1, borderLayout.CENTER);
        jp1.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        GridLayout gridLayout = new GridLayout(12, 2);
        gridLayout.setVgap(10);
        jp1.setLayout(gridLayout);

        JLabel jl8 = new JLabel("Enter a Date :");
        jp1.add(jl8);
        Date initialDate = new Date();
        Date minimumDate = new Date(0);
        Date maximumDate = new Date(Long.MAX_VALUE);
        SpinnerDateModel dateModel = new SpinnerDateModel(initialDate, minimumDate, maximumDate, Calendar.MINUTE);

        // Create a JSpinner with the SpinnerDateModel
        JSpinner spinner1 = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner1, "dd-MM-yyyy HH:mm");
        spinner1.setEditor(editor);

        // Create a SpinnerNumberModel & Create a JSpinner with the SpinnerNumberModel
        SpinnerNumberModel numberModel = new SpinnerNumberModel(0.0, 0.0, null, 0.1);
        JSpinner costSpinner = new JSpinner(numberModel);

        JTextField noteField = new JTextField();

        jp1.add(spinner1);
        JLabel jl1 = new JLabel("Patient Name :");
        tName = new JTextField(20);
        jp1.add(jl1);
        jp1.add(tName);

        JLabel jl2 = new JLabel("Patient Surname :");
        tSurname = new JTextField(20);
        jp1.add(jl2);
        jp1.add(tSurname);

        JLabel jl4 = new JLabel("Patient Mobile Number :");
        tMobileNum = new JTextField(20);
        jp1.add(jl4);
        jp1.add(tMobileNum);

        JLabel jl5 = new JLabel("Patient ID :");
        tPatientId = new JTextField(20);
        jp1.add(jl5);
        jp1.add(tPatientId);

        JLabel jl6 = new JLabel("Patient Date of Birth :");
        tDateOfBirth = new JTextField(20);
        jp1.add(jl6);
        jp1.add(tDateOfBirth);

        JLabel jl7 = new JLabel("Notes :");
        tNotes = new JTextArea(5, 20);
        jp1.add(jl7);
        jp1.add(tNotes);

        //Image uploading feature
        JButton uploadButton = new JButton("Upload Image");
        jp1.add(uploadButton);
        uploadButton.addActionListener(e1 -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    globalImage = ImageIO.read(selectedFile);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(globalImage, "jpg", baos);
                    baos.flush();
                    byte[] imageData = baos.toByteArray();
                    baos.close();
                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                    keyGenerator.init(128); // set key size to 128 bits
                    key = keyGenerator.generateKey();

                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    encryptedImageData = cipher.doFinal(imageData);

                } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException |
                         NoSuchAlgorithmException | BadPaddingException | InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (java.security.InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pDataSubmitBtn = new JButton("Submit");
        pDataSubmitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Consultation p = new Consultation();
                p.setName(tName.getText());
                p.setSurname(tSurname.getText());
                p.setMobileNumber(Integer.parseInt(tMobileNum.getText()));
                p.getPatientID(Integer.parseInt(tPatientId.getText()));
                p.setDateOB(LocalDate.parse(tDateOfBirth.getText()));
                //p.set(txt_notes.getText());

                // Add the patient details to the patient list
                consultations.add(p);

                // Add the patient details to the database
                //PatientDAO.addPatient(p);

                // Clear the text fields
                tName.setText("");
                tSurname.setText("");
                tMobileNum.setText("");
                tPatientId.setText("");
                tDateOfBirth.setText("");
                tNotes.setText("");

                JOptionPane.showMessageDialog(null, "You added Patient details successfully!");
                JFrame f1= new JFrame();
                add(f1);
            }

        });
        jp1.add(pDataSubmitBtn);

        JButton pdataClearButton = new JButton("Clear form");
        pdataClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tName.setText("");
                tSurname.setText("");
                tMobileNum.setText("");
                tPatientId.setText("");
                tDateOfBirth.setText("");
                tNotes.setText("");
            }
        });
        jp1.add(pdataClearButton);

        //view details
        JButton viewData = new JButton("View Consultation data");
        jp1.add(viewData);
        viewData.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // once the button is clicked, open a new JFrame
                JFrame det1 = new JFrame();
                det1.setSize(400, 300);
                det1.setTitle("Patient Details");
                det1.setLayout(null);

                int y = 20;
                for (Patient patient: consultations) {
/*                    JLabel appDate = new JLabel("Patient Name: " + LocalDate.get);
                    appDate.setBounds(20, y, 150, 20);
                    det1.add(appDate);
                    y += 20;*/

                    JLabel nameLabel = new JLabel("Patient Name: " + patient.getName());
                    nameLabel.setBounds(20, y, 150, 20);
                    det1.add(nameLabel);
                    y += 20;

                    JLabel surnameLabel = new JLabel("Patient Surname: " + patient.getSurname());
                    surnameLabel.setBounds(20, y, 150, 20);
                    det1.add(surnameLabel);
                    y += 20;

                    JLabel mobNumLabel = new JLabel("Patient Mobile No: " + patient.getMobileNumber());
                    nameLabel.setBounds(20, y, 150, 20);
                    det1.add(mobNumLabel);
                    y += 20;

                    JLabel dateOB = new JLabel("Patient Date of Birth: " + patient.getDateOB());
                    dateOB.setBounds(20, y, 150, 20);
                    det1.add(dateOB);
                    y += 20;

/*                    JLabel patid = new JLabel("Patient ID: " + p.getPatientID());
                    patid.setBounds(20, y, 150, 20);
                    det1.add(patid);
                    y += 20;*/
                    
                    // add labels for other Patient details
                }
                det1.setVisible(true);
            }
        });
    }
}

