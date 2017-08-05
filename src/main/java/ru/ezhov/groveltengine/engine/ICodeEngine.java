package ru.ezhov.groveltengine.engine;

/**
 *
 * @author ezhov_da
 */
public interface ICodeEngine<T, U, V>
{
    T process(U source1, V source2);
}
