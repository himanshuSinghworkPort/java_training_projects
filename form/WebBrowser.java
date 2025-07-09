import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WebBrowser extends JFrame {
    public WebBrowser() {
        super("Simple Web Browser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a JEditorPane
        JEditorPane editorPane = new JEditorPane();

        // Enable JavaScript and other necessary settings
        editorPane.setEditable(false);

       
            // Load a URL
           // editorPane.setPage("file:///C:/Users/admin/Desktop/java_swing_gui_form_embedd/form/index.html");
       
            editorPane.setContentType("text/html");
            editorPane.setText(" <form action="submit.php" method="POST">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br/>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br/>
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>
    </form>");
        }

        // Add editorPane to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(editorPane);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WebBrowser browser = new WebBrowser();
            browser.setVisible(true);
        });
    }
}
