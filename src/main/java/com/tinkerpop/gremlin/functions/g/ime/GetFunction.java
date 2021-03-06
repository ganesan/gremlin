package com.tinkerpop.gremlin.functions.g.ime;

import com.tinkerpop.blueprints.pgm.Element;
import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.functions.AbstractFunction;
import com.tinkerpop.gremlin.functions.FunctionHelper;
import com.tinkerpop.gremlin.compiler.operations.Operation;
import com.tinkerpop.gremlin.compiler.types.Atom;

import java.util.List;
import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class GetFunction extends AbstractFunction<Object> {

    private static final String FUNCTION_NAME = "get";

    public Atom<Object> compute(final List<Operation> arguments, final GremlinScriptContext context) throws RuntimeException {
        if (arguments.size() != 2)
            throw new RuntimeException(this.createUnsupportedArgumentMessage());

        List<Object> objects = FunctionHelper.generateObjects(arguments);
        if (objects.get(0) instanceof Map) {
            return new Atom(((Map) objects.get(0)).get(objects.get(1)));
        } else if (objects.get(0) instanceof List) {
            return new Atom(((List) objects.get(0)).get((Integer) objects.get(1)));
        } else if (objects.get(0) instanceof Element) {
            return new Atom(((Element) objects.get(0)).getProperty((String) objects.get(1)));
        } else {
            throw new RuntimeException(this.createUnsupportedArgumentMessage());
        }
    }

    public String getFunctionName() {
        return FUNCTION_NAME;
    }
}
