package com.xxxpackage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.android.ex.test.Utils;

public class XXXTest
{
    private static final String TAG = "XXXTest";

    @Before
    public void begin()
    {
        Utils.info(TAG, TAG + "================方法测试开始==============");
    }

    @After
    public void end()
    {
        Utils.info(TAG, TAG + "================方法测试结束==============");
    }

    @Test
    public void testGafPing()
    {
    }

}