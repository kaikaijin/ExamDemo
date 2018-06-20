package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/*
 *类名和方法不能修改
 */
public class Schedule {
    public static Schedule instance = new Schedule();
    public static List<Integer> nodeNumList = new ArrayList<Integer>();
    public static List<Integer> taskNumList = new ArrayList<Integer>();
    public static List<TaskInfo> tasks = new ArrayList<TaskInfo>();
    public static Map<Integer, Integer> params = new HashMap<Integer, Integer>();
    public static Schedule getInstance() {
        return instance;
    }

    //系统初始化
    public int init() {
        for (int i = 0; i < tasks.size(); i++) {
            TaskInfo t = tasks.get(i);
            Schedule.getInstance().deleteTask(t.getTaskId());
            Schedule.getInstance().unregisterNode(t.getNodeId());
        }
        return ReturnCodeKeys.E001;
    }

    //服务节点注册
    public int registerNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        for (int i = 0; i < nodeNumList.size(); i++) {
            if (new Integer(nodeId) == nodeNumList.get(i)) {
                return ReturnCodeKeys.E005;
            } else {
                nodeNumList.set(nodeNumList.size() + 1, nodeId);
                TaskInfo t1 = new TaskInfo();
                t1.setNodeId(nodeId);
                tasks.set(tasks.size() + 1, t1);
            }
        }
        return ReturnCodeKeys.E003;
    }

    //服务节点注销
    public int unregisterNode(int nodeId) {

        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        for (int i = 0; i < nodeNumList.size(); i++) {
            if (new Integer(nodeId) == nodeNumList.get(i)) {
                nodeNumList.remove(i);
            } else {
                return ReturnCodeKeys.E007;
            }
        }
        return ReturnCodeKeys.E006;
    }

    //添加任务
    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        for (int i = 0; i < taskNumList.size(); i++) {
            if (new Integer(taskId) == taskNumList.get(i)) {
                return ReturnCodeKeys.E010;
            } else {
                taskNumList.set(taskNumList.size() + 1, taskId);
                TaskInfo t1 = new TaskInfo();
                t1.setTaskId(taskId);
                tasks.set(tasks.size() + 1, t1);
                params.put(taskId,consumption);
            }
        }
        return ReturnCodeKeys.E008;
    }

    //删除任务
    public int deleteTask(int taskId) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        for (int i = 0; i < nodeNumList.size(); i++) {
            if (new Integer(taskId) == nodeNumList.get(i)) {
                nodeNumList.remove(i);
            } else {
                return ReturnCodeKeys.E012;
            }
        }
        return ReturnCodeKeys.E011;
    }

    //任务调度
    public int scheduleTask(int threshold) {
       if(threshold<=0){
           return ReturnCodeKeys.E002;
       }
//       if(threshold>){
//           return ReturnCodeKeys.E013;
//       }
        return ReturnCodeKeys.E000;
    }

    //查询当前任务状态
    public int queryTaskStatus(List<TaskInfo> tasks) {
        if(tasks==null){
            return ReturnCodeKeys.E016;
        }
        return ReturnCodeKeys.E000;
    }

}
