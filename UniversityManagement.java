import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Department class to store department information
class Department 
{
private String departmentId;
private String departmentName;
private ZonedDateTime establishmentDate;

public Department(String departmentId, String departmentName, ZonedDateTime establishmentDate) 
{
    this.departmentId = departmentId;
    this.departmentName = departmentName;
    this.establishmentDate = establishmentDate;
}

public String getDepartmentId() 
{
    return departmentId;
}

public String getDepartmentName() 
{
    return departmentName;
}

public ZonedDateTime getEstablishmentDate() 
{
    return establishmentDate;
}

@Override
public String toString() 
{
    return String.format("Department ID: %s, Name: %s, Established: %s",
            departmentId, departmentName, establishmentDate.toLocalDate());
}
}

// Employee class to store employee information
class Employee 
{
private String employeeId;
private ZonedDateTime dateOfBirth;
private ZonedDateTime dateOfJoining;
private String departmentId;

public Employee(String employeeId, ZonedDateTime dateOfBirth, ZonedDateTime dateOfJoining, String departmentId) 
{
    this.employeeId = employeeId;
    this.dateOfBirth = dateOfBirth;
    this.dateOfJoining = dateOfJoining;
    this.departmentId = departmentId;
}

public String getEmployeeId() 
{
    return employeeId;
}

public ZonedDateTime getDateOfBirth() 
{
    return dateOfBirth;
}

public ZonedDateTime getDateOfJoining() 
{
    return dateOfJoining;
}

public String getDepartmentId() 
{
    return departmentId;
}

@Override
public String toString() 
{
    return String.format("Employee ID: %s, Date of Birth: %s, Date of Joining: %s, Department ID: %s",
            employeeId, dateOfBirth.toLocalDate(), dateOfJoining.toLocalDate(), departmentId);
}
}

// StudentCounseling class to store student counseling information
class StudentCounseling 
{
private String studentId;
private ZonedDateTime dateOfAdmission;
private ZonedDateTime dateOfBirth;
private String choiceOfDepartment;
private String departmentAdmittedTo;

public StudentCounseling(String studentId, ZonedDateTime dateOfAdmission, ZonedDateTime dateOfBirth, String choiceOfDepartment, String departmentAdmittedTo) 
{
    this.studentId = studentId;
    this.dateOfAdmission = dateOfAdmission;
    this.dateOfBirth = dateOfBirth;
    this.choiceOfDepartment = choiceOfDepartment;
    this.departmentAdmittedTo = departmentAdmittedTo;
}

public String getStudentId() 
{
    return studentId;
}

public ZonedDateTime getDateOfAdmission() 
{
    return dateOfAdmission;
}

public ZonedDateTime getDateOfBirth() 
{
    return dateOfBirth;
}

public String getChoiceOfDepartment() 
{
    return choiceOfDepartment;
}

public String getDepartmentAdmittedTo() 
{
    return departmentAdmittedTo;
}

@Override
public String toString() 
{
    return String.format("Student ID: %s, Date of Admission: %s, Date of Birth: %s, Choice of Department: %s, Department Admitted: %s",
            studentId, dateOfAdmission.toLocalDate(), dateOfBirth.toLocalDate(), choiceOfDepartment, departmentAdmittedTo);
}
}

// StudentPerformance class to store student performance information
class StudentPerformance 
{
private String studentId;
private String semesterName;
private String paperId;
private String paperName;
private int marks;

public StudentPerformance(String studentId, String semesterName, String paperId, String paperName, int marks) 
{
    this.studentId = studentId;
    this.semesterName = semesterName;
    this.paperId = paperId;
    this.paperName = paperName;
    this.marks = marks;
}

public String getStudentId() 
{
    return studentId;
}

public String getSemesterName() 
{
    return semesterName;
}

public String getPaperId() 
{
    return paperId;
}

public String getPaperName() 
{
    return paperName;
}

public int getMarks() 
{
    return marks;
}

@Override
public String toString() 
{
    return String.format("Student ID: %s, Semester: %s, Paper ID: %s, Paper Name: %s, Marks: %d",
            studentId, semesterName, paperId, paperName, marks);
}
}

// Interface for reading data from CSV files
interface iReadFiles<T> 
{
List<T> readData(String filePath) throws IOException;
}

