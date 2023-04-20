package org.uv.programa04cc;

import java.sql.Connection;

/**
 *
 * @author Angel
 */
public abstract class TransactionDB<T> {
    protected T p;
    protected TransactionDB(T p) {
        this.p = p;
    }
    
    public abstract boolean execute(Connection con);
}
