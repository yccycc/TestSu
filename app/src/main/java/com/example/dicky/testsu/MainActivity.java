package com.example.dicky.testsu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Log.i("goddess", runCommand1(new String[]{"ls"})+"----");

            Log.i("goddess", "over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String runCommand(String[] cmd) throws IOException {
        byte buff[] = new byte[1024 * 1024];
        byte tmp[] = new byte[512];
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream inputStream = process.getInputStream();
        int count = 0;
        while (true) {
            Log.i("goddess-count", "@@@@");
            int i;
            if (count == buff.length) {
                i = inputStream.read(tmp);
            } else {
                i = inputStream.read(buff, count, buff.length - count);
            }
            if (i == -1) {
                Log.i("goddess-count", count+"");
                return new String(buff);
            } else {
                count += i;
            }
        }
    }
    public String runCommand1(String[] cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream inputStream = process.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        inputStream.close();
        Log.i("goddess--", data.length+"");
        return new String(data);
    }
}
