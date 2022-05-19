package com.j2ee.edu_admi.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class CourseManagementBean {

    private final DBUtil dbUtil;
    //一页可以展示的最大课程数
    private static final int MAXLENGTH = 8;

    public CourseManagementBean() throws ClassNotFoundException {
        dbUtil = DBUtil.getDBUtil();
    }

    public int getPageNum() {
        int count = getCourseListCount();
        return count / MAXLENGTH+1;

    }

    //获取课程列表，通过page控制页数，每页有MAXLENGTH个数据
    public List<Course> getCourseList(int page) {
        try {
            Connection connection = dbUtil.connectDB();
            String sql = "select * from courses where cno >= ? and cno <= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page - 1) * MAXLENGTH + 1);
            preparedStatement.setInt(2, page * MAXLENGTH);
            ResultSet result = preparedStatement.executeQuery();
            List<Course> courses = new LinkedList<>();
            int count = 0;
            while (result.next()) {
                if (count > MAXLENGTH) {
                    break;
                }
                Course course = new Course(
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getInt(7),
                        result.getInt(8)
                );
                courses.add(course);
                count++;
            }
            while (count <= MAXLENGTH) {
                Course course = new Course();
                courses.add(course);
                count++;
            }
            preparedStatement.close();
            result.close();
            dbUtil.closeConnect(connection);
            //将课程列表返回
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public int getCourseListCount() {
        try {
            Connection connection = dbUtil.connectDB();
            String sql = "select * from courses";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            int courseCount = 0;
            while (result.next()) {
                courseCount++;
            }
            preparedStatement.close();
            result.close();
            dbUtil.closeConnect(connection);
            //将课程列表返回
            return courseCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
