package ru.ezhov.groveltengine.engine;

import groovy.lang.GroovyShell;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ezhov_da
 */
public class CodeEngine implements ICodeEngine<String, String, String>
{
    private static final Logger LOG = Logger.getLogger(CodeEngine.class.getName());

    @Override
    public String process(String textGroovy, String templateVelocity)
    {
        String resultText;
        try
        {
            File file = File.createTempFile("grovel", ".vm");
            LOG.log(Level.INFO, "createed velocity template: {0}", file.getAbsolutePath());
            FileUtils.write(file, templateVelocity, Charset.forName("UTF-8"));
            String nameFile = file.getName();
            String groovyCode =
                    textGroovy
                    .replace(
                            "___PATH_TO_VELOCITY_TEMPLATE___",
                            nameFile);
            GroovyShell groovyShell = new GroovyShell();
            resultText = (String) groovyShell.evaluate(groovyCode);
        } catch (Exception ex)
        {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            ex.printStackTrace(printWriter);
            resultText = stringWriter.toString();
        }
        return resultText;
    }
}
