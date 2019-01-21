package leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;

    public Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }
};

/**
 * 给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。
 * 比如，员工1是员工2的领导，员工2是员工3的领导。
 * 他们相应的重要度为15, 10, 5。那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，
 * 员工3的数据结构是[3, 5, []]。注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，
 * 因此没有体现在员工1的数据结构中。
 * 现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
 */
public class No690EmployeeImportance {
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }

        int importanceSum = 0;
        Employee currentEmployee = getEmployeeById(employees, id);
        importanceSum += currentEmployee.importance;

        if (currentEmployee.subordinates != null && currentEmployee.subordinates.size() > 0) {
            for (Integer subId : currentEmployee.subordinates) {
                importanceSum += getImportance(employees, subId);
            }
        }

        return importanceSum;
    }

    private Employee getEmployeeById(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            if (employee.id == id) {
                return employee;
            }
        }
        throw new RuntimeException("error");
    }

    /**
     * 输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
     * 输出: 11
     * 解释:
     * 员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是 5 + 3 + 3 = 11。
     */
    @Test
    public void testCase1() {
        List<Employee> employees = List.of(new Employee(1, 5, List.of(2, 3)),
                new Employee(2, 3, new ArrayList<>()),
                new Employee(3, 3, new ArrayList<>()));
        int exceptResult = 11;
        int result = getImportance(employees, 1);

        Assert.assertEquals(exceptResult, result);
    }
}
