package com.journal.app;

import com.journal.app.fragments.JNUserDiaryHistory;

import java.util.Queue;

public interface JNAsyncListener {
    void onTaskCompleted(Queue<? extends JNUserDiaryHistory.User> data);


}
