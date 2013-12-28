/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.Interfaces;

import BOLO.ProductCharacteristic;
import DEL.Characteristic;
import java.util.List;
import java.util.Set;

/**
 * Contract for implementing new Characteristic business logic
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface ICharacteristic 
{
    public void addCharacteristic( String name, String Description);
    public void addProductCharacteristic(String productID, String title, String description) throws Exception;
    public List<BOLO.ProductCharacteristic> getProductCharacteristics(String productID) throws Exception;
    public List<BOLO.ProductCharacteristic> getAllCharacteristics() throws Exception;
    public List<BOLO.ProductCharacteristic> Convert(List<DEL.Characteristic> characteristics) throws Exception;
    public List<DEL.CharacteristicReview> ConvertToDels(List<BOLO.CharacteristicReview> characteristicReviews) throws Exception;


}
