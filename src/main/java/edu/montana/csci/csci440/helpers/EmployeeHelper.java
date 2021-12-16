package edu.montana.csci.csci440.helpers;

import edu.montana.csci.csci440.model.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmployeeHelper {
    public static String makeEmployeeTree() {
        // TODO, change this to use a single query operation to get all employees
        List<Employee> employee = Employee.all(); // root employee
        // and use this data structure to maintain reference information needed to build the tree structure
        Map<Long, List<Employee>> employeeMap = new HashMap<>();
        for (Employee em : employee) {
            if (employeeMap.get(em.getReportsTo()) == null) {
                employeeMap.put(em.getReportsTo(), new LinkedList<>());
            }
            employeeMap.get(em.getReportsTo()).add(em);

        }
        return "<ul>" + makeTree(employee.get(0), employeeMap) + "</ul>";
    }

    // TODO - currently this method just uses the employee.getReports() function, which
    //  issues a query.  Change that to use the employeeMap variable instead
    public static String makeTree(Employee employee, Map<Long, List<Employee>> employeeMap) {
        String list = "<li><a href='/employees" + employee.getEmployeeId() + "'>"
                + employee.getEmail() + "</a><ul>";
        List<Employee> rp = employeeMap.get(employee.getEmployeeId());
        if (rp != null) {
            for (Employee report : rp) {
                list += makeTree(report, employeeMap);
            }
        }
        return list + "</ul></li>";
    }
}
