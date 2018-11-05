
import org.lwjgl.glfw.*;

public class MainWubWub {

	public static void main(String[] args) {
		Window window = new Window(800, 400, 60, "Wub Wub Window");
		window.create();

		while (!window.closed()) {
			if (window.isUpdating()) {

				window.update();
				if (window.keyPressed(GLFW.GLFW_KEY_A))
					System.out.println("A");

				window.swapBuffers();
			}
		}
	}

}
