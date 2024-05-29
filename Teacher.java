
// Teacher class
public class Teacher {
    // Attributes
    private int teacherId;
    private String teacherName;
    private String address;
    private String workingType;
    private String employmentStatus;
    private int workingHours; // Initialized to 0 by default

    // Constructor
    public Teacher(int teacherId, String teacherName, String address, String workingType, String employmentStatus) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;// Initialize teacherName with the parameter value
        this.address = address;
        this.workingType = workingType;
        this.employmentStatus = employmentStatus;
        this.workingHours = 0; // Default value
    }

    // Accessor methods
    public int getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getAddress() {
        return address;
    }

    public String getWorkingType() {
        return workingType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    // Method to set working hours
    public void setWorkingHours(int newWorkingHours) {
        this.workingHours = newWorkingHours;
    }

    // Display method
    public void display() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Teacher Name: " + teacherName);
        System.out.println("Address: " + address);
        System.out.println("Working Type: " + workingType);
        System.out.println("Employment Status: " + employmentStatus);

        if (workingHours > 0) {
            System.out.println("Working Hours: " + workingHours);
        } else {
            System.out.println("Working Hours not assigned");
        }
    }


}
