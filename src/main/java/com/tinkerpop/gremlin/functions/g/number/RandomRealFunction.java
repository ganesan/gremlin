package com.tinkerpop.gremlin.functions.g.number;

import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.functions.AbstractFunction;
import com.tinkerpop.gremlin.compiler.operations.Operation;
import com.tinkerpop.gremlin.compiler.types.Atom;

import java.util.List;
import java.util.Random;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class RandomRealFunction extends AbstractFunction<Double> {

    public static final String FUNCTION_NAME = "rand-real";
    private static final Random random = new Random();


    public Atom<Double> compute(final List<Operation> arguments, final GremlinScriptContext context) throws RuntimeException {

        if (arguments.size() == 0) {
            return new Atom<Double>(random.nextDouble());
        }

        throw new RuntimeException(this.createUnsupportedArgumentMessage());
    }

    public String getFunctionName() {
        return FUNCTION_NAME;
    }
}