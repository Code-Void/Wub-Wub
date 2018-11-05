import org.lwjgl.glfw.*;

public class Window {
	private int width, height;
	private String title;
	private long window;
	private double fps_cap, time, unprocessedTime;

	public Window(int width, int height, int fps,  String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.fps_cap = fps;
		this.unprocessedTime = 0;

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getFps_cap() {
		return fps_cap;
	}

	public double getUnprocessedTime() {
		return unprocessedTime;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void create() {
		if (!GLFW.glfwInit()) {
			System.err.println("ERROR: Initializing GLFW");
			System.exit(-1);
		}

		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0) {
			System.err.println("Error: window not created");
			System.exit(-1);
		}

		GLFWVidMode videomode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videomode.width() - width) / 2, (videomode.height() - height) / 2);

		GLFW.glfwShowWindow(window);
		
		time = getTime();
	}

	public boolean keyPressed(int keyCode) {
		return GLFW.glfwGetKey(window, keyCode) == 1;
	}

	public boolean closed() {
		return GLFW.glfwWindowShouldClose(window);
	}

	public void update() {
		GLFW.glfwPollEvents();
	}

	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	public double getTime() {
		return (double) System.nanoTime() / (double) 1000000000;
	}
	
	public boolean isUpdating() {
		double nextTime = getTime();
		double passedTime = nextTime = time;
		
		unprocessedTime += passedTime;
		time = nextTime;
		
		while(unprocessedTime > 1.0/fps_cap) {
			unprocessedTime -= 1.0/fps_cap;
			return true;
		}
		return false;
	}
}
