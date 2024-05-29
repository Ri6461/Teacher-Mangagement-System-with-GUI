

public class Lecturer extends Teacher {
    // Additional attributes
    private String department;
    private int yearsOfExperience;
    private int gradedScore;
    private boolean hasGraded;

    // Constructor
    public Lecturer(int teacherId, String teacherName, String address, String workingType, String employmentStatus,
                    String department, int yearsOfExperience) {
        // Call superclass constructor
        super(teacherId, teacherName, address, workingType, employmentStatus);

        // Set additional attributes
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.gradedScore = 0;
        this.hasGraded = false;
    }

    // Accessor methods
    public String getDepartment() {
        return department;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public int getGradedScore() {
        return gradedScore;
    }

    public boolean hasGraded() {
        return hasGraded;
    }

    // Mutator method for gradedScore
    public void setGradedScore(int gradedScore) {
        this.gradedScore = gradedScore;
    }

    // Method to grade assignment
    public String gradeAssignment(int gradedScore, String department, int yearsOfExperience) {
        // Check eligibility for grading
        if (yearsOfExperience >= 5 && this.department.equals(department)) {
            // Ensure graded score is between 0 and 100
            if (gradedScore < 0 || gradedScore > 100) {
                return "Graded Score not valid Invalid Score";
            } else {
                // Display graded score and grade obtained in a pop-up dialog box
                String grade = "";
                if (gradedScore >= 70 && gradedScore <= 100) {
                    grade = "A";
                } else if (gradedScore >= 60 && gradedScore < 70) {
                    grade = "B";
                } else if (gradedScore >= 50 && gradedScore < 60) {
                    grade = "C";
                } else if (gradedScore >= 40 && gradedScore < 50) {
                    grade = "D";
                } else {
                    grade = "E";
                }
                String message = "Graded Score: " + gradedScore + "\nYou have secured grade " + grade;


                // Set the graded score and mark assignment as graded
                this.gradedScore = gradedScore;
                hasGraded = true;
                return message;
            }
        } else {
            return "Assignment not graded yet or not eligible for grading.";
        }
    }

    // Display method
    @Override
    public void display() {
        super.display(); // Call display method of the superclass (Teacher)
        System.out.println("Department: " + department);
        System.out.println("Years of Experience: " + yearsOfExperience);

        if (hasGraded) {
            System.out.println("Graded Score: " + gradedScore);
        } else {
            System.out.println( "Assignment not graded yet.");

        }
    }
}
