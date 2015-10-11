package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Material;

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
public class MaterialService {
    @PersistenceContext
    private EntityManager em;

    public Material findById(Long id){
        return em.find(Material.class, id);
    }

    public List<Material> findAll(){
        TypedQuery<Material> query = em.createQuery("SELECT p FROM Item p",Material.class);
        return query.getResultList();
    }

    public List<Material> findByPeriodo(Long idPer){
        TypedQuery<Material> query = em.createQuery("SELECT p FROM Item p WHERE p.periodo.id = ?0",Material.class);
        query.setParameter(0, idPer);
        return query.getResultList();
    }

    public boolean create(Material material){
        em.persist(material);
        return true;
    }

    public boolean update(Material material){
        Material target = findById(material.getId());

        if(target != null) {
            target.setName(material.getName());
            target.setCategory(material.getCategory());
            target.setImporte(material.getImporte());
            target.setPeso(material.getPeso());
            target.setUnidades(material.getUnidades());
            target.setCodigo(material.getCodigo());

            target.setCategory(material.getCategory());

            //target.setFactura(material.getFactura());
            target.setEntrega(material.getEntrega());

            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(Long id){
        Material target = findById(id);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

}
