import java.time.LocalDate;

/**
 * Clasa abstracta persoana, mai tarziu se extinde in Teacher si Student
 *
 */
abstract class Person {
    protected String name;
    protected LocalDate dateOfBirth;

    public Person() {}

    /**
     *
     * @param name name
     * @param dateOfBirth dateOfBirth
     */
    public Person(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param dateOfBirth dateOfBirth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return dateOfBirth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param obj obiectul de comparat
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        if(name.equals(person.name) && dateOfBirth.equals(person.dateOfBirth)) return true;
        return false;
    }
}

/**
 * Un student
 */
class Student extends Person {

    private String registrationNumber;
    private Project[] preferences;

    public Student() {}

    /**
     *
     * @param name name
     * @param dateOfBirth dateOfBirth
     * @param registrationNumber registrationNumber
     * @param preferences preferintele de proiecte ale unui student
     */
    public Student(String name, LocalDate dateOfBirth, String registrationNumber, Project[] preferences) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.registrationNumber = registrationNumber;
        this.preferences = preferences;
    }

    /**
     *
     * @return nrmatricol
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     *
     * @param registrationNumber nr matricol
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     *
     * @return preferinte
     */
    public Project[] getPreferences() {
        return preferences;
    }

    /**
     *
     * @param preferences
     */
    public void setPreferences(Project[] preferences) {
        this.preferences = preferences;
    }

    /**
     *
     * @param obj obiectul de comparat
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        if (registrationNumber.equals(student.registrationNumber)) return true;
        return false;
    }
}

/**
 * Un profesor
 */
class Teacher extends Person {
    private Project[] proposedProjects;

    public Teacher() {}

    /**
     *
     * @param name name
     * @param dateOfBirth dateOfBirth
     * @param proposedProjects proiecte propuse de profesor
     */
    public Teacher(String name, LocalDate dateOfBirth, Project[] proposedProjects) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.proposedProjects = proposedProjects;
    }

    /**
     *
     * @return lista de proiecte
     */
    public Project[] getProposedProjects() {
        return proposedProjects;
    }

    /**
     *
     * @param proposedProjects proiecte propuse
     */
    public void setProposedProjects(Project[] proposedProjects) {
        this.proposedProjects = proposedProjects;
        for (Project p : proposedProjects) {
            p.setTeacher(this);
        }
    }

    /**
     *
     * @param obj obiectul de comparat
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        if(!teacher.getName().equals(this.getName()) && !teacher.getDateOfBirth().equals(this.getDateOfBirth())) return false;
        return true;
        /*if(teacher.proposedProjects.length()!= this.proposedProjects.length()) return false;
        for(int i = 0; i < teacher.proposedProjects.length(); i++){
            if(!(teacher.proposedProjects[i].getName().equals(this.proposedProjects[i].getName())) &&
               !(teacher.proposedProjects[i].getDateOfBirth().equals(this.proposedProjects[i].getDateOfBirth())))return false;
        }*/
    }
}

/**
 * Un proiect
 */
class Project {
    private String name;
    private Teacher teacher;

    public Project() {}

    /**
     *
     * @param name name
     * @param teacher teacher
     */
    public Project(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     *
     * @param teacher teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     *
     * @param obj obiect de comparat
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return name.equals(project.name) && teacher.equals(project.teacher);
    }
}

/**
 * Reprezinta toate datele problemei, deci toti studentii, profesorii si proiectele
 */
class Problem {
    private Student[] students;
    private Project[] projects;
    private Teacher[] teachers;

    public Problem() {}

    /**
     *
     * @param students students
     * @param projects projects
     * @param teachers teachers
     */
    public Problem(Student[] students, Project[] projects, Teacher[] teachers) {
        this.students = students;
        this.projects = projects;
        this.teachers = teachers;
    }

    /**
     *
     * @return lista de persoane implicate
     */
    public Person[] getPersonsInvolved(){
        Person[] rez = new Person[this.students.length+this.teachers.length];
        for(int i=0;i < students.length; i++){
            rez[i] = new Student(this.students[i].getName(), this.students[i].getDateOfBirth(), this.students[i].getRegistrationNumber(),  this.students[i].getPreferences());
        }
        for(int i=0;i < teachers.length; i++){
            rez[i+students.length] = new Teacher(this.teachers[i].getName(), this.teachers[i].getDateOfBirth(), this.teachers[i].getProposedProjects());
        }
        return rez;
    }
}

