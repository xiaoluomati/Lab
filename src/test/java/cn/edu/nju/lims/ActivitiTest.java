package cn.edu.nju.lims;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = LimsApplication.class)
public class ActivitiTest {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    TaskService taskService;

    @Test
    public void TestStartProcess() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("reportName", "reportA");
        variables.put("reportItemA", "High");
        variables.put("reportItemB", "Low");
        runtimeService.startProcessInstanceByKey("report", variables);
        TestComposeReport();
        TestCheckReport();
        TestComposeReport();
        TestPrintReport();
    }

    private void TestComposeReport() {
        String assignee = "composer";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        System.out.println(tasks.size());
        for (Task task : tasks) {
            String id = task.getId();
            System.out.println("taskId:" + id +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
            System.out.println("reportName = " + taskService.getVariable(id, "reportName"));
            taskService.complete(task.getId());
        }
    }

    private void TestCheckReport() {
        String assignee = "checker";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        System.out.println(tasks.size());
        for (Task task : tasks) {
            System.out.println("taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
            taskService.setVariable(task.getId(), "isAgree", false);
            taskService.complete(task.getId());
        }
    }

    private void TestPrintReport(){
        String assignee = "user";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        System.out.println(tasks.size());
        for (Task task : tasks) {
            System.out.println("taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
            taskService.complete(task.getId());
        }
    }


}
