import java.time.LocalDate;

public class compulsory_lab2{

    /**
     *
     * @param args nu avem argumente
     */
    public static void main(String[] args) {
        Project p1 = new Project("Java", ProjectType.theoretical, null);
        Project p2 = new Project("IP", ProjectType.practical, null);

        Student c1 = new Student("Student1", LocalDate.of(1999, 12, 31), 123_456_789L, p1);
        Student c2 = new Student("Student2", LocalDate.of(2004, 6, 20), 129_446_759L, p2);
        //System.out.println(c2);

        p1.setStudent(c1);
        p2.setStudent(c2);

        c1.printStudent();
        c2.printStudent();
        p1.printProject();
        p2.printProject();
    }
}


enum ProjectType{
    theoretical, practical;
}

class Student{
    private String name;
    private LocalDate birthdate;
    private Long regNumber;
    private Project project;

    /**
     * constructor default
     */
    public Student(){}

    /**
     *
     * @param name name
     */
    public Student(String name){
        this(name, null, null, null);
    }

    /**
     *
     * @param name nume student
     * @param birthdate  birthdate
     * @param regNumber regNumber
     * @param project project
     */
    public Student(String name, LocalDate birthdate, Long regNumber, Project project){
        this.name=name;
        this.birthdate=birthdate;
        this.regNumber=regNumber;
        this.project=project;
    }

    /**
     *
     * @return nume student
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     *
     * @return birthdate student
     */
    public LocalDate getBirthdate(){
        return birthdate;
    }

    /**
     *
     * @param birthdate birthdate
     */
    public void setBirthdate(LocalDate birthdate){
        this.birthdate = birthdate;
    }

    /**
     *
     * @return nr matricol
     */
    public Long getRegNumber(){
        return regNumber;
    }

    /**
     *
     * @param regNumber nr matricol
     */
    public void setRegNumber(Long regNumber){
        this.regNumber = regNumber;
    }

    /**
     *
     * @return proiect
     */
    public Project getProject(){
        return project;
    }

    /**
     *
     * @param project proiect
     */
    public void setProject(Project project){
        this.project=project;
    }

    /**
     *
     * @return override la toString pentru student
     */
    @Override
    public String toString(){
        String s;
        s = "{\"Student\"{\"name\":\"" + name + "\",\"birthdate\":\"" + birthdate + "\",\"regNumber\":\"" + regNumber +  "\",\"project\":\"" + project.getName() + "\"}}";
        return s;
    }

    /**
     *
     */
    public void printStudent() {
        System.out.println(this.toString());
    }
}


class Project{
    private String name;
    private ProjectType type;
    private Student student;

    public Project(String name, ProjectType type, Student student){
        this.name=name;
        this.type=type;
        this.student=student;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public ProjectType getType(){
        return type;
    }

    public void setType(ProjectType type){
        this.type=type;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student=student;
    }

    @Override
    public String toString(){
        return "{\"Project\":{\"name\":\""+name+"\",\"type\":\""+type+"\",\"student\":\""+student.getName()+"\"}}";
    }

    public void printProject() {
        System.out.println(this.toString());
    }
}


