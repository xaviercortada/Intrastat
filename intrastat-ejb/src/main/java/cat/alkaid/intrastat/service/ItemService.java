package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Item;

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
public class ItemService {
    @PersistenceContext
    private EntityManager em;

    public Item findById(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        TypedQuery<Item> query = em.createQuery("SELECT p FROM Item p",Item.class);
        return query.getResultList();
    }

    public List<Item> findByPeriodo(Long idPer){
        TypedQuery<Item> query = em.createQuery("SELECT p FROM Item p WHERE p.periodo.id = ?0",Item.class);
        query.setParameter(0, idPer);
        return query.getResultList();
    }

    public boolean create(Item item){
        em.persist(item);
        return true;
    }

    public boolean update(Item item){
        Item target = findById(item.getId());

        if(target != null) {
            target.setName(item.getName());
            target.setCategory(item.getCategory());
            target.setImporte(item.getImporte());
            target.setPeso(item.getPeso());
            target.setUnidades(item.getUnidades());
            target.setCodigo(item.getCodigo());

            target.setProveedor(item.getProveedor());
            target.setCategory(item.getCategory());

            target.setFactura(item.getFactura());
            target.setPais(item.getPais());
            target.setEntrega(item.getEntrega());
            target.setTransporte(item.getTransporte());

            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(Long id){
        Item target = findById(id);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

}
