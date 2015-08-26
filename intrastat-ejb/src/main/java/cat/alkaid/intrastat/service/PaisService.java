package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Pais;

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
public class PaisService {
    @PersistenceContext
    private EntityManager em;

    public Pais findByCodigo(int codigo){
        return em.find(Pais.class, codigo);
    }

    public List<Pais> findAll(){
        TypedQuery<Pais>query = em.createQuery("SELECT p FROM Pais p",Pais.class);
        return query.getResultList();
    }

    public boolean create(Pais item){
        em.persist(item);
        return true;
    }

    public boolean update(Pais item){
        Pais target = findByCodigo(item.getCodigo());
        if(target != null) {
            target.setCodigo(item.getCodigo());
            target.setName(item.getName());
            target.setSigla(item.getSigla());

            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(int codigo){
        Pais target = findByCodigo(codigo);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

}
