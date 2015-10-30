/*
 * 文  件  名：Utils.java
 * 描        述：辅助工具类
 */
package com.android.ex.test;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;
import android.util.Log;

/**
 * 辅助工具类
 */
public final class Utils
{
    /**
     * TAG
     */
    private static final String TAG = "TST_FRM";

    /**
     * 类信息分隔符
     */
    private static final String CLASS_SPLIT = ",";

    /**
     * 类信息中的字段分隔符
     */
    private static final String FIELD_SPLIT = "@";

    /**
     * 解析输入参数
     * 
     * @param cmdStr 需要测试的命令参数
     * @return 命令对象
     */
    public static List<TestClassInfo> getTestClassLst(String cmdStr)
    {
        List<TestClassInfo> result = new ArrayList<TestClassInfo>();
        if (!TextUtils.isEmpty(cmdStr))
        {
            // 类信息拆分
            String[] classInfoArray = cmdStr.split(CLASS_SPLIT);
            if (classInfoArray != null && classInfoArray.length > 0)
            {
                for (String classInfoStr : classInfoArray)
                {
                    if (!TextUtils.isEmpty(classInfoStr))
                    {
                        // 单个类信息的解析保存
                        result.add(parseClassInfo2Obj(classInfoStr));
                    }
                }
            }
        }
        return result;
    }

    /**
     * 日志打印
     * 
     * @param tag 标签
     * @param msg 消息
     */
    public static void info(String tag, String msg)
    {
        Log.i(TAG, tag + "[" + msg + "]");
    }

    /**
     * 日志打印
     * 
     * @param tag 标签
     * @param msg 消息
     */
    public static void error(String tag, String msg)
    {
        Log.e(TAG, tag + "[" + msg + "]");
    }

    /**
     * 解析Str类型的类信息到对象类型的对象
     * 
     * @param classInfoStr Str类型的类信息
     * @return 对象类型的对象
     */
    private static TestClassInfo parseClassInfo2Obj(String classInfoStr)
    {
        TestClassInfo result = new TestClassInfo();
        if (classInfoStr.contains(FIELD_SPLIT))
        {
            // class@method@method的测试场景，测试类中的某些方法
            String[] fields = classInfoStr.split(FIELD_SPLIT);
            for (int i = 0; i < fields.length; i++)
            {
                if (i == 0)
                {
                    // 默认0号位置是类名称
                    result.setClassName(fields[i]);
                }
                else
                {
                    // 后面是各个需要测试的方法名称
                    result.getMethodLst().add(fields[i]);
                }
            }
        }
        else
        {
            // 测试类中的所有测试方法
            result.setClassName(classInfoStr);
        }
        return result;
    }
}
