package net.zn80.engine;


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

	/**
	 * run method with the main running loop
	 */
	public void run() {

		running = true;

		boolean render = false;

		// timing our loop
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0; // initial the lastTime
		double passedTime = 0;
		double unprocessedTime = 0;
		
		// counting frames and fps
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		

		// main game loop
		while (running) {
			render = false;  // reset the render flag to render only every 60 frames
			
			// setting startpoint for the rendering 
			firstTime = System.nanoTime() / 1000000000.0;  // making milliseconds, but much more precise
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			// calculating time - keeping track of time
			unprocessedTime += passedTime;
			frameTime += passedTime;  // keep track of the time on how much has been passed
			
			while (unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true; // only render when its time to update
			
				
				// TODO - update game
				if(frameTime >= 1.0) { // this is one second
					frameTime = 0;  // reset frametime
					fps = frames;
					frames = 0;
					System.out.println("FPS: " + fps);
				}
			}

			if (render) {
				// TODO render game
				frames++;  // increment the frames counter 
				
				
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
