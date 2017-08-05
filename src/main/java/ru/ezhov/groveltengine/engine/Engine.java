package ru.ezhov.groveltengine.engine;

/**
 *
 * @author ezhov_da
 */
public interface Engine<T, V>
{
    T process(V source);
}
