# 基于JavaEE的教务管理系统-----期末大大作业 #

## 项目要求： ##
1. 根据课程所学的javaee设计并实现一个小型的web项目。
2. 项目必须包含：JSP、Servlet、Session、过滤器和jdbc技术
3. 必须有创建数据库的SQL脚本
4. 后端不允许使用任何框架。
5. 网页禁止粗制滥造（会扣分）！

## 项目分工： ##
+ 组长：董辰钰
+ 后端开发：董辰钰
+ 前端开发：董辰钰、范洳铖、段崇文、耿延、范洳铖

## 项目使用技术 ##
+ 前端：BootStrap
+ 后端：JavaEE
+ 数据库：MySql8
+ 服务器：Tomcat 8.0.27


## 功能 ##
### 用户登录 ###
用户未登录只能看到登录界面无法进入\
用户可以主动登出\
用户有三种角色：学生,老师,管理员\
根据用户的角色不同分别进入

### 学生功能 ###
+ #### 查看个人信息 ####
学号、姓名、性别、院系、生日\
账号、用户名\
可以对用户名和密码进行修改
+ #### 选课 ####
可以查看管理员发布的所有课程信息\
并且可以对自己喜欢的课程进行选择，选课之后可以在课表里查看
+ #### 课表 ####
可以查看所有已选择的课程\
并且可以看到老师对课程的评分

### 教师功能 ###
+ #### 课程查看 ####
查看自己教的所有课程\
可以点击打分按钮，对选择这门课的学生进行打分

### 管理员功能 ###
+ #### 课程管理 ####
可以查看所有发布的课程，并对课程进行修改或者时删除操作
+ #### 学生管理 ####
可以查看所有录入的学生信息，对所有学生的信息进行修改，同时可以增加学生
但是不可以删除
+ #### 教师管理 ####
可以查看所有已录入的教师信息，对所有教师信息进行修改，同时可以增加教师
但是不可以删除

### 内容详情 ###
+ #### 数据库设计 ####
1. 共有5个表分别为：users,students,teachers,courses,selectcourse\
2. users：存有userNum,username,password 用来存储登录使用的账号\
3. students：存有studentNum,studentName,userNum,gender,facultyNum,birth 用来存储学生信息，通过外键userNum与users表相联系\
4. teachers：存有teacherNum,teacherName,userNum,gender,facultyNum,birth 用来存储教师信息，通过外键userNum与users表相联系\
5. courses：存有courseNum,courseName,teacherNum,courseTime,coursePosition,weeks,facultyNum,credit 用来存储所有课程信息\
6. selectcourse：存有courseNum,studentNum 用来表示选课信息