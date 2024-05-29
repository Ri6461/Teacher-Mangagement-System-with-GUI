public class Tutor extends Teacher {
    // Additional attributes
    private double salary;
    private String specialization;
    private String academicQualifications;
    private int performanceIndex;
    private boolean isCertified;

    // Constructor
    public Tutor(int teacherId, String teacherName, String address, String workingType, String employmentStatus,
                 int workingHours, double salary, String specialization, String academicQualifications, int performanceIndex) {
        super(teacherId, teacherName, address, workingType, employmentStatus);
        super.setWorkingHours(workingHours);

        this.salary = salary;
        this.specialization = specialization;
        this.academicQualifications = academicQualifications;
        this.performanceIndex = performanceIndex;
        this.isCertified = false;
    }

    // Accessor methods
    public double getSalary() {
        return salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAcademicQualifications() {
        return academicQualifications;
    }

    public int getPerformanceIndex() {
        return performanceIndex;
    }

    public boolean isCertified() {
        return isCertified;
    }

    // Method to certify and set the salary of the tutor
    public String certifyAndSetSalary(double newSalary, int newPerformanceIndex) {
        if (newPerformanceIndex > 5 && super.getWorkingHours() > 20) {
            double appraisal = 0.0;
            String appraisalMessage = "";

            if (newPerformanceIndex >= 5 && newPerformanceIndex <= 7) {
                appraisal = newSalary * 0.05;
                appraisalMessage = "Appraisal is 5%. New salary: " + (salary + appraisal);
            } else if (newPerformanceIndex >= 8 && newPerformanceIndex <= 9) {
                appraisal = newSalary * 0.10;
                appraisalMessage = "Appraisal is 10%. New salary: " + (salary + appraisal);
            } else if (newPerformanceIndex == 10) {
                appraisal = newSalary * 0.20;
                appraisalMessage = "Appraisal is 20%. New salary: " + (salary + appraisal);
            }

            this.salary = salary + appraisal;
            isCertified = true;
            return appraisalMessage;
        } else {
            return "Salary cannot be approved as the tutor has not been certified.";
        }
    }

    // Method to remove tutor
    public void removeTutor() {
        if (!isCertified) {
            this.salary = 0;
            this.specialization = null;
            this.academicQualifications = null;
            this.performanceIndex = 0;

            System.out.println("Tutor removed successfully.");
        } else {
            System.out.println("Cannot remove a certified tutor.");
        }
    }

    // Display method
    @Override
    public void display() {
        super.display();

        if (isCertified) {
            System.out.println("Salary: " + salary);
            System.out.println("Specialization: " + specialization);
            System.out.println("Academic Qualifications: " + academicQualifications);
            System.out.println("Performance Index: " + performanceIndex);
        } else {
            System.out.println("Tutor has not been certified yet.");
        }
    }
}
