package ru.ezhov.groveltengine.engine;

import java.util.logging.Logger;

/**
 *
 * @author ezhov_da
 */
public class XmlObject
{
    private static final Logger LOG = Logger.getLogger(XmlObject.class.getName());
    private String groovy;
    private String velocity;

    public XmlObject()
    {
    }


    public XmlObject(String groovy, String velocity)
    {
        this.groovy = groovy;
        this.velocity = velocity;
    }

    public String getGroovy()
    {
        return groovy;
    }

    public void setGroovy(String groovy)
    {
        this.groovy = groovy;
    }

    public String getVelocity()
    {
        return velocity;
    }

    public void setVelocity(String velocity)
    {
        this.velocity = velocity;
    }


}
