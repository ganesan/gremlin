package com.tinkerpop.gremlin.functions.sail.neo4jsail;

import com.tinkerpop.gremlin.functions.AbstractFunctions;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class Neo4jSailFunctions extends AbstractFunctions {

    private static final String NAMESPACE = "neo4jsail";

    public Neo4jSailFunctions() {
        this.functions.add(new OpenFunction());

    }

    public String getNamespace() {
        return NAMESPACE;
    }
}