# 基于JavaEE的教务管理系统-----期末大大作业 #

## 项目要求： ##
1. 根据课程所学的javaee设计并实现一个小型的web项目。
2. 项目必须包含：JSP、Servlet、Session、过滤器和jdbc技术
3. 必须有创建数据库的SQL脚本
4. 后端不允许使用任何框架。
5. 网页禁止粗制滥造（会扣分）！

## 项目分工： ##
+ 组长：dcy
+ 后端开发：dcy、frc
+ 前端开发：dcw、gy

## 项目使用技术 ##
+ 前端：BootStrap
+ 后端：实验要求的javaee技术
+ 数据库：MySql8
+ 服务器：Tomcat 8.0.27

## 功能 ##
### 用户登录 ###
用户未登录只能看到登录界面无法进入\
用户可以主动登出\
用户有三种角色：学生,老师,管理员\
根据用户的角色不同分别进入 home_stu,home_tea,home_adm


### 学生功能 ###
+ #### 查看个人信息 ####
+ #### 查看课表 ####
+ #### 选课 ####
+ #### 成绩查询 ####

### 教师功能 ###
+ #### 课程查看 ####
查看自己教的所有课程
+ #### 班级管理 ####
查看每个课程的学生信息
+ #### 成绩录入 ####
给每个课程每个学生进行成绩的录入

### 管理员功能 ###
+ #### 查看并修改所有用户的信息 ####
