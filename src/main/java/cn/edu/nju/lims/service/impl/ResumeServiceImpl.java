package cn.edu.nju.lims.service.impl;

import cn.edu.nju.lims.service.ResumeService;
import org.springframework.stereotype.Service;

@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

    @Override
    public void storeResume() {
        System.out.println("任务已经执行.....................................");
    }
}