/**
 * Solutia problemei, implementeaza un algoritm greedy
 */
class Solution {
    private Project[] projects;
    private Student[] students;

    /**
     *
     * @return lista de studenti care au asignat un proiect
     */
    public Student[] getStudents(){
        return this.students;
    }

    /**
     *
     * @return proiectele la care lucreaza un student
     */
    public Project[] getProjects(){
        return  this.projects;
    }

    /**
     *
     * @param problem problem
     * @param students students
     */
    public Solution(Problem problem, Student[] students) {
        this.projects = new Project[students.length];
        this.students = new Student[students.length];

        int count = 0;
        for (Student student : students) {
            if (!checkStudentInProblem(problem, student)) continue;
            for (Project project : student.getPreferences()) {
                if (!checkProjectAlreadyTaken(project)) {
                    projects[count] = project;
                    this.students[count] = student;
                    count++;
                    break; // Assign only one project per student
                }
            }
        }
    }

    /**
     *
     * @param problem problem
     * @param student student
     * @return true/false daca exista/nu exista studentul in lista de studenti declarata in problem
     */
    public static boolean checkStudentInProblem(Problem problem, Student student){
        for(int i=0;i < problem.getPersonsInvolved().length; i++){
            if(problem.getPersonsInvolved()[i].equals(student)) return true;
        }
        return false;
    }

    /**
     *
     * @param project project
     * @return daca un proiect a fost deja asignat unui student
     */
    public boolean checkProjectAlreadyTaken(Project project){
        for(int i = 0; i < projects.length; i++){
            if(project.equals(this.projects[i])) return true;
        }
        return false;
    }
}

/**
 * Clasa principala unde se testeaza clasele
 *
 */
public class hw_lab2 {
    /**
     *
     */
    hw_lab2(){}

    /**
     *
     * @param args nu avem argumente
     */
    public static void main(String args[]) {

        Teacher teacher1 = new Teacher("Frasinaru", LocalDate.of(1980, 5, 14), new Project[0]);
        Teacher teacher2 = new Teacher("Patrut", LocalDate.of(1975, 8, 21), new Project[0]);

        Project project1 = new Project("AI", teacher1);
        Project project2 = new Project("Web Development", teacher2);
        Project project3 = new Project("Java", teacher1);

        teacher1.setProposedProjects(new Project[]{project1, project3});
        teacher2.setProposedProjects(new Project[]{project2});

        Student student1 = new Student("Gabi", LocalDate.of(2002, 1, 10), "RSL231024", new Project[]{project1, project2});
        Student student2 = new Student("Ioan", LocalDate.of(2001, 3, 22), "RSL231220", new Project[]{project3, project2});
        Student student3 = new Student("Carla", LocalDate.of(2000, 6, 15), "RSL231103", new Project[]{project1, project2});

        Problem problem = new Problem(new Student[]{student1, student2, student3},
                new Project[]{project1, project2, project3},
                new Teacher[]{teacher1, teacher2});

        Solution solution = new Solution(problem, new Student[]{student1, student2, student3});

        System.out.println("Profesori:");
        System.out.println(teacher1.getName() + " - " + teacher1.getDateOfBirth());
        System.out.println(teacher2.getName() + " - " + teacher2.getDateOfBirth());

        System.out.println("\nProiecte:");
        System.out.println(project1.getName() + " propus de " + project1.getTeacher().getName());
        System.out.println(project2.getName() + " propus de " + project2.getTeacher().getName());
        System.out.println(project3.getName() + " propus de " + project3.getTeacher().getName());

        System.out.println("\nStudentii si proiectele lor:");
        for (int i = 0; i < solution.getStudents().length; i++) {
            System.out.println(solution.getStudents()[i].getName() + " -> " + solution.getProjects()[i].getName());
        }
    }
}
