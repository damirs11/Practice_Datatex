package com.company.Factory;

import com.company.Models.Documents.Task;

public class TaskFactory extends DocumentFactory {

    public Task create() {
        return new Task();
    }
}
