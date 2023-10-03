import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Shapes {
    public static void main(String[] args) {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create a windowed mode window and its OpenGL context
        long window = GLFW.glfwCreateWindow(800, 600, "OpenGL Shapes", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Unable to create GLFW window");
        }

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // Set up OpenGL settings
        GL11.glOrtho(0, 800, 600, 0, 1, -1);

        // Main loop
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            drawCircle();
            drawRectangle();

            int[] xPoints = {400, 450, 500, 450};
            int[] yPoints = {400, 350, 400, 450};
            drawPolygon(xPoints, yPoints);

            GLFW.glfwSwapBuffers(window);

            GLFW.glfwPollEvents();
        }

        GLFW.glfwTerminate();
    }

    private static void drawCircle() {
        int numSegments = 100;
        double theta = 2 * Math.PI / numSegments;
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (int i = 0; i < numSegments; i++) {
            double angle = i * theta;
            double dx = 100 + 50 * Math.cos(angle);
            double dy = 100 + 50 * Math.sin(angle);
            GL11.glVertex2d(dx, dy);
        }
        GL11.glEnd();
    }

    private static void drawRectangle() {
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex2d(200, 200);
        GL11.glVertex2d(200 + 100, 200);
        GL11.glVertex2d(200 + 100, 200 + 50);
        GL11.glVertex2d(200, 200 + 50);
        GL11.glEnd();
    }

    private static void drawPolygon(int[] xPoints, int[] yPoints) {
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (int i = 0; i < xPoints.length; i++) {
            GL11.glVertex2d(xPoints[i], yPoints[i]);
        }
        GL11.glEnd();
    }
}


