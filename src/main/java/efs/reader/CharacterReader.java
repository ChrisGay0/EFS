package efs.reader;

/**
 * @author mattf - 2013-05-15
 */
public interface CharacterReader {

    /**
     * @return the next character in the stream
     * @throws javatest.exception.EndOfStreamException if there are no more characters
     */
    char getNextChar();
}
