package com.company.factory;

import com.company.models.documents.Task;

/**
 * The Task factory.
 */
class TaskFactory {
    /**
     * Create Task document.
     *
     * @return the Task document
     */
    static Task create() {
        return new Task();
    }
}
