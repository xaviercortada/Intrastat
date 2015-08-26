package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Category;

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
public class CategoryService {
    @PersistenceContext
    private EntityManager em;

    public Category findById(Long id){
        return em.find(Category.class, id);
    }

    public List<Category> findAll(){
        TypedQuery<Category>query = em.createQuery("SELECT p FROM Category p",Category.class);
        return query.getResultList();
    }

    public boolean create(Category item){
        em.persist(item);
        return true;
    }

    public boolean update(Category item){
        Category target = findById(item.getId());
        if(target != null) {
            target.setName(item.getName());
            target.setCodigo(item.getCodigo());
            target.setDescripcion(item.getDescripcion());
            target.setModificationDate(new Date(Calendar.getInstance().getTimeInMillis()));

            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(Long id){
        Category target = findById(id);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCategory(Category item) {
        em.persist(item);
    }


}
