package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Periodo;
import cat.alkaid.intrastat.model.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by xavier on 21/07/15.
 */

@Stateless
@Local
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public User findById(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        TypedQuery<User>query = em.createQuery("SELECT p FROM User p",User.class);
        return query.getResultList();
    }

    public User findByUsername(String username){
        TypedQuery<User>query = em.createQuery("SELECT p FROM User p WHERE p.username = ?0 ",User.class);
        query.setParameter(0, username);
        List<User> list = query.getResultList();
        if(list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public boolean create(User item){
        em.persist(item);
        return true;
    }

    public boolean update(User item){
        User target = findById(item.getId());
        if(target != null) {
            target.setName(item.getName());

            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(Long id){
        User target = findById(id);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

    public boolean changePassword(Long id, String password){
        User target = findById(id);
        if(target != null) {
            target.changePassword(password);

            em.merge(target);
            return true;
        }
        return false;

    }

    public boolean changePerido(Long id, Periodo periodo){
        User target = findById(id);
        if(target != null) {
            target.setPeriodo(periodo);

            em.merge(target);
            return true;
        }
        return false;

    }
}
