package ru.ezhov.groveltengine.engine;

import java.io.IOException;

/**
 *
 * @author ezhov_da
 */
public interface Saver<T, S, V>
{
    T save(S source1, V source2) throws IOException;
}
