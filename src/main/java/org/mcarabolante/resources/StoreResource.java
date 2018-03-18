package org.mcarabolante.resources;

import org.mcarabolante.domain.product.Product;
import org.mcarabolante.domain.product.ProductDAO;
import org.mcarabolante.domain.store.Store;
import org.mcarabolante.domain.store.StoreDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.mcarabolante.commons.ResponseUtil.fromOptional;

@Path("api/v1/Store")
@Produces(MediaType.APPLICATION_JSON)
public class StoreResource {

    private final StoreDAO storeDAO;
    private final ProductDAO productDAO;

    public StoreResource(StoreDAO storeDAO, ProductDAO productDAO) {
        this.storeDAO = storeDAO;
        this.productDAO = productDAO;
    }

    @GET
    public List<Store> list(){
        return storeDAO.list();
    }

    @GET
    @Path("search/{searchText}")
    public List<Store> searchByName(@PathParam("searchText") String searchText){
        // TODO - create custom binder in order to remove "%"
        return storeDAO.listLikeName("%" + searchText + "%");
    }

    @GET
    @Path("{storeId}")
    public Response findById(@PathParam("storeId") Long storeId){
        return fromOptional(storeDAO.findById(storeId), "Store " + storeId + " not found.");
    }

    @GET
    @Path("{storeId}/products")
    public List<Product> productsByStore(@PathParam("storeId") Integer storeId){
        return productDAO.listByStore(storeId);
    }



}
