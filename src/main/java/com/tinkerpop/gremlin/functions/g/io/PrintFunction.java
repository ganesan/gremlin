package com.tinkerpop.gremlin.functions.g.io;

import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.functions.AbstractFunction;
import com.tinkerpop.gremlin.compiler.operations.Operation;
import com.tinkerpop.gremlin.compiler.types.Atom;

import java.util.List;

/**
 * @author Pavel A. Yaskevich
 */
public class PrintFunction extends AbstractFunction<Object> {

    private static final String FUNCTION_NAME = "print";


    public Atom<Object> compute(final List<Operation> arguments, final GremlinScriptContext context) throws RuntimeException {

        for (Operation operation : arguments) {
            Atom atom = operation.compute();
            context.writeOutput(atom);
        }
        context.writeOutputLine("");

        return new Atom<Object>(null);
    }

    public String getFunctionName() {
        return FUNCTION_NAME;
    }
}
