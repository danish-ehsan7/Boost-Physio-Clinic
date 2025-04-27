
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

public class BookingSystem {
    private TreatmentTimetable timetable;
    private List<Physiotherapist> physiotherapists;
    private List<Patient> patients;
    private List<Appointment> appointments;
    private int bookingCounter = 1;

    private int countAttendedAppointments(Physiotherapist physio) {
        int count = 0;
        for (Appointment appointment : appointments) {
            if (appointment.getPhysio().equals(physio) && "Attended".equalsIgnoreCase(appointment.getStatus())) {
                count++;
            }
        }
        return count;
    }
    public BookingSystem() {
        this.physiotherapists = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.timetable = new TreatmentTimetable();
        loadSampleData();
    }

    private void loadSampleData() {
        // Create physiotherapists
        Physiotherapist p1 = new Physiotherapist(1, "Dr. John", "Physiotherapy");
        Physiotherapist p2 = new Physiotherapist(2, "Dr. Smith", "Rehabilitation");
        Physiotherapist p3 = new Physiotherapist(3, "Dr. Emma", "Sports Therapy");
        Physiotherapist p4 = new Physiotherapist(4, "Dr. Michael", "Orthopedics");
        Physiotherapist p5 = new Physiotherapist(5, "Dr. Sarah", "Neurology");
    
        // Add physiotherapists to the system
        physiotherapists.add(p1);
        physiotherapists.add(p2);
        physiotherapists.add(p3);
        physiotherapists.add(p4);
        physiotherapists.add(p5);
    
        // Create patients
        patients.add(new Patient("Alice", "123 Main St", "123-456-7890"));
        patients.add(new Patient("Bob", "456 Oak Ave", "987-654-3210"));
        patients.add(new Patient("Charlie", "789 Birch Blvd", "456-789-1234"));
        patients.add(new Patient("David", "101 Pine Rd", "654-321-9870"));
        patients.add(new Patient("Eva", "202 Cedar St", "789-654-0123"));
        patients.add(new Patient("Frank", "303 Maple Ave", "321-654-9870"));
        patients.add(new Patient("Grace", "404 Oak Ln", "234-567-8901"));
        patients.add(new Patient("Hannah", "505 Elm St", "543-210-8765"));
        patients.add(new Patient("Isaac", "606 Redwood Way", "876-543-2109"));
        patients.add(new Patient("Jack", "707 Willow Dr", "234-890-1234"));
        patients.add(new Patient("Kelly", "808 Cherry St", "567-890-2341"));
        patients.add(new Patient("Liam", "909 Palm Ave", "678-123-4567"));
    
       // Week 1 Treatments (Only one treatment per physiotherapist)
        addTreatmentsForWeek(p1, 1, "2025-05-01", new String[]{"Acupuncture"});
        addTreatmentsForWeek(p2, 1, "2025-05-01", new String[]{"Therapeutic Exercise"});
        addTreatmentsForWeek(p3, 1, "2025-05-01", new String[]{"Sports Massage"});
        addTreatmentsForWeek(p4, 1, "2025-05-01", new String[]{"Joint Mobilization"});
        addTreatmentsForWeek(p5, 1, "2025-05-01", new String[]{"Electrotherapy"});

        // Week 2 Treatments (Only one treatment per physiotherapist)
        addTreatmentsForWeek(p1, 2, "2025-05-08", new String[]{"Chiropractic"});
        addTreatmentsForWeek(p2, 2, "2025-05-08", new String[]{"Hydrotherapy"});
        addTreatmentsForWeek(p3, 2, "2025-05-08", new String[]{"Sports Massage"});
        addTreatmentsForWeek(p4, 2, "2025-05-08", new String[]{"Massage"});
        addTreatmentsForWeek(p5, 2, "2025-05-08", new String[]{"Electrotherapy"});

        // Week 3 Treatments (Only one treatment per physiotherapist)
        addTreatmentsForWeek(p1, 3, "2025-05-15", new String[]{"Kinesiology Tape"});
        addTreatmentsForWeek(p2, 3, "2025-05-15", new String[]{"Therapeutic Exercise"});
        addTreatmentsForWeek(p3, 3, "2025-05-15", new String[]{"Sports Massage"});
        addTreatmentsForWeek(p4, 3, "2025-05-15", new String[]{"Joint Mobilization"});
        addTreatmentsForWeek(p5, 3, "2025-05-15", new String[]{"Electrotherapy"});

        // Week 4 Treatments (Only one treatment per physiotherapist)
        addTreatmentsForWeek(p1, 4, "2025-05-22", new String[]{"Chiropractic"});
        addTreatmentsForWeek(p2, 4, "2025-05-22", new String[]{"Hydrotherapy"});
        addTreatmentsForWeek(p3, 4, "2025-05-22", new String[]{"Sports Massage"});
        addTreatmentsForWeek(p4, 4, "2025-05-22", new String[]{"Dry Needling"});
        addTreatmentsForWeek(p5, 4, "2025-05-22", new String[]{"Therapeutic Ultrasound"});

    // Book appointments for patients
    bookAppointment(p1, patients.get(0), new Treatment("Acupuncture", "2025-05-01 10:00"));
    bookAppointment(p2, patients.get(1), new Treatment("Massage", "2025-05-02 11:00"));
}
private void addTreatmentsForWeek(Physiotherapist physiotherapist, int weekNumber, String date, String[] treatments) {
    List<Treatment> weeklyTreatments = new ArrayList<>();
    for (String treatmentName : treatments) {
        weeklyTreatments.add(new Treatment(treatmentName, date + " 10:00")); // Adjust times as needed
    }
    physiotherapist.addWeeklyTreatments(weekNumber, weeklyTreatments);
}
    public Appointment bookAppointment(Physiotherapist physio, Patient patient, Treatment treatment) {
        Appointment appointment = new Appointment(bookingCounter++, physio, patient, treatment);
        appointments.add(appointment);
        return appointment;
    }

