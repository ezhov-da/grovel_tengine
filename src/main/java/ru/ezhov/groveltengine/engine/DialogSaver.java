package ru.ezhov.groveltengine.engine;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author ezhov_da
 */
public class DialogSaver
{
    private static final Logger LOG = Logger.getLogger(DialogSaver.class.getName());

    public File save() throws IOException
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setDialogTitle("Save yours templates");
        int i = fileChooser.showSaveDialog(null);
        if (i == JFileChooser.APPROVE_OPTION)
        {
            return fileChooser.getSelectedFile();
        } else
        {
            return null;
        }

    }
}
