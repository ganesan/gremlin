package com.tinkerpop.gremlin.compiler.util;

import com.tinkerpop.gremlin.compiler.GremlinEvaluator;
import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.compiler.operations.Operation;
import com.tinkerpop.gremlin.compiler.types.Atom;
import com.tinkerpop.gremlin.compiler.types.Func;
import com.tinkerpop.gremlin.compiler.types.GPath;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;

import java.util.Iterator;
import java.util.List;

/**
 * CodeBlock holds body of the statements and functions.
 * 
 * @author Pavel A. Yaskevich
 */
public class CodeBlock {

    private final List<Tree> statements;
    private final GremlinScriptContext context;
    
    public CodeBlock(final List<Tree> statements, final GremlinScriptContext context) {
        this.statements = statements;
        this.context = context;
    }

    public Atom invoke() throws RuntimeException {
        Atom result = null;
        Operation currentOperation;
        
        for (Tree statement : statements) {
            final CommonTreeNodeStream nodes = new CommonTreeNodeStream(statement);
            final GremlinEvaluator walker = new GremlinEvaluator(nodes, this.context);
            
            try {
                currentOperation = walker.statement().op;
                result = currentOperation.compute();

                Object value = result.getValue();

                // auto iteration
                if (value instanceof Iterable) {
                    for (Object o : (Iterable) value) {}
                } else if(value instanceof Iterator) {
                    Iterator itty = (Iterator) value;

                    while (itty.hasNext()) itty.next();
                }
            } catch (RecognitionException e) {
                throw new RuntimeException(e);
            }
        }

        return (result == null) ? new Atom<Object>(null) : result;   
    }
}
