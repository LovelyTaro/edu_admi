package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dcy
 * 第一次尝试使用反射
 * Dao模式尝试，将jdbc中所有和数据库有关的操作进行封装
 * -----用一个抽象的BaseDao实现通用的增删改查
 * -----对每个实体类创建一个dao的接口，通过实现这个接口，实现对这个实体类对应的表的查询操作
 * -----同时简化对数据库的操作，在每次进行数据库操作的时候不需要管数据库连接/关闭连接之类的操作，只需要传入数据和sql就能执行
 */

public abstract class BaseDao<T> {

    //用来接受泛型类型
    private Class<T> type;

    //在构造方法里通过反射获得具体的类型
    public BaseDao() {
        //获取子类类的型
        Class clazz = this.getClass();
        //获取父类的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        //使用getActualTypeArguments获取具体的泛型类型
        Type[] types = parameterizedType.getActualTypeArguments();
        //获取具体的泛型类型
        this.type = (Class<T>) types[0];
    }

    //Object ...args表示动态可变参数实际上和Object[]差不多
    //这是一个通用的增删改的方法，exeUpdate不能运行查询语句，返回值为
    public int executeUpdate(String sql, Object... args) throws Exception {
        //获得连接并预编译sql
        Connection connection = DBUtil.getDBUtil().connectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //用for循环将参数绑定到sql中
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        //executeUpdate会返回该语句影响的行数
        int rows = preparedStatement.executeUpdate();
        DBUtil.getDBUtil().closeConnect(connection, preparedStatement);
        return rows;
    }

    //获取List的查询
    public List<T> queryForList(String sql, Object... args) throws Exception {
        //创建用于存放返回值的list
        List<T> list = new ArrayList<>();
        //获得数据库连接
        Connection connection = DBUtil.getDBUtil().connectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //使用for循环绑定参数
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()) {
            // 通过反射创建对象
            T bean = type.newInstance();
            /*
             通过resultSetMetaData获取数据库返回的每一列的名字，通过for循环将它写入创建的对象对象里
             默认跳过第一列，因为第一列是no，一般是自增的，不需要查询，也不需要添加
             */
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                String columnName = resultSetMetaData.getColumnName(i + 1);
                Object value = resultSet.getObject(columnName);

                // TODO 反射直接获取类属性，赋值
                // 通过通过数据库查询得出的columnName去获取这个属性（columnName和属性名相同）
                Field field = type.getDeclaredField(columnName);
                //因为实体类中属性都是private，所以必须设置setAccessible
                field.setAccessible(true);
                field.set(bean, value);
                //TODO 这个是通过反射获取当前属性的set方法，调用set方法进行赋值
//                //用stringBuilder创建set方法的名字（“set”+column的首字母大写---驼峰式的命名方式）
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("set");
//                 //获取column的首字母并大写
//                stringBuilder.append(columnName.substring(0,1).toUpperCase(Locale.ROOT));
//                // 获取column后面的字串，添加到string中
//                stringBuilder.append(columnName.substring(1));
//                Method method =type.getDeclaredMethod(stringBuilder.toString(),field.getType());
//                if (String.class.equals(field.getType())) {
//                    String value = resultSet.getString(columnName);
//                    method.invoke(bean, value);
//                } else if (int.class.equals(field.getType())) {
//                    int value2 = resultSet.getInt(columnName);
//                    method.invoke(bean, value2);
//                } else {
//                    throw new IllegalStateException("Unexpected value: " + field.getType());
//                }
//
            }
            list.add(bean);
        }//while
        return list;
    }


    public Object queryForValue(String sql,Object ...args) throws Exception{
        Object result = null;
        //连接数据库，执行sql
        Connection connection = DBUtil.getDBUtil().connectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //绑定参数
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        //执行sql获取数据
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            result = resultSet.getObject(1);
        }
        return result;
    }

}
