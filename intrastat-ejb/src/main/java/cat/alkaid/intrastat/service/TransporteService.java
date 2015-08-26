package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Transporte;

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
public class TransporteService {
    @PersistenceContext
    private EntityManager em;

    public Transporte findByCodigo(String codigo){
        return em.find(Transporte.class, codigo);
    }

    public List<Transporte> findAll(){
        TypedQuery<Transporte>query = em.createQuery("SELECT p FROM Transporte p",Transporte.class);
        return query.getResultList();
    }

    public boolean create(Transporte item){
        em.persist(item);
        return true;
    }

    public boolean update(Transporte item){
        Transporte target = findByCodigo(item.getCodigo());
        if(target != null) {
            target.setName(item.getName());
            target.setCodigo(item.getCodigo());

            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(String codigo){
        Transporte target = findByCodigo(codigo);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

}
