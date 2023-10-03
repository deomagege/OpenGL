import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class Rectangles {
    private long window;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        window = GLFW.glfwCreateWindow(1000, 500, "OpenGL Rectangles", MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(window);

        GL.createCapabilities();

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    private void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            drawRectangles();

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    private void drawRectangles() {
        // Blue Rectangle
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(-0.5f, 1.0f);
        GL11.glVertex2f(-0.5f, -1.0f);
        GL11.glVertex2f(0.0f, -1.0f);
        GL11.glVertex2f(0.0f, 1.0f);
        GL11.glEnd();

        // Red Rectangle
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex2f(0.0f, 1.0f);
        GL11.glVertex2f(0.0f, -1.0f);
        GL11.glVertex2f(0.5f, -1.0f);
        GL11.glVertex2f(0.5f, 1.0f);
        GL11.glEnd();
    }

    private void cleanup() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public static void main(String[] args) {
        new Rectangles().run();
    }
}
