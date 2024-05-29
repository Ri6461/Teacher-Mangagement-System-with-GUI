import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherGUI extends JFrame {
    // List to store Teacher objects
    private List<Teacher> teachers;

    public TeacherGUI() {
        // Initialize the list to store Teacher objects
        teachers = new ArrayList<>();

        // Create a tabbed pane to hold the different panels
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create and add panels, passing the TeacherGUI instance (this) to allow access to teacher data
        // Add the AddAndGradeLecturerPanel
        AddAndGradeLecturerPanel addAndGradeLecturerPanel = new AddAndGradeLecturerPanel(this);
        tabbedPane.addTab("Add and Grade Lecturer", addAndGradeLecturerPanel.getPanel());

        // Add the ManageTutorPanel
        ManageTutorPanel manageTutorPanel = new ManageTutorPanel(this);
        tabbedPane.addTab("Manage Tutor", manageTutorPanel);

        // Add the DisplayPanel
        DisplayPanel displayPanel = new DisplayPanel(this);
        tabbedPane.addTab("Display", displayPanel);

        // Add the tabbed pane to the frame
        add(tabbedPane);

        // Configure frame settings
        setTitle("Teacher Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to add a teacher to the list
    public void addTeacher(Teacher teacher) {
        // Check if the teacher ID already exists in the list
        for (Teacher existingTeacher : teachers) {
            if (existingTeacher.getTeacherId() == teacher.getTeacherId()) {
                throw new IllegalArgumentException("Teacher ID already exists. Please use a different ID.");
            }
        }
        teachers.add(teacher);
    }

    // Method to find a teacher by their ID
    public Teacher findTeacherById(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    // Method to remove a tutor by their ID
    public void removeTutorById(int teacherId) {
        Teacher teacherToRemove = findTeacherById(teacherId);
        if (teacherToRemove != null && teacherToRemove instanceof Tutor) {
            teachers.remove(teacherToRemove);
        }
    }

    // Method to find a lecturer by their ID
    public Lecturer findLecturerById(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher instanceof Lecturer && teacher.getTeacherId() == teacherId) {
                return (Lecturer) teacher;
            }
        }
        return null;
    }

    // Method to find a tutor by their ID
    public Tutor findTutorById(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher instanceof Tutor && teacher.getTeacherId() == teacherId) {
                return (Tutor) teacher;
            }
        }
        return null;
    }

    // Method to get a list of all teachers
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers);
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TeacherGUI());
    }
}
