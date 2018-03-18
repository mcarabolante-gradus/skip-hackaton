package org.mcarabolante.resources;

import org.mcarabolante.domain.product.Product;
import org.mcarabolante.domain.product.ProductDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.mcarabolante.commons.ResponseUtil.fromOptional;

@Path("api/v1/Product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private final ProductDAO productDAO;

    public ProductResource(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GET
    public List<Product> list(){
        return productDAO.list();
    }

    @GET
    @Path("/search/{searchText}")
    public List<Product> searchByName(@PathParam("searchText") String searchText){
        return productDAO.listLikeName("%" + searchText + "%");
    }

    @GET
    @Path("{productId}")
    public Response findById(@PathParam("productId") Long id){
        return fromOptional(productDAO.findById(id), "Product " + id + " not found.");
    }

}
