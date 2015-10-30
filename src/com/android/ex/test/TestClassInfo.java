/*
 * 文  件  名：TestClassInfo.java
 * 描        述：测试信息
 */
package com.android.ex.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 要测试的类的信息
 */
public class TestClassInfo
{
    /**
     * 要测试类的类名
     */
    private String className;

    /**
     * 要测试类的方法列表
     */
    private List<String> methodLst = new ArrayList<String>();

    /**
     * 取得className
     * 
     * @return 返回className。
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * 设置className
     * 
     * @param className 要设置的className。
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     * 取得methodLst
     * 
     * @return 返回methodLst。
     */
    public List<String> getMethodLst()
    {
        return methodLst;
    }

    /**
     * 设置methodLst
     * 
     * @param methodLst 要设置的methodLst。
     */
    public void setMethodLst(List<String> methodLst)
    {
        this.methodLst = methodLst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[className:" + className + "]");
        if (!methodLst.isEmpty())
        {
            sb.append("==>methods[");
            for (String method : methodLst)
            {
                sb.append(method).append("|");
            }
            sb.setLength(sb.length() - 1);
            sb.append("]");
        }
        return sb.toString();
    }
}
