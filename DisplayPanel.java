import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class DisplayPanel extends JPanel {
    private JTextField teacherIdField;
    private JButton displayButton; // Button to display the teacher information
    private JButton clearButton; // Button to clear the teacher ID input field
    private TeacherGUI teacherList;

    public DisplayPanel(TeacherGUI teacherList) {
        this.teacherList = teacherList;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Add label and text field for teacher ID
        constraints.anchor = GridBagConstraints.EAST; // Align labels to the right
        addComponent(constraints, new JLabel("Teacher ID:"), 0, 0);
        teacherIdField = addTextField(constraints, 1, 0);

        // Add button to display teacher information
        displayButton = new JButton("Display Teacher");
        addComponent(constraints, displayButton, 1, 1);
        displayButton.addActionListener(new DisplayTeacherActionListener());

        // Add the clear button to clear the teacher ID input field
        clearButton = new JButton("Clear");
        addComponent(constraints, clearButton, 2, 1);
        clearButton.addActionListener(new ClearActionListener());
    }

    // Adds a component to the panel using the provided constraints
    private void addComponent(GridBagConstraints constraints, JComponent component, int gridx, int gridy) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        add(component, constraints);
    }

    // Adds a JTextField to the panel using the provided constraints
    private JTextField addTextField(GridBagConstraints constraints, int gridx, int gridy) {
        JTextField textField = new JTextField(15);
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = GridBagConstraints.WEST; // Align text fields to the left
        add(textField, constraints);
        return textField;
    }

    // ActionListener for the clear button
    private class ClearActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear the teacher ID input field
            teacherIdField.setText("");
        }
    }

    // ActionListener for the display button
    private class DisplayTeacherActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve the inputted teacher ID from the text field
                String teacherIdText = teacherIdField.getText().trim();

                // Check if the inputted teacher ID is valid
                if (teacherIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(DisplayPanel.this, "Please enter a valid teacher ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Parse the teacher ID from the input text
                int teacherId = Integer.parseInt(teacherIdText);

                // Find the teacher with the specified ID
                Teacher teacher = teacherList.findTeacherById(teacherId);

                // If the teacher does not exist, show an error message
                if (teacher == null) {
                    JOptionPane.showMessageDialog(DisplayPanel.this, "Teacher ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a table model with column names for the teacher data
                DefaultTableModel tableModel = new DefaultTableModel();

                if (teacher instanceof Lecturer) {
                    // Add columns specific to a lecturer, including working hours
                    tableModel.addColumn("Teacher ID");
                    tableModel.addColumn("Name");
                    tableModel.addColumn("Address");
                    tableModel.addColumn("Working Type");
                    tableModel.addColumn("Employment Status");
                    tableModel.addColumn("Working Hours");
                    tableModel.addColumn("Department");
                    tableModel.addColumn("Years of Experience");
                    tableModel.addColumn("Graded Score");

                    // Add the lecturer's data to the table model
                    Lecturer lecturer = (Lecturer) teacher;
                    Object[] rowData = {
                            lecturer.getTeacherId(),
                            lecturer.getTeacherName(),
                            lecturer.getAddress(),
                            lecturer.getWorkingType(),
                            lecturer.getEmploymentStatus(),
                            lecturer.getWorkingHours(),
                            lecturer.getDepartment(),
                            lecturer.getYearsOfExperience(),
                            lecturer.getGradedScore()
                    };
                    tableModel.addRow(rowData);
                } else if (teacher instanceof Tutor) {
                    // Add columns specific to a tutor, including working hours
                    tableModel.addColumn("Teacher ID");
                    tableModel.addColumn("Name");
                    tableModel.addColumn("Address");
                    tableModel.addColumn("Working Type");
                    tableModel.addColumn("Employment Status");
                    tableModel.addColumn("Working Hours");
                    tableModel.addColumn("Specialization");
                    tableModel.addColumn("Academic Qualifications");
                    tableModel.addColumn("Performance Index");
                    tableModel.addColumn("Salary");

                    // Add the tutor's data to the table model
                    Tutor tutor = (Tutor) teacher;
                    Object[] rowData = {
                            tutor.getTeacherId(),
                            tutor.getTeacherName(),
                            tutor.getAddress(),
                            tutor.getWorkingType(),
                            tutor.getEmploymentStatus(),
                            tutor.getWorkingHours(),
                            tutor.getSpecialization(),
                            tutor.getAcademicQualifications(),
                            tutor.getPerformanceIndex(),
                            tutor.getSalary()
                    };
                    tableModel.addRow(rowData);
                }

                // Create a JTable with the populated table model
                JTable table = new JTable(tableModel);

                // Wrap the table in a JScrollPane for scrolling
                JScrollPane scrollPane = new JScrollPane(table);

                // Display the table in a dialog box
                JOptionPane.showMessageDialog(DisplayPanel.this, scrollPane, "Teacher Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                // Handle error when the input is not a valid number
                JOptionPane.showMessageDialog(DisplayPanel.this, "Please enter a valid numeric teacher ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Handle other exceptions and show an error message
                JOptionPane.showMessageDialog(DisplayPanel.this, "Error displaying teacher information: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

