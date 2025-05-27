public class SchoolApp {
    public static void main(String[] args) {
        Student student = new Student("Alice", 18);
        Teacher teacher = new Teacher("Mr. John", "Mathematics");

        School school = new School("Sunrise High School");
        school.enrollStudent(student);
        school.assignTeacher(teacher);

        school.printDetails();
    }
}

// Student class
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDetails() {
        return "Student: " + name + ", Age: " + age;
    }
}

// Teacher class
class Teacher {
    private String name;
    private String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getDetails() {
        return "Teacher: " + name + ", Subject: " + subject;
    }
}

// School class
class School {
    private String schoolName;
    private Student student;
    private Teacher teacher;

    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    public void enrollStudent(Student student) {
        this.student = student;
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void printDetails() {
        System.out.println("School: " + schoolName);
        if (student != null) {
            System.out.println(student.getDetails());
        }
        if (teacher != null) {
            System.out.println(teacher.getDetails());
        }
    }
}