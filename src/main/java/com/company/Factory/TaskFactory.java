package com.company.factory;

import com.company.models.documents.Task;

class TaskFactory {

    private TaskFactory() {
        throw new IllegalStateException();
    }

    static Task create() {
        return new Task();
    }
}
