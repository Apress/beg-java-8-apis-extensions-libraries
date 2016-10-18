// SwingWorkerProcessor.java
package com.jdojo.swing;

import javax.swing.SwingWorker;
import java.util.List;

public class SwingWorkerProcessor extends SwingWorker<Integer, Integer> {
	private final SwingWorkerFrame frame;
	private int iteration;
	private int intervalInMillis;

	public SwingWorkerProcessor(SwingWorkerFrame frame, int iteration,
		int intervalInMillis) {
		this.frame = frame;
		this.iteration = iteration;

		if (this.iteration <= 0) {
			this.iteration = 10;
		}

		this.intervalInMillis = intervalInMillis;

		if (this.intervalInMillis <= 0) {
			this.intervalInMillis = 1000;
		}
	}

	@Override
	protected Integer doInBackground() throws Exception {
		int sum = 0;
		for (int counter = 1; counter <= iteration; counter++) {
			sum = sum + counter;

			// Publish the result to the GUI 
			this.publish(counter);

			// Make sure it listens to an interruption and exits this 
			// method by throwing an appropriate exception 
			if (Thread.interrupted()) {
				throw new InterruptedException();
			}

			// Make sure the loop exits, when the task is cancelled 
			if (this.isCancelled()) {
				break;
			}

			Thread.sleep(intervalInMillis);
		}

		return sum;
	}

	@Override
	protected void process(List<Integer> data) {
		for (int counter : data) {
			frame.updateStatus(counter, iteration);
		}
	}

	@Override
	public void done() {
		frame.doneProcessing();
	}
}
