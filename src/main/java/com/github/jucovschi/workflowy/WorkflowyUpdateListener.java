package com.github.jucovschi.workflowy;

import com.github.jucovschi.model.delta.response.OpResult;
import com.github.jucovschi.model.snapshot.TaskTree;

public interface WorkflowyUpdateListener {

	void run(TaskTree tree, OpResult result);
	
}
