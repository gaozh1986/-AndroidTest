/*
 * 文  件  名：Receiver2Runner.java
 * 描        述：通过接收外部广播，执行Junit具体测试
 */
package com.android.ex.test;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 通过接收外部广播，执行Junit具体测试
 */
public class Receiver2Runner
{
    /**
     * TAG
     */
    private static final String TAG = "Receiver2Runner";

    /**
     * 单实例
     */
    private static Receiver2Runner instance = null;

    /**
     * 命令key值
     */
    private static final String CMDKEY = "cmd";

    /**
     * 广播名称
     */
    private static final String BROAD_NAME = "com.android.ex.test";

    /**
     * 广播接收
     */
    private BroadcastReceiver receiver = new BroadcastReceiver()
    {
        /**
         * {@inheritDoc}
         */
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String cmdStr = intent.getStringExtra(CMDKEY);
            runTest(cmdStr);
        }
    };

    /**
     * 构造函数
     * 
     * @param context 上下文对象
     */
    private Receiver2Runner(Context context)
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROAD_NAME);
        context.registerReceiver(receiver, filter);
    }

    /**
     * 
     * 获取单实例
     * 
     * @param context 上下文对象
     * @return 单实例
     */
    public static Receiver2Runner getInstance(Context context)
    {
        synchronized (Receiver2Runner.class)
        {
            if (instance == null)
            {
                instance = new Receiver2Runner(context);
            }
            return instance;
        }
    }

    /**
     * 通过单独线程跑测
     * 
     * @param cmdStr 命令关键字
     */
    private void runTest(final String cmdStr)
    {
        Runnable runnable = new Runnable()
        {
            /**
             * {@inheritDoc}
             */
            @Override
            public void run()
            {
                try
                {
                    Utils.info(TAG, "************** START TEST **************");
                    Utils.info(TAG, "org cmdStr:" + cmdStr);
                    JUnitCore core = new JUnitCore();
                    // 1.命令关键字转换为测试对象
                    List<TestClassInfo> testClassInfoLst = Utils.getTestClassLst(cmdStr);
                    for (TestClassInfo testClass : testClassInfoLst)
                    {
                        Utils.info(TAG, testClass.toString());
                        if (testClass.getMethodLst().isEmpty())
                        {
                            // 没有方法的情况下测试整个类
                            JUnitCore.runClasses(Class.forName(testClass.getClassName()));
                            continue;
                        }
                        else
                        {
                            // 有方法的情况下，单独测试
                            for (String methodName : testClass.getMethodLst())
                            {
                                core.run(Request.method(Class.forName(testClass.getClassName()), methodName));
                            }
                        }
                    }
                    Utils.info(TAG, "************** END TEST **************");
                }
                catch (Exception e)
                {
                    Utils.error(TAG, e.toString());
                }
            }
        };
        Thread runThread = new Thread(runnable, TAG + "-thread");
        runThread.start();
    }
}
