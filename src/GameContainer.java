

public class GameContainer implements Runnable {

	private Thread thread;

	private boolean running = false; // is the game loop running?
	private final double UPDATE_CAP = 1.0 / 60.0; // update up to 60 times a seconds

	public GameContainer() {

	}

	public void start() {
		thread = new Thread(this);
		thread.run();
	}

	public void stop() {

	}

	public void run() {

		running = true;

		boolean render = false;

		// timing our loop
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0; // initial the lastTime
		double passedTime = 0;
		double unprocessedTime = 0;

		// main game loop
		while (running) {
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			while (unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true; // only render when its time to update
				System.out.println("Update");
				
				// TODO - update game
			}

			if (render) {
				// TODO render game
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		dispose();  // when game loop ends, run the dispose code
	}

	public void dispose() {
		
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer();
		gc.start();
	}
		
}
