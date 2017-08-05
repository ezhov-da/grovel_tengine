package ru.ezhov.groveltengine;

import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import ru.ezhov.groveltengine.engine.CodeEngine;
import ru.ezhov.groveltengine.engine.Engine;
import ru.ezhov.groveltengine.engine.GroovyEngine;
import ru.ezhov.groveltengine.engine.ICodeEngine;
import ru.ezhov.groveltengine.view.FrameEngine;

/**
 *
 * @author ezhov_da
 */
public class App
{
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable ex)
            {
                //
            }
            Engine<String, JTextComponent> engineGroovy = new GroovyEngine();
            Engine<String, JTextComponent> engineVelocity = new GroovyEngine();
            ICodeEngine codeEngine = new CodeEngine();
            JFrame frame = new FrameEngine(engineGroovy, engineVelocity, codeEngine);
            frame.setTitle("Template engine");
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }


}
