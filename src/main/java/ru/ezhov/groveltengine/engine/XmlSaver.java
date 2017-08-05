package ru.ezhov.groveltengine.engine;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;

/**
 *
 * @author ezhov_da
 */
public class XmlSaver implements Saver<File, String, String>
{
    private static final Logger LOG = Logger.getLogger(XmlSaver.class.getName());

    private final File file;

    public XmlSaver(File file)
    {
        this.file = file;
    }

    @Override
    public File save(String source1, String source2) throws IOException
    {
        if (file != null)
        {
            XmlObject xmlSaveCode = new XmlObject(source1, source2);
            JAXB.marshal(xmlSaveCode, file);
        }
        return file;

    }
}
