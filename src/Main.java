import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("Tom", 1, 200000, 2);
        Employee e2 = new Employee("John", 2, 300000, 4);
        Employee e3 = new Employee("Shaun", 3, 250000, 2);
        Employee e4 = new Employee("Robert", 4, 500000, null);

        List<Employee> employeeList = new ArrayList<Employee>(){{add(e1); add(e2); add(e3); add(e4);}};

        // get total salary expenses
        System.out.println(totalSalaryExpenses(employeeList));
        // get second highest salary
        System.out.println(secondHighestSalary(employeeList));
        // get manager name for given employee
        System.out.println(getManagerName("Tom", employeeList));
        // sort with salary in ascending order
        sortEmployeeBasedOnSalary(employeeList).stream().forEach(e -> System.out.println(e.getEmployeeName()+" "+e.getEmployeeSalary()));
        // sort with employee name
        sortEmployeeBasedOnNameDescending(employeeList).stream().forEach(e -> System.out.println(e.getEmployeeName()));
    }
    public static Integer totalSalaryExpenses(List<Employee> employeeList){
        return employeeList.stream().mapToInt(Employee::getEmployeeSalary).reduce(0, (salary1, salary2) -> salary1+salary2);
    }

    public static String getManagerName(String name, List<Employee> employeeList) {
        Integer managerId = employeeList.stream().
                filter(p -> p.getEmployeeName().equals(name)).
                findFirst().get().getEmployeeManagerID();
        return employeeList.stream().filter(p -> p.getEmployeeID().equals(managerId)).findFirst().get().getEmployeeName();
    }

    public static Integer secondHighestSalary(List<Employee> employeeList) {
        Optional<Employee> result = employeeList.stream()
                .sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed()).skip(1).findFirst();
        return result.get().getEmployeeSalary();
    }

    public static List<Employee> sortEmployeeBasedOnSalary(List<Employee> employeeList) {
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::getEmployeeSalary)).collect(Collectors.toList());
    }

    public static List<Employee> sortEmployeeBasedOnNameDescending(List<Employee> employeeList) {
        return employeeList.stream().sorted(Comparator.comparing(Employee::getEmployeeName).reversed()).collect(Collectors.toList());
    }


}


class Employee {
    private String employeeName;
    private Integer employeeID;
    private Integer employeeSalary;
    private Integer employeeManagerID;

    public Employee(String employeeName, Integer employeeID, Integer employeeSalary, Integer employeeManagerID) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeSalary = employeeSalary;
        this.employeeManagerID = employeeManagerID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Integer getEmployeeManagerID() {
        return employeeManagerID;
    }

    public void setEmployeeManagerID(Integer employeeManagerID) {
        this.employeeManagerID = employeeManagerID;
    }
}
