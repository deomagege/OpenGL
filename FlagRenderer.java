import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class FlagRenderer {
    private long window;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // Create the window
        window = glfwCreateWindow(800, 600, "Flag Renderer", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Set key callback
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert vidMode != null;
        glfwSetWindowPos(
                window,
                (vidMode.width() - 800) / 2,
                (vidMode.height() - 600) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // Initialize OpenGL
        GL.createCapabilities();

        // Set the clear color (France flag colors)
        glClearColor(0.0f, 0.0f, 1.0f, 0.0f);
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Draw the flag (3 vertical stripes)
            glBegin(GL_QUADS);

            // Blue stripe
            glColor3f(0.0f, 0.0f, 1.0f);
            glVertex2f(-1.0f, 1.0f);
            glVertex2f(-1.0f, -1.0f);
            glVertex2f(-0.33f, -1.0f);
            glVertex2f(-0.33f, 1.0f);

            // White stripe
            glColor3f(1.0f, 1.0f, 1.0f);
            glVertex2f(-0.33f, 1.0f);
            glVertex2f(-0.33f, -1.0f);
            glVertex2f(0.33f, -1.0f);
            glVertex2f(0.33f, 1.0f);

            // Red stripe
            glColor3f(1.0f, 0.0f, 0.0f);
            glVertex2f(0.33f, 1.0f);
            glVertex2f(0.33f, -1.0f);
            glVertex2f(1.0f, -1.0f);
            glVertex2f(1.0f, 1.0f);

            glEnd();

            // Draw the country name (France)
            glColor3f(1.0f, 1.0f, 1.0f);
            renderText();

            glfwSwapBuffers(window);

            // Poll for and process events
            glfwPollEvents();
        }
    }

    private void cleanup() {
        // Release window and window callbacks
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void renderText() {

        // You can implement text rendering here using OpenGL (e.g., textured quads for each character)
        // This is a complex task and requires a separate library or implementation.
    }

    public static void main(String[] args) {
        new FlagRenderer().run();
    }
}
