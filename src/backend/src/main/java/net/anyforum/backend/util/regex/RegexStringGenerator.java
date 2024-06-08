package net.anyforum.backend.util.regex;

import net.anyforum.backend.constants.AuthConstants;
import org.cornutum.regexpgen.RandomGen;
import org.cornutum.regexpgen.RegExpGen;
import org.cornutum.regexpgen.js.Provider;
import org.cornutum.regexpgen.random.RandomBoundsGen;

public class RegexStringGenerator {
    public static String generateString(String regex) {
        RandomGen random = new RandomBoundsGen();
        RegExpGen generator = Provider.forEcmaScript().matching(regex);

        return generator.generate(random);
    }
}
