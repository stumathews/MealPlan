/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL;

import DAL.Interfaces.IProductDAO;
import BOL.Interfaces.IProduct;
import Website.Controllers.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Product business logic
 * @author Lenovo x220
 */
public class Product implements IProduct
{
    
    private DAL.Interfaces.IProductDAO productDAO;

    @Autowired
    public void setProductDAO(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }
        
    @Transactional
    public BOLO.Product MakeProductFormFromId(String productID) throws Exception 
    {
        DEL.Product prod = productDAO.getProductByID( productID);
        /* Construct the form binding object aka command object: */
        BOLO.Product newProduct = new BOLO.Product();
        newProduct = ConvertToBOLO(prod);
        return newProduct;
        
    }
    
    public BOLO.Product ConvertToBOLO( DEL.Product del )
    {
        BOLO.Product product = new BOLO.Product();
        product.setIdentifier(del.getId().toString());
        product.setTitle(del.getTitle());
        product.setWhatIsIt(del.getWhatIsIt());
        product.setWhoMadeIt(del.getWhoMadeIt());        
        return product;
        
    
    }
}
