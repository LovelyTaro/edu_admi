package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.Student;
import com.j2ee.edu_admi.beans.User;

import java.util.List;

public class StudentDaoImpl extends BaseDao<Student> implements StudentDao {

    @Override
    public Student getStudentByNum(int studentNum) throws Exception {
        Student student = null;
        String sql = "select * from students where studentNum =?";
        List<Student> list = this.queryForList(sql, studentNum);
        if (list.size() != 0) {
            student = list.get(0);
        }
        return student;
    }

    @Override
    public Student getStudentByName(String studentName) throws Exception {
        Student student = null;
        String sql = "select * from students where studentName =?";
        List<Student> list = this.queryForList(sql, studentName);
        if (list.size() != 0) {
            student = list.get(0);
        }
        return student;
    }

    @Override
    public void insertStudent(Student student) throws Exception {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserNameExist(student.getUsername())){
            throw new Exception();
        }else{
            userDao.insertUser(student);
            User user = userDao.getUser(student.getUsername());
            String sql = "insert into students(userNum,studentName,gender,facultyNum,birth) values(?,?,?,?,?)";
            this.executeUpdate(sql, user.getUserNum(), student.getStudentName(), student.getGender(), student.getFacultyNum(), student.getBirth());
        }
    }

    //通过外键关联删除
    @Override
    public void deleteStudentByNum(Integer studentNum) throws Exception {
        UserDao userDao = new UserDaoImpl();
        userDao.deleteUserByUserNum(getStudentByNum(studentNum).getUserNum());
    }

    @Override
    public Integer getStudentCount() throws Exception {
        String sql = "select count(*) from students";
        return Integer.valueOf(this.queryForValue(sql).toString());
    }

    @Override
    public List<Student> getStudentList(int page) throws Exception {
        String sql = "select * from students";
        List<Student> list = this.queryForList(sql);
        if (page * 8 > list.size()) {
            return list.subList((page - 1) * 8, list.size());
        }
        return list.subList((page - 1) * 8, page * 8);
    }

    @Override
    public void alterStudent(Student student) throws Exception {
        String sql = "update students set studentName=? , gender = ? , facultyNum = ? , birth = ? where studentNum = ?";
        this.executeUpdate(
                sql,
                student.getStudentName(),
                student.getGender(),
                student.getFacultyNum(),
                student.getBirth(),
                student.getStudentNum()
        );
    }

    @Override
    public List<Student> getCourseStudents(int courseNum) throws Exception {
        String sql = "select * from students where studentNum in (Select studentNum from selectcourse where courseNum =?)";
        return this.queryForList(sql,courseNum);
    }

    @Override
    public Student getStudentByUsername(String username) throws Exception{
        Student student = null;
        String sql = "select * from students where userNum = (select userNum from users where username = ?)";
        List<Student> list = this.queryForList(sql, username);
        if (list.size() != 0) {
            student = list.get(0);
        }
        return student;
    }
    @Override
    public List<Student> getSelectedStudent(int courseNum)throws Exception{
//        String sql = "select * from students where studentNum in (Select studentNum from selectcourse where courseNum = ?) limit ?,?";
//        return this.queryForList(sql, courseNum,(page - 1) * 8, 8);
        String sql = " select students.studentNum,studentName,gender,facultyNum,score from students,selectcourse where students.studentNum=selectcourse.studentNum and courseNum =?";
        return this.queryForList(sql, courseNum);
    }
    @Override
    public int getSelectedStudentCount(int courseNum)throws Exception{
        String sql = "select count(*) from students where studentNum in (Select studentNum from selectcourse where courseNum = ?)";
        return Integer.parseInt(this.queryForValue(sql, courseNum).toString());
    }

}
