import com.sun.org.apache.xpath.internal.operations.String;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Ars on 14.10.2017.
 */
public class ManagementSystem {
    private List<Group> groups;
    private Collection<Student> students;

    private static ManagementSystem instance;

    private ManagementSystem(){
        loadGroups();
        loadStudents();
    }

    private  static synchronized  ManagementSystem getInstance() {
        if (instance == null) {
            instance=new ManagementSystem();
        }
        return instance;
    }

    public static void main(String[] args) {
       try {
            System.setOut(new PrintStream("out.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        ManagementSystem ms=ManagementSystem.getInstance();

        printString("Полный список групп");
        printString("*******************");
        List<Group> allGroups=ms.getGroups();
        for (Group gi : allGroups) {
            printString(gi);
        }
        printString();

        printString("Полный список студентов");
        printString("***********************");
        Collection<Student> allStudents=ms.getAllStudents();
        for (Student si : allStudents) {
            printString(si);
        }
        printString();

        printString("Список студентов по группам");
        printString("***************************");
        List<Group> groups=ms.getGroups();
        for (Group gi : groups) {
            printString("--> Группа:" + gi.getNameGroup());
            Collection<Student> students=ms.getStudentsFromGroup(gi,2006);
            for (Student si : students) {
                printString(si);
            }
        }
        printString();

        Student s = new Student();
        s.setStudentId(5);
        s.setFirstName("Igor");
        s.setPatronymic("Vladimirovich");
        s.setSurName("Perebejkin");
        s.setSex('M');
        Calendar c=Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("Добавляем студента: "+s);
        printString("**********************");
        ms.insertStudent(s);
        printString("-->> Полный список студентов после добавления");
        allStudents=ms.getAllStudents();
        for (Student si:allStudents) {
            printString(si);
        }
        printString();

        s=new Student();
        s.setStudentId(5);
        s.setFirstName("Igor");
        s.setPatronymic("Vladimirovich");
        s.setSurName("Novoperebejkin");
        s.setSex('M');
        c=Calendar.getInstance();
        c.set(1991,8,31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("Редактирование данных студента: " + s);
        printString("**************************");
        ms.updateStudent(s);
        printString("-->> Полный список студентов после редактирования");
        allStudents=ms.getAllStudents();
        for (Student si : allStudents) {
            printString(si);
        }
        printString();

        printString("Удаление студента: " + s);
        printString("*************************");
        ms.deleteStudent(s);
        printString("-->> Полный список студентов после удаления");
        allStudents=ms.getAllStudents();
        for (Student si : allStudents) {
            printString(si);
        }
        printString();

        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        printString("Перевод студентов из 1-ой во 2-ую группу");
        printString("****************************************");
        ms.moveStudentsToGroup(g1,2006,g2,2007);
        printString("-->> Полный список студентов после перевода");
        allStudents=ms.getAllStudents();
        for (Student si : allStudents) {
            printString(si);
        }
        printString();

        printString("Удаление студентов из группы: " + g2 + " в 2006 году");
        printString("********************************");
        ms.removeStudentsFromGroup(g2, 2006);
        printString("-->> Полный список студентов после удаления");
        allStudents=ms.getAllStudents();
        for (Student si : allStudents) {
            printString(si);
        }
        printString();

    }

    public void loadGroups() {

        if (groups == null) {
            groups = new ArrayList<Group>();
        } else {
            groups.clear();
        }

        Group g = null;
        g = new Group();
        g.setGroupId(1);
        g.setNameGroup("First");
        g.setCurator("Doctor Bormental");
        g.setSpeciality("Make man from dog");
        groups.add(g);

        g = new Group();
        g.setGroupId(2);
        g.setNameGroup("Second");
        g.setCurator("Professor Preobrajenski");
        g.setSpeciality("Make man from dog");
        groups.add(g);
    }

    public void loadStudents() {
        if (students == null) {
            students = new TreeSet<Student>();
        } else {
            students.clear();
        }
        Student s=null;
        Calendar c=Calendar.getInstance();

        s=new Student();
        s.setStudentId(1);
        s.setFirstName("Ivan");
        s.setPatronymic("Sergeevich");
        s.setSurName("Stepanov");
        s.setSex('M');
        c.set(1990,3,20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        s = new Student();
        s.setStudentId(2);
        s.setFirstName("Наталья");
        s.setPatronymic("Андреевна");
        s.setSurName("Чичикова");
        s.setSex('Ж');
        c.set(1990, 6, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        // Первая группа
        s = new Student();
        s.setStudentId(3);
        s.setFirstName("Петр");
        s.setPatronymic("Викторович");
        s.setSurName("Сушкин");
        s.setSex('М');
        c.set(1991, 3, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);

        s = new Student();
        s.setStudentId(4);
        s.setFirstName("Вероника");
        s.setPatronymic("Сергеевна");
        s.setSurName("Ковалева");
        s.setSex('Ж');
        c.set(1991, 7, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);
    }

    public List<Group> getGroups() {
        return  groups;
    }

    public Collection<Student> getAllStudents() {
        return students;
    }

    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> l = new TreeSet<Student>();
        for (Student si:students) {
            if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }

    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
    }

    public void removeStudentsFromGroup(Group group, int year) {
        Collection<Student> tmp = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() != group.getGroupId() || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students=tmp;
    }

    public void insertStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(Student student) {
        Student updStudent=null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                updStudent=si;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setPatronymic(student.getPatronymic());
        updStudent.setSurName(student.getSurName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
    }

    public void deleteStudent(Student student) {
        Student delStudent=null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                delStudent=si;
                break;
            }
        }
        students.remove(delStudent);
    }

    public static void printString(Object s) {
        System.out.println(s.toString());
    }

    public static void printString(){
        System.out.println();
    }

}
