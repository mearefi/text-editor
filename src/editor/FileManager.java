package editor;

import javax.swing.*;
import java.io.File;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                textArea.setText(content);
                textEditor.currentFile = file;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(textEditor, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile != null) {
            try {
                java.nio.file.Files.write(textEditor.currentFile.toPath(), textArea.getText().getBytes());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(textEditor, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    java.nio.file.Files.write(file.toPath(), textArea.getText().getBytes());
                    textEditor.currentFile = file;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(textEditor, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
    }

}
