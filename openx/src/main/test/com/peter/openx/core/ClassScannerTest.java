package com.peter.openx.core;

import com.peter.openx.core.impl.DefaultClassScanner;
import com.peter.openx.mock.dao.BaseDao;
import com.peter.openx.mvc.annotation.Controller;
import com.peter.openx.tx.Service;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ClassScannerTest {
    private ClassScanner classScanner = new DefaultClassScanner();

    @Test
    public void getClassSet() {
        Set<Class<?>> classSet = classScanner.getClassSet("com.peter.openx.mock");
    }

    @Test
    public void getClassSetByAnnotation() {
        Set<Class<?>> classSetByAnnotation = classScanner.getClassSetByAnnotation("com.peter.openx.mock", Controller.class);
    }

    @Test
    public void getClassSetBySuper() {
        Set<Class<?>> classSetBySuper = classScanner.getClassSetBySuper("com.peter.openx.mock", BaseDao.class);
    }
}