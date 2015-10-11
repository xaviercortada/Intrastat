package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Company;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by xavier on 21/07/15.
 */

@Stateless
@Local
public class CompanyService {
    @PersistenceContext
    private EntityManager em;

    public Company findById(Long id){
        return em.find(Company.class, id);
    }

    public List<Company> findAll(){
        TypedQuery<Company>query = em.createQuery("SELECT p FROM Company p", Company.class);
        return query.getResultList();
    }

    public boolean create(Company item){
        em.persist(item);
        return true;
    }

    public boolean update(Company item){
        Company target = findById(item.getId());
        if(target != null) {
            target.setName(item.getName());
            target.setCodigo(item.getCodigo());
            target.setDescripcion(item.getDescripcion());
            target.setModificationDate(new Date(Calendar.getInstance().getTimeInMillis()));
            target.setCif(item.getCif());
            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(Long id){
        Company target = findById(id);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCompany(Company item) {
        em.persist(item);
    }

}
