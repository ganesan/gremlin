package com.tinkerpop.gremlin.functions.g.number;

import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.functions.AbstractFunction;
import com.tinkerpop.gremlin.compiler.operations.Operation;
import com.tinkerpop.gremlin.compiler.types.Atom;

import java.util.List;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class FloorFunction extends AbstractFunction<Long> {

    public static final String FUNCTION_NAME = "floor";

    public Atom<Long> compute(final List<Operation> arguments, final GremlinScriptContext context) throws RuntimeException {

        if (arguments.size() == 1) {
            return new Atom<Long>(((Double)Math.floor(((Number) arguments.get(0).compute().getValue()).doubleValue())).longValue());
        } else {
            throw new RuntimeException(this.createUnsupportedArgumentMessage());
        }
    }

    public String getFunctionName() {
        return FUNCTION_NAME;
    }
}