// Class for reading department data from CSV files
class DepartmentFileReader implements iReadFiles<Department> 
{
private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

@Override
public List<Department> readData(String filePath) throws IOException 
{
    List<Department> departments = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    String line = br.readLine();
    
    while ((line = br.readLine()) != null) 
    {
        String[] values = line.split(",");
        
        if (values.length < 3)
        {
            continue;
        }
        departments.add(new Department(values[0], values[1], ZonedDateTime.parse(values[2], dateTimeFormatter)));
    }
    br.close();
    return departments;
}
}

// Class for reading employee data from CSV files
class EmployeeFileReader implements iReadFiles<Employee> 
{
private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

@Override
public List<Employee> readData(String filePath) throws IOException 
{
    List<Employee> employees = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    String line = br.readLine();
    
    while ((line = br.readLine()) != null) 
    {
        String[] values = line.split(",");
        
        if (values.length < 4) 
        {
            continue;
        }
        employees.add(new Employee(values[0], ZonedDateTime.parse(values[1], dateTimeFormatter), ZonedDateTime.parse(values[2], dateTimeFormatter), values[3]));
    }
    br.close();
    return employees;
}
}

// Class for reading student counseling data from CSV files
class StudentCounselingFileReader implements iReadFiles<StudentCounseling> 
{
private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

@Override
public List<StudentCounseling> readData(String filePath) throws IOException 
{
    List<StudentCounseling> studentCounselings = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    String line = br.readLine(); // Skip header
    
    while ((line = br.readLine()) != null) 
    {
        String[] values = line.split(",");
        
        if (values.length < 5) 
        {
            continue;
        }
        studentCounselings.add(new StudentCounseling(values[0], ZonedDateTime.parse(values[1], dateTimeFormatter), ZonedDateTime.parse(values[2], dateTimeFormatter), values[3], values[4]));
    }
    br.close();
    return studentCounselings;
}
}

// Class for reading student performance data from CSV files
class StudentPerformanceFileReader implements iReadFiles<StudentPerformance> 
{
@Override
public List<StudentPerformance> readData(String filePath) throws IOException 
{
    List<StudentPerformance> performances = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    String line = br.readLine();
    
    while ((line = br.readLine()) != null) 
    {
        String[] values = line.split(",");
        
        if (values.length < 5) 
        {
            continue;
        }
        performances.add(new StudentPerformance(values[0], values[1], values[2], values[3], Integer.parseInt(values[4])));
    }
    br.close();
    return performances;
}
}

// Repository interfaces
interface DepartmentRepository 
{
Department getDepartmentById(String departmentId);
List<StudentCounseling> getStudentsByDepartment(String departmentId);
List<Employee> getEmployeesByDepartment(String departmentId);
DepartmentStatistics calculateDepartmentStatistics(String departmentId);
}

interface EmployeeRepository 
{
Employee getEmployeeById(String employeeId);
}

interface StudentRepository 
{
StudentCounseling getStudentCounselingById(String studentId);
List<StudentCounseling> getStudentsInDepartmentByDate(String departmentId, ZonedDateTime birthStart, ZonedDateTime birthEnd, ZonedDateTime admissionStart, ZonedDateTime admissionEnd);
StudentPerformance getStudentPerformanceById(String studentId);
List<StudentPerformance> getStudentPerformancesForStudent(String studentId);
StudentStatistics calculateStudentStatistics(String studentId);
}

