package ru.ezhov.groveltengine.engine;

import java.io.IOException;

/**
 *
 * @author ezhov_da
 */
public interface ResourceHolder<T>
{
    T resource() throws IOException;
}
