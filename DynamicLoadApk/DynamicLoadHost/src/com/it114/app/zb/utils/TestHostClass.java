package com.it114.app.zb.utils;

import android.content.Context;
import android.widget.Toast;

public class TestHostClass {
    
    public void testMethod(Context context) {
        Toast.makeText(context, "Successed invoke host method", Toast.LENGTH_SHORT).show();
    }
}