    public void cancelAppointment(int bookingId) {
        for (Appointment a : appointments) {
            if (a.getBookingId() == bookingId) {
                a.cancel();
                break;
            }
        }
    }
    public void attendAppointment(int bookingId) {
        for (Appointment a : appointments) {
            if (a.getBookingId() == bookingId) {
                a.attend(); // Make sure Appointment class has an `attend()` method
                break;
            }
        }
    }
    private void showTimetableDialog(JFrame parentFrame) {
        // Create a modal dialog for displaying the physiotherapist timetable
        JDialog timetableDialog = new JDialog(parentFrame, "Physiotherapist Timetable", true);
        timetableDialog.setSize(800, 600);
        timetableDialog.setLayout(new BorderLayout());

        DefaultTableModel timetableModel = new DefaultTableModel();
        timetableModel.addColumn("Physiotherapist");
        timetableModel.addColumn("Week");
        timetableModel.addColumn("Treatment Name");
        timetableModel.addColumn("Time");

        // Populate the timetable with the treatments of each physiotherapist
        for (Physiotherapist physio : physiotherapists) {
            for (int week = 1; week <= 4; week++) {
                List<Treatment> treatments = physio.getTreatmentsForWeek(week);
                for (Treatment treatment : treatments) {
                    timetableModel.addRow(new Object[]{
                            physio.getName(),  // Display physiotherapist's name
                            "Week " + week,    // Display the week
                            treatment.getName(),  // Display the treatment name
                            treatment.getDateTime()  // Display the treatment time
                    });
                }
            }
        }

        JTable timetableTable = new JTable(timetableModel);
        JScrollPane scrollPane = new JScrollPane(timetableTable);
        timetableDialog.add(scrollPane, BorderLayout.CENTER);

        timetableDialog.setVisible(true);
    }
    
    public void removePatient(int patientId) {
        patients.removeIf(p -> p.getId() == patientId);
    }