// UniversityManagement class to manage university data and calculate statistics
public class UniversityManagement implements DepartmentRepository, EmployeeRepository, StudentRepository 
{
private List<Department> departments;
private List<Employee> employees;
private List<StudentCounseling> studentCounselings;
private List<StudentPerformance> studentPerformances;

public UniversityManagement(iReadFiles<Department> departmentReader, iReadFiles<Employee> employeeReader, 
                            iReadFiles<StudentCounseling> studentCounselingReader, iReadFiles<StudentPerformance> studentPerformanceReader, 
                            String deptPath, String empPath, String studCounPath, String studPerfPath) throws IOException 
{
    this.departments = departmentReader.readData(deptPath);
    this.employees = employeeReader.readData(empPath);
    this.studentCounselings = studentCounselingReader.readData(studCounPath);
    this.studentPerformances = studentPerformanceReader.readData(studPerfPath);
}

@Override
public Department getDepartmentById(String departmentId) 
{
    for (Department department : departments) 
    {
        if (department.getDepartmentId().equals(departmentId)) 
        {
            return department;
        }
    }
    return null;
}

@Override
public Employee getEmployeeById(String employeeId) 
{
    for (Employee employee : employees) {
        if (employee.getEmployeeId().equals(employeeId)) 
        {
            return employee;
        }
    }
    return null;
}

@Override
public StudentCounseling getStudentCounselingById(String studentId) 
{
    for (StudentCounseling studentCounseling : studentCounselings) 
    {
        if (studentCounseling.getStudentId().equals(studentId)) 
        {
            return studentCounseling;
        }
    }
    return null;
}

@Override
public StudentPerformance getStudentPerformanceById(String studentId) 
{
    for (StudentPerformance studentPerformance : studentPerformances) 
    {
        if (studentPerformance.getStudentId().equals(studentId)) 
        {
            return studentPerformance;
        }
    }
    return null;
}

@Override
public List<StudentCounseling> getStudentsByDepartment(String departmentId) 
{
    List<StudentCounseling> result = new ArrayList<>();
    
    for (StudentCounseling studentCounseling : studentCounselings) 
    {
        if (studentCounseling.getDepartmentAdmittedTo().equals(departmentId)) 
        {
            result.add(studentCounseling);
        }
    }
    return result;
}

@Override
public List<Employee> getEmployeesByDepartment(String departmentId) 
{
    List<Employee> result = new ArrayList<>();
    
    for (Employee employee : employees) 
    {
        if (employee.getDepartmentId().equals(departmentId)) 
        {
            result.add(employee);
        }
    }
    return result;
}

@Override
public List<StudentPerformance> getStudentPerformancesForStudent(String studentId) 
{
    List<StudentPerformance> result = new ArrayList<>();
    for (StudentPerformance studentPerformance : studentPerformances) 
    {
        if (studentPerformance.getStudentId().equals(studentId)) 
        {
            result.add(studentPerformance);
        }
    }
    return result;
}

@Override
public List<StudentCounseling> getStudentsInDepartmentByDate(String departmentId, ZonedDateTime birthStart, ZonedDateTime birthEnd, ZonedDateTime admissionStart, ZonedDateTime admissionEnd) 
{
    List<StudentCounseling> result = new ArrayList<>();
    for (StudentCounseling studentCounseling : studentCounselings) 
    {
        if (studentCounseling.getDepartmentAdmittedTo().equals(departmentId) &&
            (studentCounseling.getDateOfBirth().isEqual(birthStart) || studentCounseling.getDateOfBirth().isAfter(birthStart)) &&
            (studentCounseling.getDateOfBirth().isEqual(birthEnd) || studentCounseling.getDateOfBirth().isBefore(birthEnd)) &&
            (studentCounseling.getDateOfAdmission().isEqual(admissionStart) || studentCounseling.getDateOfAdmission().isAfter(admissionStart)) &&
            (studentCounseling.getDateOfAdmission().isEqual(admissionEnd) || studentCounseling.getDateOfAdmission().isBefore(admissionEnd))) {
            result.add(studentCounseling);
        }
    }
    return result;
}

@Override
public StudentStatistics calculateStudentStatistics(String studentId) 
{
    List<StudentPerformance> performances = getStudentPerformancesForStudent(studentId);
    int maxMarks = 0;
    int minMarks = Integer.MAX_VALUE;
    double averageMarks = 0.0;
    int totalMarks = 0;
    int numPapers = performances.size();
    int numSemesters = 0;

    for (StudentPerformance performance : performances) 
    {
        int marks = performance.getMarks();
        totalMarks += marks;
        if (marks > maxMarks) 
        {
            maxMarks = marks;
        }
        if (marks < minMarks) 
        {
            minMarks = marks;
        }
    }

    if (numPapers > 0) 
    {
        averageMarks = (double) totalMarks / numPapers;
    }

    List<String> semesters = new ArrayList<>();
    for (StudentPerformance performance : performances) 
    {
        if (!semesters.contains(performance.getSemesterName())) 
        {
            semesters.add(performance.getSemesterName());
        }
    }
    numSemesters = semesters.size();

    return new StudentStatistics(maxMarks, minMarks, averageMarks, totalMarks, numPapers, numSemesters);
}

@Override
public DepartmentStatistics calculateDepartmentStatistics(String departmentId) 
{
    List<Employee> deptEmployees = getEmployeesByDepartment(departmentId);
    List<StudentCounseling> deptStudents = getStudentsByDepartment(departmentId);
    int numEmployees = deptEmployees.size();
    int numStudents = deptStudents.size();
    return new DepartmentStatistics(numEmployees, numStudents);
}

public static void main(String[] args) 
{
    try 
    {
        iReadFiles<Department> departmentReader = new DepartmentFileReader();
        iReadFiles<Employee> employeeReader = new EmployeeFileReader();
        iReadFiles<StudentCounseling> studentCounselingReader = new StudentCounselingFileReader();
        iReadFiles<StudentPerformance> studentPerformanceReader = new StudentPerformanceFileReader();

        UniversityManagement um = new UniversityManagement(departmentReader, employeeReader, studentCounselingReader, studentPerformanceReader,
                "C:\\Users\\msoth\\OneDrive\\Desktop\\AP-Code\\Department_Information.csv",
                "C:\\Users\\msoth\\OneDrive\\Desktop\\AP-Code\\Employee_Information.csv",
                "C:\\Users\\msoth\\OneDrive\\Desktop\\AP-Code\\Student_Counceling_Information.csv",
                "C:\\Users\\msoth\\OneDrive\\Desktop\\AP-Code\\Student_Performance_Data.csv");

        System.out.println("===== Department Information =====");
        Department dept = um.getDepartmentById("IDEPT4670");
        
        if (dept != null) 
        {
            System.out.println(dept);
        }

        System.out.println("\n===== Employees in Department =====");
        List<Employee> employees = um.getEmployeesByDepartment("IDEPT4670");
        
        for (Employee employee : employees) 
        {
            System.out.println(employee);
        }

        System.out.println("\n===== Student Counseling Information =====");
        StudentCounseling sc = um.getStudentCounselingById("SID20131143");
        
        if (sc != null) 
        {
            System.out.println(sc);
        }

        System.out.println("\n===== Student Performance Information =====");
        StudentPerformance sp = um.getStudentPerformanceById("SID20131143");
        
        if (sp != null) 
        {
            System.out.println(sp);
        }

        System.out.println("\n===== Filtered Students by Dates =====");
        List<StudentCounseling> filteredStudents = um.getStudentsInDepartmentByDate("IDEPT4670",
                                                      ZonedDateTime.parse("1990-01-01T00:00:00Z"),
                                                      ZonedDateTime.parse("2000-01-01T00:00:00Z"),
                                                      ZonedDateTime.parse("2012-01-01T00:00:00Z"),
                                                      ZonedDateTime.parse("2013-12-31T23:59:59Z"));
        
        for (StudentCounseling student : filteredStudents) 
        {
            System.out.println(student);
        }

        System.out.println("\n===== Student Statistics =====");
        StudentStatistics stats = um.calculateStudentStatistics("SID20131143");
        
        if (stats != null) 
        {
            System.out.println(stats);
        }

        System.out.println("\n===== Department Statistics =====");
        DepartmentStatistics deptStats = um.calculateDepartmentStatistics("IDEPT4670");
        
        if (deptStats != null) 
        {
            System.out.println(deptStats);
        }

    } 
    catch (IOException e) 
    {
        System.out.println("Error reading files: " + e.getMessage());
    }
}
}

// StudentStatistics class to store student statistics
class StudentStatistics 
{
int maxMarks;
int minMarks;
double averageMarks;
int totalMarks;
int numPapers;
int numSemesters;

public StudentStatistics(int maxMarks, int minMarks, double averageMarks, int totalMarks, int numPapers, int numSemesters) 
{
    this.maxMarks = maxMarks;
    this.minMarks = minMarks;
    this.averageMarks = averageMarks;
    this.totalMarks = totalMarks;
    this.numPapers = numPapers;
    this.numSemesters = numSemesters;
}

@Override
public String toString() 
{
    return String.format("Max Marks: %d, Min Marks: %d, Average Marks: %.2f, Total Marks: %d, Papers: %d, Semesters: %d",
            maxMarks, minMarks, averageMarks, totalMarks, numPapers, numSemesters);
}

}

// DepartmentStatistics class to store department statistics
class DepartmentStatistics 
{
int numEmployees;
int numStudents;

public DepartmentStatistics(int numEmployees, int numStudents) 
{
    this.numEmployees = numEmployees;
    this.numStudents = numStudents;
}

@Override
public String toString() 
{
    return String.format("Employees: %d, Students: %d", numEmployees, numStudents);
}

}