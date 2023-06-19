package logging;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.util.logging.*;
import java.io.*;

public class LoggingImageViewer {

    public static void main(String[] args) {

        if (System.getProperty("java.util.logging.config.class") == null &&
        System.getProperty("java.util.logging.config.file") == null){
            try {
                Logger.getLogger("logging").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                var handler = new FileHandler("%h/Desktop/JAVA/logs/LoggingTest%u", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("logging").addHandler(handler);
            } catch (IOException e){
                Logger.getLogger("logging").log(Level.FINE, "failed to create logger");
            }
        }

        EventQueue.invokeLater(() -> {
            var windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("logging").addHandler(windowHandler);

            var frame = new ImageViewerFrame();
            frame.setTitle("image viewer");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            Logger.getLogger("logging").log(Level.FINE, "showing frame");
        });
    }
}

class ImageViewerFrame
        extends JFrame{

    private static Logger logger = Logger.getLogger("logging");
    private final int DEFAULT_WIDTH = 300;
    private final int DEFAULT_HEIGHT = 300;

    private JLabel label;

    public ImageViewerFrame(){
        logger.entering("logging.ImageViewerFrame", "<init>");

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        var fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        var openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        var exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        openItem.addActionListener(new OpenFileListener());
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("exiting..");
                System.exit(0);
            }
        });

        label = new JLabel();
        logger.exiting("logging.ImageViewerFrame", "<init>");
    }

    private class OpenFileListener
            implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            logger.entering("logging.ImageViewerFrame.OpenFileListener", "actionPerformed", event);
            var chooser = new JFileChooser();

            chooser.setCurrentDirectory(new File("."));
            chooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "GIF Images";
                }
            });

            int resp = chooser.showOpenDialog(ImageViewerFrame.this);
            if (resp == JFileChooser.APPROVE_OPTION){
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            }
            else logger.log(Level.FINE, "file chooser canceled");
            logger.exiting("logging.ImageViewerFrame.OpenFileListener", "actionPerformed", event);
        }
    }
}

class WindowHandler extends StreamHandler{

    private JFrame frame;

    public WindowHandler(){

        frame = new JFrame();
        var output = new JTextArea();
        output.setEditable(false);
        frame.setSize(300, 300);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);

        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                output.append(new String(b, off, len));
            }
        });
    }

    @Override
    public void publish(LogRecord record){

        if (!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}
