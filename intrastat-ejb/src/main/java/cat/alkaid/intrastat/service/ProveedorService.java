package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Proveedor;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by xavier on 9/07/15.
 */

@Stateless
@LocalBean
public class ProveedorService {

    @PersistenceContext
    private EntityManager em;

    public Proveedor findById(Long id){
        return em.find(Proveedor.class, id);
    }

    public List<Proveedor> findAll(){
        TypedQuery<Proveedor> query = em.createQuery("SELECT p FROM Proveedor p",Proveedor.class);
        return query.getResultList();
    }

    public boolean create(Proveedor item){
        em.persist(item);
        return true;
    }

    public boolean update(Proveedor item){
        Proveedor target = findById(item.getId());
        if(target != null) {
            target.setName(item.getName());
            target.setCodigo(item.getCodigo());
            target.setCpostal(item.getCpostal());
            target.setDocumento(item.getDocumento());
            target.setDomicilio(item.getDomicilio());
            target.setPoblacion(item.getPoblacion());
            target.setProvincia(item.getProvincia());

            em.merge(target);
            return true;
        }
        return false;
    }

    public boolean delete(Long id){
        Proveedor target = findById(id);
        if(target != null) {
            em.remove(target);
            return true;
        }

        return false;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addProveedor(Proveedor proveedor) {
        em.persist(proveedor);
    }

}
