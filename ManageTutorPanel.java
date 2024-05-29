import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageTutorPanel extends JPanel {

    // Text fields for tutor information
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField addressField;
    private JTextField workingTypeField;
    private JTextField employmentStatusField;
    private JTextField workingHoursField;
    private JTextField salaryField;
    private JTextField specializationField;
    private JTextField academicQualificationsField;
    private JTextField performanceIndexField;

    // Buttons for actions
    private JButton addButton;
    private JButton removeButton;
    private JButton setSalaryButton;
    private JButton clearButton;

    // Reference to the TeacherGUI object to manage the list of teachers
    private TeacherGUI teacherList;

    public ManageTutorPanel(TeacherGUI teacherList) {
        this.teacherList = teacherList;

        // Setting layout and constraints for the panel
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Add labels and input fields for tutor information
        addLabelAndField("Teacher ID:", teacherIdField = new JTextField(), constraints, 0);
        addLabelAndField("Teacher Name:", teacherNameField = new JTextField(), constraints, 1);
        addLabelAndField("Address:", addressField = new JTextField(), constraints, 2);
        addLabelAndField("Working Type:", workingTypeField = new JTextField(), constraints, 3);
        addLabelAndField("Employment Status:", employmentStatusField = new JTextField(), constraints, 4);
        addLabelAndField("Working Hours:", workingHoursField = new JTextField(), constraints, 5);
        addLabelAndField("Salary:", salaryField = new JTextField(), constraints, 6);
        addLabelAndField("Specialization:", specializationField = new JTextField(), constraints, 7);
        addLabelAndField("Academic Qualifications:", academicQualificationsField = new JTextField(), constraints, 8);
        addLabelAndField("Performance Index:", performanceIndexField = new JTextField(), constraints, 9);

        // Add action buttons
        addActionButtons(constraints, 10);
    }

    /**
     * Helper method to add a label and text field to the panel
     * @param labelText The text for the label
     * @param textField The text field for input
     * @param constraints GridBagConstraints object to control layout
     * @param row The row index for the layout
     */
    private void addLabelAndField(String labelText, JTextField textField, GridBagConstraints constraints, int row) {
        constraints.gridx = 0; // Set the x position for the label
        constraints.gridy = row; // Set the y position for the label
        constraints.anchor = GridBagConstraints.EAST; // Align the label to the right
        add(new JLabel(labelText), constraints); // Add the label to the panel

        // Configure and add the text field
        constraints.gridx = 1; // Set the x position for the text field
        constraints.anchor = GridBagConstraints.WEST; // Align the text field to the left
        add(textField, constraints);
    }

    /**
     * Helper method to add action buttons to the panel
     * @param constraints GridBagConstraints object to control layout
     * @param row The row index for the layout
     */
    private void addActionButtons(GridBagConstraints constraints, int row) {
        constraints.gridx = 0; // Start with the first column
        constraints.gridy = row; // Set the y position for the buttons
        constraints.gridwidth = 1; // Each button occupies one column

        // Add the "Add Tutor" button and set its action listener
        addButton = new JButton("Add Tutor");
        addButton.addActionListener(new AddTutorActionListener());
        add(addButton, constraints);

        // Add the "Set Salary of Tutor" button and set its action listener
        constraints.gridx++;
        setSalaryButton = new JButton("Set Salary of Tutor");
        setSalaryButton.addActionListener(new SetSalaryActionListener());
        add(setSalaryButton, constraints);

        // Add the "Remove Tutor" button and set its action listener
        constraints.gridx++;
        removeButton = new JButton("Remove Tutor");
        removeButton.addActionListener(new RemoveTutorActionListener());
        add(removeButton, constraints);

        // Add the "Clear" button and set its action listener
        constraints.gridx++;
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearActionListener());
        add(clearButton, constraints);
    }

    /**
     * ActionListener for the "Add Tutor" button
     */
    private class AddTutorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve input data
                int teacherId = Integer.parseInt(teacherIdField.getText());

                // Check for duplicate ID before adding a tutor
                if (teacherList.findTeacherById(teacherId) != null) {
                    JOptionPane.showMessageDialog(ManageTutorPanel.this, "A teacher with ID " + teacherId + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a new Tutor object and add it to the teacher list
                Tutor tutor = createTutorFromInput();
                teacherList.addTeacher(tutor);
                JOptionPane.showMessageDialog(ManageTutorPanel.this, "Tutor added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ManageTutorPanel.this, "Invalid input data. Please enter valid values.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ManageTutorPanel.this, "Error adding tutor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * ActionListener for the "Set Salary of Tutor" button
     */
    private class SetSalaryActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Show the custom dialog to get user input
                String[] result = showSetSalaryDialog();

                // Check if the input is valid
                if (result != null && result.length == 3) {
                    int teacherId = Integer.parseInt(result[0]);
                    double newSalary = Double.parseDouble(result[1]);
                    int newPerformanceIndex = Integer.parseInt(result[2]);

                    // Find the tutor by teacher ID and set new salary and performance index
                    Tutor tutor = teacherList.findTutorById(teacherId);
                    if (tutor != null) {
                        String appraisalMessage = tutor.certifyAndSetSalary(newSalary, newPerformanceIndex);
                        JOptionPane.showMessageDialog(ManageTutorPanel.this, appraisalMessage, "Appraisal Result", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ManageTutorPanel.this, "Tutor not found with the given Teacher ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ManageTutorPanel.this, "Invalid input data. Please enter valid values.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ManageTutorPanel.this, "Error setting tutor's salary and performance index: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * ActionListener for the "Remove Tutor" button
     */
    private class RemoveTutorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Show the custom dialog to get user input
                String teacherIdStr = showRemoveTutorDialog();

                // Check if the input is valid
                if (teacherIdStr != null) {
                    int teacherId = Integer.parseInt(teacherIdStr);
                    teacherList.removeTutorById(teacherId);
                    JOptionPane.showMessageDialog(ManageTutorPanel.this, "Tutor removed successfully!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ManageTutorPanel.this, "Invalid input data. Please enter valid values.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ManageTutorPanel.this, "Error removing tutor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * ActionListener for the "Clear" button
     */
    private class ClearActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear all input fields
            clearInputFields();
            JOptionPane.showMessageDialog(ManageTutorPanel.this, "All input fields have been cleared.");
        }
    }

    /**
     * Clears all input fields in the panel
     */
    private void clearInputFields() {
        teacherIdField.setText("");
        teacherNameField.setText("");
        addressField.setText("");
        workingTypeField.setText("");
        employmentStatusField.setText("");
        workingHoursField.setText("");
        salaryField.setText("");
        specializationField.setText("");
        academicQualificationsField.setText("");
        performanceIndexField.setText("");
    }

    /**
     * Creates a Tutor object from the input fields
     * @return a new Tutor object
     */
    private Tutor createTutorFromInput() {
        // Retrieve input values from text fields
        int teacherId = Integer.parseInt(teacherIdField.getText());
        String teacherName = teacherNameField.getText();
        String address = addressField.getText();
        String workingType = workingTypeField.getText();
        String employmentStatus = employmentStatusField.getText();
        int workingHours = Integer.parseInt(workingHoursField.getText());
        double salary = Double.parseDouble(salaryField.getText());
        String specialization = specializationField.getText();
        String academicQualifications = academicQualificationsField.getText();
        int performanceIndex = Integer.parseInt(performanceIndexField.getText());

        // Create and return a new Tutor object
        return new Tutor(teacherId, teacherName, address, workingType, employmentStatus, workingHours, salary, specialization, academicQualifications, performanceIndex);
    }

    /**
     * Shows a custom dialog to input teacher ID, new salary, and new performance index
     * @return an array containing teacher ID, new salary, and new performance index, or null if cancelled
     */
    private String[] showSetSalaryDialog() {
        JTextField teacherIdField = new JTextField();
        JTextField newSalaryField = new JTextField();
        JTextField newPerformanceIndexField = new JTextField();

        // Dialog message with fields for input
        Object[] message = {
                "Teacher ID:", teacherIdField,
                "New Salary:", newSalaryField,
                "New Performance Index:", newPerformanceIndexField
        };

        // Show confirm dialog and get user input
        int option = JOptionPane.showConfirmDialog(this, message, "Set Salary of Tutor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return new String[] {teacherIdField.getText(), newSalaryField.getText(), newPerformanceIndexField.getText()};
        }
        return null;
    }

    /**
     * Shows a custom dialog to input teacher ID for removing tutor
     * @return the input teacher ID as a string, or null if cancelled
     */
    private String showRemoveTutorDialog() {
        JTextField teacherIdField = new JTextField();

        // Dialog message with a field for teacher ID input
        Object[] message = {
                "Teacher ID:", teacherIdField
        };

        // Show confirm dialog and get user input
        int option = JOptionPane.showConfirmDialog(this, message, "Remove Tutor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return teacherIdField.getText();
        }
        return null;
    }
}
