package br.com.vdelgado.nikepromo;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class ExempleInstrumentJavaTest {
    @Test
    public void useAppContext(){
        assertEquals("br.com.vdelgado.nikepromo", InstrumentationRegistry.getTargetContext().getPackageName());
    }
}