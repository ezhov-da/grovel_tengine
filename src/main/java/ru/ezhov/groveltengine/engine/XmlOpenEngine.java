package ru.ezhov.groveltengine.engine;

import java.io.File;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;

/**
 *
 * @author ezhov_da
 */
public class XmlOpenEngine implements Engine<XmlObject, File>
{
    private static final Logger LOG = Logger.getLogger(XmlOpenEngine.class.getName());

    @Override
    public XmlObject process(File source)
    {
        XmlObject xmlObject = null;
        if (source != null)
        {
            xmlObject = JAXB.unmarshal(source, XmlObject.class);
        }
        return xmlObject;
    }
}