    public void displayGUI() {
        JFrame frame = new JFrame("Physiotherapy Appointments");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Booking ID");
        model.addColumn("Physiotherapist");
        model.addColumn("Treatment");
        model.addColumn("Patient");
        model.addColumn("Time");
        model.addColumn("Status");
        model.addColumn("Cancel");
        model.addColumn("Attend");
        // Populate the table with appointments
        for (Appointment a : appointments) {
            model.addRow(new Object[] {
                a.getBookingId(),
                a.getPhysio().getName(),
                a.getTreatment().getName(),
                a.getPatient().getName(),
                a.getTreatment().getDateTime(),
                a.getStatus(),
                "Cancel",
                "Attend"
            });
            
        }

        JTable table = new JTable(model);
        table.setCellSelectionEnabled(true);

        // Add the Cancel button for each row
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new AppointmentActionEditor(new JCheckBox(), model, this, "Cancel"));
        
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new AppointmentActionEditor(new JCheckBox(), model, this, "Attend"));
        

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons to open appointment form dialog and patients history dialog
        JPanel buttonPanel = new JPanel();
        JButton openFormButton = new JButton("Add Appointment");
        openFormButton.addActionListener(e -> openAppointmentFormDialog(frame, model));
        buttonPanel.add(openFormButton);

        JButton changeAppointmentButton = new JButton("Change Appointment");
        changeAppointmentButton.addActionListener(e -> showChangeAppointmentDialog(frame, model));
        buttonPanel.add(changeAppointmentButton);

        JButton viewTimetableButton = new JButton("View Timetable");
        viewTimetableButton.addActionListener(e -> showTimetableDialog(frame));
        buttonPanel.add(viewTimetableButton);

        JButton patientsHistoryButton = new JButton("Patients History");
        patientsHistoryButton.addActionListener(e -> showPatientsHistoryDialog(frame));
        buttonPanel.add(patientsHistoryButton);

        JButton printReportButton = new JButton("Print Report");
        printReportButton.addActionListener(e -> printReport());
        buttonPanel.add(printReportButton);
        
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    public void printReport() {
        // Ensure that the appointments list is initialized
        if (appointments == null || appointments.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No appointments to print.");
            return;
        }
    
        // Ensure that physiotherapists list is initialized
        if (physiotherapists == null || physiotherapists.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No physiotherapists available.");
            return;
        }
    
        // Creating report content
        StringBuilder report = new StringBuilder();
        report.append("Appointment Report\n\n");
    
        // Report all treatments for each physiotherapist
        for (Physiotherapist physio : physiotherapists) {
            report.append("Physiotherapist: ").append(physio.getName()).append("\n");
            for (Appointment appointment : appointments) {
                if (appointment.getPhysio().equals(physio)) {
                    report.append("  Treatment: ").append(appointment.getTreatment().getName())
                            .append(", Patient: ").append(appointment.getPatient().getName())
                            .append(", Time: ").append(appointment.getTreatment().getDateTime())
                            .append(", Status: ").append(appointment.getStatus()).append("\n");
                }
            }
            report.append("\n");
        }
    
        // Add physiotherapist ranking
        report.append("\nPhysiotherapists Ranked by Attended Appointments\n");
    
        // Sort physiotherapists based on the number of attended appointments
        physiotherapists.sort((p1, p2) -> Integer.compare(countAttendedAppointments(p2), countAttendedAppointments(p1)));
    
        for (Physiotherapist physio : physiotherapists) {
            int attendedCount = countAttendedAppointments(physio);
            report.append(physio.getName()).append(": ").append(attendedCount).append(" attended appointments\n");
        }
    
        // Display the report
        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Report", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void openAppointmentFormDialog(JFrame parentFrame, DefaultTableModel model) {
        JDialog dialog = new JDialog(parentFrame, "Book Appointment", true);
        dialog.setSize(800, 600);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parentFrame);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Patient selection
        JPanel patientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel patientLabel = new JLabel("Patient:");
        JComboBox<Patient> patientCombo = new JComboBox<>();
        for (Patient p : patients) {
            patientCombo.addItem(p);
        }
        patientPanel.add(patientLabel);
        patientPanel.add(patientCombo);

        // Physio selection
        JPanel physioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel physioLabel = new JLabel("Physiotherapist:");
        JComboBox<Physiotherapist> physioCombo = new JComboBox<>();
        for (Physiotherapist p : physiotherapists) {
            physioCombo.addItem(p);
        }
        physioPanel.add(physioLabel);
        physioPanel.add(physioCombo);

        // Treatment table
        String[] columns = {"Week", "Treatment", "Date/Time"};
        DefaultTableModel treatmentsModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        JTable treatmentsTable = new JTable(treatmentsModel);
        treatmentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(treatmentsTable);

        // Load treatments button
        JButton loadBtn = new JButton("Show Timetable");
        loadBtn.addActionListener(e -> {
            treatmentsModel.setRowCount(0);
            Physiotherapist physio = (Physiotherapist)physioCombo.getSelectedItem();
            if (physio == null) {
                JOptionPane.showMessageDialog(dialog, "Please select a physiotherapist");
                return;
            }
            
            if (timetable == null) {
                JOptionPane.showMessageDialog(dialog, "Timetable not available");
                return;
            }
        
            boolean hasTreatments = false;
            for (int week = 1; week <= 4; week++) {
                List<Treatment> treatments = physio.getTreatmentsForWeek(week);
                for (Treatment t : treatments) {
                    if (isAvailable(t, appointments)) {
                        treatmentsModel.addRow(new Object[]{
                            "Week " + week,
                            t.getName(),
                            t
                        });
                        hasTreatments = true;
                    }
                }
            }
            
            if (!hasTreatments) {
                JOptionPane.showMessageDialog(dialog, "No available treatments found. They may already be booked.");
            }
        });
        
        // Book button
        JButton bookBtn = new JButton("Add Appointment");
        bookBtn.addActionListener(e -> {
            int selectedRow = treatmentsTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(dialog, "Please select a treatment");
                return;
            }

            Patient patient = (Patient)patientCombo.getSelectedItem();
            Physiotherapist physio = (Physiotherapist)physioCombo.getSelectedItem();
            
            Treatment treatment = (Treatment)treatmentsModel.getValueAt(selectedRow, 2); 
            String treatmentName = treatment.getName();
            String dateTime = treatment.getDateTime(); 
            
            // Check if already booked
            if (isBooked(patient, treatmentName, dateTime, appointments)) {
                JOptionPane.showMessageDialog(dialog, "Patient already has this treatment booked");
                return;
            }

            // Create and book appointment
            Appointment appt = bookAppointment(physio, patient, treatment);
            appointments.add(appt);
            
            // Update main table
            model.addRow(new Object[]{
                appt.getBookingId(),
                physio.getName(),
                treatment.getName(),
                patient.getName(),
                treatment.getDateTime(),
                "Booked",
                "Cancel", 
                "Attend"
            });

            JOptionPane.showMessageDialog(dialog, "Appointment booked successfully!");
            dialog.dispose();
        });

        // Layout
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(patientPanel);
        topPanel.add(physioPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadBtn);
        buttonPanel.add(bookBtn);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private static boolean isAvailable(Treatment t, List<Appointment> appointments) {
        return appointments.stream()
                .noneMatch(a -> a.getTreatment().getName().equals(t.getName()) && 
                               a.getTreatment().getDateTime().equals(t.getDateTime()) && 
                               a.getStatus().equals("Booked"));
    }

    private static boolean isBooked(Patient p, String treatmentName, String dateTime, 
                                  List<Appointment> appointments) {
        return appointments.stream()
                .anyMatch(a -> a.getPatient().equals(p) && 
                             a.getTreatment().getName().equals(treatmentName) &&
                             a.getTreatment().getDateTime().equals(dateTime) &&
                             a.getStatus().equals("Booked"));
    }
    
    private void showChangeAppointmentDialog(JFrame parent, DefaultTableModel model) {
        JTextField bookingIdField = new JTextField(10);
        JTextField newTreatmentField = new JTextField(15);
        JTextField newDateTimeField = new JTextField(15); // Format: yyyy-MM-dd HH:mm
    
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Booking ID:"));
        panel.add(bookingIdField);
        panel.add(new JLabel("New Treatment Name:"));
        panel.add(newTreatmentField);
        panel.add(new JLabel("New DateTime (yyyy-MM-dd HH:mm):"));
        panel.add(newDateTimeField);
    
        int result = JOptionPane.showConfirmDialog(parent, panel, "Change Appointment",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                int bookingId = Integer.parseInt(bookingIdField.getText().trim());
                String treatmentName = newTreatmentField.getText().trim();
                String dateTime = newDateTimeField.getText().trim();
    
                Treatment newTreatment = new Treatment(treatmentName, dateTime);
                boolean success = changeAppointment(bookingId, newTreatment);
    
                if (success) {
                    JOptionPane.showMessageDialog(parent, "Appointment changed successfully.");
    
                    // Update the table row
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if ((int) model.getValueAt(i, 0) == bookingId) {
                            model.setValueAt(treatmentName, i, 2); // Treatment name
                            model.setValueAt(newTreatment.getDateTime(), i, 4); // Time
                            break;
                        }
                    }
    
                } else {
                    JOptionPane.showMessageDialog(parent, "Could not change appointment. Time may be already booked.");
                }
    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "Invalid input: " + ex.getMessage());
            }
        }
    }
    public boolean changeAppointment(int bookingId, Treatment newTreatment) {
        // Find the appointment
        Appointment oldAppointment = null;
        for (Appointment a : appointments) {
            if (a.getBookingId() == bookingId) {
                oldAppointment = a;
                break;
            }
        }
        
        if (oldAppointment == null) return false;
        
        // Check for conflicts
        for (Appointment a : appointments) {
            if (a != oldAppointment && 
                a.getTreatment().getDateTime().equals(newTreatment.getDateTime()) &&
                a.getPhysio().equals(oldAppointment.getPhysio())) {
                return false; // Conflict found
            }
        }
        
        // Update the appointment
        oldAppointment.setTreatment(newTreatment);
        return true;
    }
    private void showPatientsHistoryDialog(JFrame parentFrame) {
        // Create a modal dialog for displaying patients history
        JDialog patientsHistoryDialog = new JDialog(parentFrame, "Patients History", true);
        patientsHistoryDialog.setSize(600, 400);
        patientsHistoryDialog.setLayout(new BorderLayout());

        DefaultTableModel patientModel = new DefaultTableModel();
        patientModel.addColumn("Patient ID");
        patientModel.addColumn("Name");
        patientModel.addColumn("Address");
        patientModel.addColumn("Phone Number");
        patientModel.addColumn("Action");

        // Populate the table with patient data
        for (Patient p : patients) {
            patientModel.addRow(new Object[] {
                    p.getId(),
                    p.getName(),
                    p.getAddress(),
                    p.getPhoneNumber(),
                    "Remove" 
            });
        }

        JTable patientTable = new JTable(patientModel);
        JScrollPane scrollPane = new JScrollPane(patientTable);
        patientsHistoryDialog.add(scrollPane, BorderLayout.CENTER);

        // Add action listener for the "Remove" button
        patientTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        patientTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox(), patientModel));

        // Add a button to add a new patient
        JButton addPatientButton = new JButton("Add New Patient");
        addPatientButton.addActionListener(e -> openAddPatientFormDialog(patientsHistoryDialog, patientModel));
        patientsHistoryDialog.add(addPatientButton, BorderLayout.SOUTH);

        patientsHistoryDialog.setVisible(true);
    }

    private void openAddPatientFormDialog(JDialog parentDialog, DefaultTableModel model) {
        JDialog addPatientDialog = new JDialog(parentDialog, "Add New Patient", true);
        addPatientDialog.setSize(400, 300);
        addPatientDialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();

        addPatientDialog.add(new JLabel("Name:"));
        addPatientDialog.add(nameField);
        addPatientDialog.add(new JLabel("Address:"));
        addPatientDialog.add(addressField);
        addPatientDialog.add(new JLabel("Phone Number:"));
        addPatientDialog.add(phoneField);

        JButton submitButton = new JButton("Add Patient");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();

            // Validate input
            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(addPatientDialog, "Please fill in all fields.");
                return;
            }

            // Create and add the new patient
            Patient newPatient = new Patient(name, address, phone);
            patients.add(newPatient);

            // Add the patient to the table
            model.addRow(new Object[] {
                    newPatient.getId(),
                    newPatient.getName(),
                    newPatient.getAddress(),
                    newPatient.getPhoneNumber(),
                    "Remove"
            });

            // Close the dialog after the patient is added
            addPatientDialog.dispose();
        });

        addPatientDialog.add(submitButton);

        addPatientDialog.setVisible(true);
    }

    // Custom ButtonRenderer class to render the cancel button
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText(value == null ? "Remove" : value.toString());
            return this;
        }
    }

    // Custom ButtonEditor class to handle button click events
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;
        private DefaultTableModel model;

        public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
            super(checkBox);
            this.model = model;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "Remove" : value.toString();
            JButton button = new JButton(label);
            button.addActionListener(e -> {
                isPushed = true;
                int patientId = (int) table.getValueAt(row, 0);
                removePatient(patientId);
                model.removeRow(row);
            });
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                isPushed = false;
                return label;
            }
            return label;
        }
    }

    class AppointmentActionEditor extends DefaultCellEditor {
        private JButton button;
        private boolean isPushed;
        private DefaultTableModel model;
        private JTable table;
        private BookingSystem bookingSystem;
        private String actionType; // "Cancel" or "Attend"
    
        public AppointmentActionEditor(JCheckBox checkBox, DefaultTableModel model, BookingSystem bookingSystem, String actionType) {
            super(checkBox);
            this.model = model;
            this.bookingSystem = bookingSystem;
            this.actionType = actionType;
            this.button = new JButton();
            this.button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }
    
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.table = table;
            button.setText((value == null) ? actionType : value.toString());
            isPushed = true;
            return button;
        }
    
        @Override
        public Object getCellEditorValue() {
            if (isPushed && table != null) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int bookingId = (int) model.getValueAt(selectedRow, 0);
                    String currentStatus = model.getValueAt(selectedRow, 5).toString();
    
                    if ("Canceled".equalsIgnoreCase(currentStatus) || "Attended".equalsIgnoreCase(currentStatus)) {
                        // Do nothing if already finalized
                        JOptionPane.showMessageDialog(null, "This appointment is already " + currentStatus + ".");
                    } else if ("Cancel".equalsIgnoreCase(actionType)) {
                        bookingSystem.cancelAppointment(bookingId);
                        model.setValueAt("Canceled", selectedRow, 5);
                    } else if ("Attend".equalsIgnoreCase(actionType)) {
                        bookingSystem.attendAppointment(bookingId);
                        model.setValueAt("Attended", selectedRow, 5);
                    }
                }
            }
            isPushed = false;
            return button.getText();
        }
    
        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    
        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();
        bookingSystem.displayGUI();
    }
}
