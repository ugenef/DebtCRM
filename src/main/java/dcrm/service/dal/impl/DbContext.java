package dcrm.service.dal.impl;

import dcrm.service.bl.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DbContext {
    private SessionUtil _sessionUtil;

    public DbContext(){
        _sessionUtil = new SessionUtil();
    }

    public void OpenConnection(){
        _sessionUtil.openTransactionSession();
    }

    public void CloseConnection(){
        _sessionUtil.closeTransactionSession();
    }

    public <TEntity> List<TEntity> getQuery(String sql, Class entityClass){
        Session session = _sessionUtil.getSession();
        Query query = session.createNativeQuery(sql).addEntity(entityClass);
        List<TEntity> entities = query.list();
        return entities;
    }
}
