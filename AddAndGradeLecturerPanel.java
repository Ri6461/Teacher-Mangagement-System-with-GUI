import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAndGradeLecturerPanel {
    private JPanel panel;
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField addressField;
    private JTextField workingTypeField;
    private JTextField employmentStatusField;
    private JTextField departmentField;
    private JTextField yearsOfExperienceField;
    private JTextField gradedScoreField;
    private JButton addLecturerButton;
    private JButton gradeAssignmentButton;
    private JButton clearButton;

    // Reference to the TeacherGUI object to manage the list of teachers
    private TeacherGUI teacherList;

    // Constructor to initialize the panel and its components
    public AddAndGradeLecturerPanel(TeacherGUI teacherList) {
        this.teacherList = teacherList;

        // Create a new panel with a GridBagLayout for layout management
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Initialize the text fields
        teacherIdField = new JTextField(10);
        teacherNameField = new JTextField(10);
        addressField = new JTextField(10);
        workingTypeField = new JTextField(10);
        employmentStatusField = new JTextField(10);
        departmentField = new JTextField(10);
        yearsOfExperienceField = new JTextField(10);
        gradedScoreField = new JTextField(10);

        // Initialize the buttons
        addLecturerButton = new JButton("Add Lecturer");
        gradeAssignmentButton = new JButton("Grade Assignment");
        clearButton = new JButton("Clear");

        // Configure layout constraints
        constraints.anchor = GridBagConstraints.EAST; // Align components to the right
        constraints.insets = new Insets(5, 5, 5, 5); // Add padding between components

        // Add components to the panel
        addComponents(constraints);

        // Set up action listeners for the buttons
        addLecturerButton.addActionListener(new AddLecturerActionListener());
        gradeAssignmentButton.addActionListener(new GradeAssignmentActionListener());
        clearButton.addActionListener(new ClearActionListener());
    }

    // Method to add labels and text fields to the panel
    private void addComponents(GridBagConstraints constraints) {
        // Add labels and text fields for each attribute
        addComponent(constraints, new JLabel("Teacher ID:"), teacherIdField, 0);
        addComponent(constraints, new JLabel("Teacher Name:"), teacherNameField, 1);
        addComponent(constraints, new JLabel("Address:"), addressField, 2);
        addComponent(constraints, new JLabel("Working Type:"), workingTypeField, 3);
        addComponent(constraints, new JLabel("Employment Status:"), employmentStatusField, 4);
        addComponent(constraints, new JLabel("Department:"), departmentField, 5);
        addComponent(constraints, new JLabel("Years of Experience:"), yearsOfExperienceField, 6);
        addComponent(constraints, new JLabel("Graded Score:"), gradedScoreField, 7);

        // Add buttons for adding a lecturer and grading assignments
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER; // Center the buttons
        panel.add(addLecturerButton, constraints);

        constraints.gridy = 9;
        panel.add(gradeAssignmentButton, constraints);

        // Add clear button
        constraints.gridy = 10;
        panel.add(clearButton, constraints);
    }

    // Method to add a label and text field to the panel
    private void addComponent(GridBagConstraints constraints, JLabel label, JTextField textField, int row) {
        constraints.gridx = 0; // Set x-coordinate
        constraints.gridy = row; // Set y-coordinate
        constraints.anchor = GridBagConstraints.EAST; // Align components to the right
        panel.add(label, constraints); // Add label

        constraints.gridx = 1; // Set x-coordinate
        constraints.anchor = GridBagConstraints.WEST; // Align components to the left
        panel.add(textField, constraints); // Add text field
    }

    // Action listener for adding a lecturer
    private class AddLecturerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve input values for adding a lecturer
                int teacherId = Integer.parseInt(teacherIdField.getText());

                // Check if the teacher ID already exists in the list
                if (teacherList.findLecturerById(teacherId) != null) {
                    JOptionPane.showMessageDialog(panel, "Teacher ID already exists! Please use a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Retrieve the remaining input values
                String teacherName = teacherNameField.getText();
                String address = addressField.getText();
                String workingType = workingTypeField.getText();
                String employmentStatus = employmentStatusField.getText();
                String department = departmentField.getText();
                int yearsOfExperience = Integer.parseInt(yearsOfExperienceField.getText());
                int gradedScore = Integer.parseInt(gradedScoreField.getText());

                // Create a new Lecturer object
                Lecturer newLecturer = new Lecturer(teacherId, teacherName, address, workingType, employmentStatus, department, yearsOfExperience);
                newLecturer.setGradedScore(gradedScore);

                // Add the lecturer to the teacher list
                teacherList.addTeacher(newLecturer);

                // Show confirmation dialog
                JOptionPane.showMessageDialog(panel, "Lecturer added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter valid numeric input for ID, years of experience, and graded score.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Action listener for grading assignments
    private class GradeAssignmentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Show a custom dialog for grading assignments
            showGradingDialog();
        }
    }

    // Method to show the grading dialog
    private void showGradingDialog() {
        // Create a new dialog for grading assignments
        JDialog dialog = new JDialog();
        dialog.setTitle("Grade Assignment");

        // Create fields for the dialog
        JTextField dialogTeacherIdField = new JTextField(10);
        JTextField dialogGradedScoreField = new JTextField(10);
        JTextField dialogDepartmentField = new JTextField(10);
        JTextField dialogYearsOfExperienceField = new JTextField(10);

        // Create a panel for the dialog
        JPanel dialogPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Configure layout constraints
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST; // Align components to the left

        // Add fields to the dialog panel
        addDialogField(constraints, new JLabel("Teacher ID:"), dialogTeacherIdField, dialogPanel, 0);
        addDialogField(constraints, new JLabel("Graded Score:"), dialogGradedScoreField, dialogPanel, 1);
        addDialogField(constraints, new JLabel("Department:"), dialogDepartmentField, dialogPanel, 2);
        addDialogField(constraints, new JLabel("Years of Experience:"), dialogYearsOfExperienceField, dialogPanel, 3);

        // Add confirm and cancel buttons
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(event -> handleGrading(dialog, dialogTeacherIdField, dialogGradedScoreField, dialogDepartmentField, dialogYearsOfExperienceField));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> dialog.dispose());

        // Add buttons to the dialog panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        dialog.add(dialogPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Configure the dialog settings
        dialog.pack();
        dialog.setLocationRelativeTo(panel); // Center the dialog relative to the main panel
        dialog.setVisible(true); // Show the dialog
    }

    // Handle grading from the grading dialog
    private void handleGrading(JDialog dialog, JTextField dialogTeacherIdField, JTextField dialogGradedScoreField, JTextField dialogDepartmentField, JTextField dialogYearsOfExperienceField) {
        try {
            // Retrieve input values from the dialog
            int teacherId = Integer.parseInt(dialogTeacherIdField.getText());
            int gradedScore = Integer.parseInt(dialogGradedScoreField.getText());
            String department = dialogDepartmentField.getText();
            int yearsOfExperience = Integer.parseInt(dialogYearsOfExperienceField.getText());

            // Find the lecturer and grade the assignment
            Lecturer lecturer = teacherList.findLecturerById(teacherId);
            if (lecturer != null) {
                // Call the method to grade assignments
                String message = lecturer.gradeAssignment(gradedScore, department, yearsOfExperience);

                // Display success message
                JOptionPane.showMessageDialog(panel, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display an error message if the teacher ID is invalid
                JOptionPane.showMessageDialog(panel, "Invalid teacher ID. Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Display an error message if inputs are not valid numbers
            JOptionPane.showMessageDialog(panel, "Please enter valid numeric values for ID, graded score, and years of experience.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Handle any other exceptions that may occur
            JOptionPane.showMessageDialog(panel, "An error occurred while grading assignment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Close the dialog
        dialog.dispose();
    }

    // Add dialog fields to the dialog panel
    private void addDialogField(GridBagConstraints constraints, JComponent label, JTextField textField, JPanel dialogPanel, int row) {
        constraints.gridy = row;
        constraints.gridx = 0;
        dialogPanel.add(label, constraints);

        constraints.gridx = 1;
        dialogPanel.add(textField, constraints);
    }

    // Action listener for the clear button
    private class ClearActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear all input fields
            teacherIdField.setText("");
            teacherNameField.setText("");
            addressField.setText("");
            workingTypeField.setText("");
            employmentStatusField.setText("");
            departmentField.setText("");
            yearsOfExperienceField.setText("");
            gradedScoreField.setText("");

            // Display a message to confirm fields have been cleared
            JOptionPane.showMessageDialog(panel, "All input fields have been cleared.");
        }
    }

    // Return the panel
    public JPanel getPanel() {
        return panel;
    }
}
