package com.andreiverdes.training.expleo.utils;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.noties.markwon.Markwon;

public class Lessons {

    public static void displayLesson1(TextView textView) {
        loadLesson(textView, "lesson1.md");
    }
    public static void displayLesson2(TextView textView) {
        loadLesson(textView, "lesson2.md");
    }
    public static void displayLesson3(TextView textView) {
        loadLesson(textView, "lesson3.md");
    }

    private static void loadLesson(TextView textView, String lesson) {
        InputStream inputStream;
        String markdown = "";
        try {
            inputStream = textView.getContext().getAssets().open(lesson);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                total.append(line).append('\n');
            }
            markdown = total.toString();
            inputStream.close();
        } catch (IOException e) {
            Log.e(Lessons.class.getSimpleName(), "Read lesson:", e);
        }
        Markwon markwon = Markwon.create(textView.getContext());
        markwon.setMarkdown(textView, markdown);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

}
