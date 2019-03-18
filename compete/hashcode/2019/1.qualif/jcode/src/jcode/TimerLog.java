package jcode;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Helper class to compute timing and provide informations
 * @author psuzzi
 *
 */
public class TimerLog {
	static TimerLog instance;

	static void start() {
		instance = new TimerLog().schedule(0, 1000);
	}

	static void info(Solver sol, String info) {
		instance.solver = sol;
		instance.info = info;
	}

	static void message(String format, Object... args) {
		instance.message = String.format(format, args);
	}

	static void end() {
		instance.cancel();
	}

	private Timer timer;
	private Solver solver;
	private String info;
	private TimerTask task;
	private String message;
	private long start;

	public TimerLog() {
		start = System.currentTimeMillis();
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				if (message != null) {
					System.out.print(message);
				}
			}
		};
	}

	public TimerLog schedule(long delay, long period) {
		timer.schedule(task, delay, period);
		return this;
	}

	public void cancel() {
		task.cancel();
		if (info != null) {
			System.out.printf("| %-20s", info);
		}
		System.out.printf(" t: %,8d ms ", System.currentTimeMillis() - start);
		if (solver != null) {
			System.out.printf("-> %,7d |", solver.out.score());
		}
	}
}
