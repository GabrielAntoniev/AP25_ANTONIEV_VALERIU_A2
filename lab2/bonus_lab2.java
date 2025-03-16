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
     * @param preferences preferintele de projects ale unui student
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

    public void changeAPreferedProject(Project project, int idx){
        this.preferences[idx] = project;
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

    public void printStudent(){
        String rez = "\nName: "+this.getName()+"\nRegistration number: "+this.getRegistrationNumber()+"\n Project preferences / teacher:\n";
        for(var p : this.preferences){
            rez += p.getName() + " / " + p.getTeacher().getName() + "\n";
        }
        System.out.print(rez);
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
     * @param proposedProjects projects propuse de profesor
     */
    public Teacher(String name, LocalDate dateOfBirth, Project[] proposedProjects) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.proposedProjects = proposedProjects;
    }

    /**
     *
     * @return lista de projects
     */
    public Project[] getProposedProjects() {
        return proposedProjects;
    }

    /**
     *
     * @param proposedProjects projects propuse
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

class bonus_lab2{

    public static String toBase2(int n, int nrBits){

        //System.out.print("nrBits : ");System.out.println(nrBits);
        String rez="";
        int nr=0;
        while(n!=0){
            int c=n%2;
            //c==0?rez = rez + '0' : rez = rez + '1';
            rez = (c==0)? rez + '0' : rez + '1';
            n/=2;
            nr++;
        }
        for(int i=1;i <= nrBits-nr;i++)rez = rez + '0';
        return rez;
    }

    public static boolean checkProjectAlreadyWritten(Project project, Project[] uniqueProjects, int contor){
        for(int i = 0; i < contor; i++){
            if(project.equals(uniqueProjects[i]))return true;
        }
        return false;
    }

    public static void main(String[] args){

        System.out.println("****************\nAlgoritmul in primul rand se asigura ca are de oferit un numar de proiecte cel putin egal cu numarul de studenti, pentru ca altfel problema nu ar avea solutie oricum\n****************************\n\n");
        int nrProjects = 1+(int) (Math.random() * 1_000_000) % 20; System.out.print("nrProjects : "); System.out.println(nrProjects);
        int nrStudents = 1+(int) (Math.random() * 1_000_000) % nrProjects; System.out.print("nrStudents : ");System.out.println(nrStudents); System.out.println("**********");

        Teacher teacher1 = new Teacher("Frasinaru", LocalDate.of(1980, 5, 14), null);
        Teacher teacher2 = new Teacher("Patrut", LocalDate.of(1975, 8, 21), null);

        int nrProjectsTeacher1 = 1+(int) (Math.random() * 1_000_000) % nrProjects;
        int nrProjectsTeacher2 = nrProjects - nrProjectsTeacher1;

        Project[] projectList = new Project[nrProjects];
        Student[] studentList = new Student[nrStudents];
        for(int i = 0; i < nrProjectsTeacher1;i++) projectList[i] = new Project("project"+String.valueOf(i), teacher1);
        for(int i = nrProjectsTeacher1; i < nrProjects;i++) projectList[i] = new Project("project"+String.valueOf(i), teacher2);
        
        Project[] projectsTeacher1 = new Project[nrProjectsTeacher1]; for(int i=0;i < nrProjectsTeacher1; i++) projectsTeacher1[i] = projectList[i];
        Project[] projectsTeacher2 = new Project[nrProjectsTeacher2]; for(int i=0;i < nrProjectsTeacher2; i++) projectsTeacher2[i] = projectList[i+nrProjectsTeacher1];
        teacher1.setProposedProjects(projectsTeacher1);
        teacher2.setProposedProjects(projectsTeacher2);

        for(int i = 0; i < nrStudents; i++){
            int nrPreferences = 1+(int) (Math.random() * 1_000_000) % nrProjects;
            Project[] preferences = new Project[nrPreferences];

            int[] idxProjectList = new int[nrProjects];
            for(int j = 0; j < nrProjects; j++) idxProjectList[j] = j;
            for(int j = 0; j < nrProjects / 2; j++){
                int pos1 = (int) (Math.random() * 1_000_000) % nrProjects;
                int pos2 = (int) (Math.random() * 1_000_000) % nrProjects;
                int aux = idxProjectList[pos1];
                idxProjectList[pos1] = idxProjectList[pos2];
                idxProjectList[pos2]=aux;
            }

            for(int j = 0; j < nrPreferences; j++) preferences[j] = projectList[idxProjectList[j]];
            studentList[i] = new Student("student"+String.valueOf(i), LocalDate.of(2002, 1, 10), "RSL23"+String.valueOf(i), preferences);
        }

        int nrSubsets = (1<<(nrStudents))-1;
        int nrBits = nrStudents;
        Project[] uniqueElements = new Project[nrProjects];
        boolean existsRepartitionOfProjects = true;
        for(int i=1; i <= nrSubsets;i++){
            
            int contor = 0;
            int studentSubsetcount = 0;
            for(int j = 0; j < nrProjects; j++)uniqueElements[j] = null;
            String binary = toBase2(i, nrBits);
            for(int j = 0; j < nrBits; j++){
                if(binary.charAt(j)=='1'){

                    studentSubsetcount++;
                    Student student = studentList[j];
                    for(var p : student.getPreferences()){
                        if(uniqueElements.length ==0){
                            uniqueElements[0]=p;
                            contor = 1;
                        }
                        else{
                            if(!checkProjectAlreadyWritten(p, uniqueElements, contor)){
                                uniqueElements[contor] = p;
                                contor++;
                            }
                        }
                    }
                }
            }
            /*System.out.println("");System.out.print("nrBits : "); System.out.print(nrBits); System.out.print(" binary subset of students : "); System.out.println(binary); System.out.print("nr unique elements : "); System.out.print(contor); System.out.print(" studentSubsetCount : "); System.out.println(studentSubsetcount);*/

            if(contor < studentSubsetcount){
                existsRepartitionOfProjects = false;
                break;
            }
            //System.out.println(binary);
        }
        System.out.println(existsRepartitionOfProjects);
        for(var s : studentList) s.printStudent();
        System.out.println("\n");

        if(existsRepartitionOfProjects){

            System.out.println("Final distribution of projects: ");
            int[] frecvProjects = new int[nrProjects];
            for(int i = 0;i < nrProjects; i++) frecvProjects[i]=0;

            for(var s : studentList){
                for(var p : s.getPreferences()){
                    frecvProjects[Integer.valueOf(p.getName().substring(7))]++;
                }
            }

            //order every student s prefered projects by frecvprojects
            for(var s : studentList){
                boolean ok = true;
                while(ok == true){
                    ok = false;
                    for(int i = 0; i < s.getPreferences().length -1 ; i++){
                        Project p_i = s.getPreferences()[i];
                        Project p_i_1 = s.getPreferences()[i+1];
                        if(frecvProjects[Integer.valueOf(p_i.getName().substring(7))] > frecvProjects[Integer.valueOf(p_i_1.getName().substring(7))]){
                            ok = true;
                            Project aux = p_i;
                            s.changeAPreferedProject(p_i_1, i);
                            s.changeAPreferedProject(aux, i+1);
                        }
                    }
                }
            }

            //order students by nr of options
            boolean ok = true;
            while(ok == true){
                ok = false;
                for(int i =0; i < nrStudents-1; i++) if(studentList[i].getPreferences().length > studentList[i+1].getPreferences().length){
                    ok = true;
                    var aux = studentList[i];
                    studentList[i] = studentList[i+1];
                    studentList[i+1] = aux;
                }
            }

            for(int i = 0;i < nrProjects; i++) frecvProjects[i]=0;
            Student[] solutionStudents = new Student[nrStudents];
            Project[] solutionProjects = new Project[nrProjects];
            nrBits = 0; //reuse variable nrBits

            
            for(var s : studentList){
                
                solutionStudents[nrBits] = s;
                Project[] preference_s=s.getPreferences();
                for(var p : preference_s){
                    if(frecvProjects[Integer.valueOf(p.getName().substring(7))]==0){
                        frecvProjects[Integer.valueOf(p.getName().substring(7))]=1;
                        solutionProjects[nrBits] = p;
                        break;
                    }
                }
                nrBits++;
            }

            ok = true;
            while(ok ==true){
                ok = false;
                for(int i = 0; i < nrStudents-1; i++){
                    if(Integer.valueOf(solutionStudents[i].getName().substring(7)) > Integer.valueOf(solutionStudents[i+1].getName().substring(7))){
                        var auxs = solutionStudents[i]; solutionStudents[i] = solutionStudents[i+1]; solutionStudents[i+1] = auxs;
                        var auxp = solutionProjects[i]; solutionProjects[i] = solutionProjects[i+1]; solutionProjects[i+1] = auxp;
                        ok = true;
                    }
                }
            }
            for(int i = 0; i < nrStudents; i++){
                System.out.print(solutionStudents[i].getName()); System.out.print(" -> ");
                System.out.print(solutionProjects[i].getName()); System.out.println("");
            }
        }
        else System.out.println("No solution");
    }
}
