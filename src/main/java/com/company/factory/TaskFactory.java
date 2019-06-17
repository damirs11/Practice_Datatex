package com.company.factory;

import com.company.models.documents.Task;

class TaskFactory {
    static Task create() {
        return new Task();
    }
}
