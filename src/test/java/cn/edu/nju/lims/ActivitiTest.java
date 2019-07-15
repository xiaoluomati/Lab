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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
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
    }

    @Test
    public void TestComposeReport() {
        String assignee = "composer";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        for (Task task : tasks) {
            System.out.println("taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
        }
    }


}
