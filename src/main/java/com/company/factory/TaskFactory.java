package com.company.factory;

import com.company.models.documents.Task;

public class TaskFactory extends DocumentFactory {

    public Task create() {
        return new Task();
    }
}
