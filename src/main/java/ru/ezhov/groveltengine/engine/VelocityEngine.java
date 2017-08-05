package ru.ezhov.groveltengine.engine;

import java.util.logging.Logger;
import javax.swing.text.JTextComponent;

/**
 *
 * @author ezhov_da
 */
public class VelocityEngine implements Engine<String, JTextComponent>
{
    private static final Logger LOG = Logger.getLogger(VelocityEngine.class.getName());

    @Override
    public String process(JTextComponent source)
    {
        return source.getText();
    }
}
