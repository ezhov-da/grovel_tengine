package ru.ezhov.groveltengine.engine;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

/**
 *
 * @author ezhov_da
 */
public class TemplateGroovyFileResourceHolder implements ResourceHolder<String>
{
    private static final Logger LOG = Logger.getLogger(TemplateGroovyFileResourceHolder.class.getName());

    @Override
    public String resource() throws IOException
    {
        URL url = Resources.getResource("GroovyTemplate.groovy");
        return Resources.toString(url, Charsets.UTF_8);
    }
}
