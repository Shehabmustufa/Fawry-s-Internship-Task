package Products;

import java.time.LocalDate;

public class ProductRepository {
    public static final Product CHEESE = new Product("Cheese",50
            ,10,true, LocalDate.now().plusMonths(6)
            ,true,0.25);
    public static final Product BISCUITS = new Product("Biscuits"
            , 150, 4,true
            ,LocalDate.now().plusMonths(12),true,0.2);
    public static final Product TV = new Product("TV", 2000, 2,false,
            null,true,5);
    public static final Product SCRATCH_CARD = new Product("Scratch Card", 50, 10,false,
            null,false,0.0) {};
}
