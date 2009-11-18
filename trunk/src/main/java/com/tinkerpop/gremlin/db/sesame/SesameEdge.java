package com.tinkerpop.gremlin.db.sesame;

import com.tinkerpop.gremlin.Edge;
import com.tinkerpop.gremlin.Vertex;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.ContextStatementImpl;
import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 * @version 0.1
 */
public class SesameEdge implements Edge {

    Statement statement;
    SailConnection sailConnection;

    protected final static String NAMED_GRAPH = "named_graph";
    protected static Set<String> keys = new HashSet<String>();

    static {
        keys.add(NAMED_GRAPH);
    }

    public SesameEdge(Statement statement, SailConnection sailConnection) {
        this.statement = statement;
        this.sailConnection = sailConnection;
    }

    public String getLabel() {
        return this.statement.getPredicate().toString();
    }

    public Set<String> getPropertyKeys() {
        return keys;
    }

    public Object getProperty(String key) {
        if (key.equals(NAMED_GRAPH))
            return this.statement.getContext();
        else
            return null;
    }

    public void setProperty(String key, Object value) {
        if (key.equals(NAMED_GRAPH)) {
            try {
                sailConnection.removeStatements(this.statement.getSubject(), this.statement.getPredicate(), this.statement.getObject(), this.statement.getContext());
                Statement newStatement = new ContextStatementImpl(this.statement.getSubject(), this.statement.getPredicate(), this.statement.getObject(), (Resource) value);
                sailConnection.addStatement(newStatement.getSubject(), this.statement.getPredicate(), this.statement.getObject(), this.statement.getContext());
                this.statement = newStatement;
            } catch (SailException e) {
                e.printStackTrace();
            }
        }
    }

    public Vertex getVertex(Vertex.Direction direction) {
        if (direction == Vertex.Direction.OUT)
            return new SesameVertex(this.statement.getSubject(), this.sailConnection);
        else
            return new SesameVertex(this.statement.getObject(), this.sailConnection);
    }
}