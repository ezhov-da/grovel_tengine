package ru.ezhov.groveltengine.engine;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author ezhov_da
 */
public class DialogOpen
{
    private static final Logger LOG = Logger.getLogger(DialogOpen.class.getName());

    public File open() throws IOException
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setDialogTitle("Open yours templates");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = fileChooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION)
        {
            return fileChooser.getSelectedFile();
        } else
        {
            return null;
        }

    }
}
