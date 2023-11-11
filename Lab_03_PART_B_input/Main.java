import java.util.*;


/* TO do => 
 
- better CID and PID names
- private members
- find out a way to pass values of hasmaps without making them public
- make the code concise enough to have no need for lenghty comments


*/



class CartItem {
    private String productID;
    private String name;
    private double price;
    private int    quantity;


    public CartItem(String productID, String name, double price, int quantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    
    // getters and setters
    public String getProductID() { return this.productID; }
    public String getName()      { return this.name;      }
    public double getPrice()     { return this.price;     }
    public int    getQuantity()  { return this.quantity;  }

    public void setProductID(String newProductID) { this.productID = newProductID; }
    public void setName(String newName)           { this.name = newName;           }
    public void setPrice(double newPrice)         { this.price = newPrice;         }
    public void setQuantity(int newQuantity)      { this.quantity = newQuantity;   }


}



/*
 Assumption made :-

 -> Shopping cart has a unique cart ID and not a customer ID
 -> Reason : If same customer has multiple shopping carts 
             then each cart can't be given the same customer ID  
             as then it won't be unique
 */


class ShoppingCart {
    // Hashmap of  <ProductID : cartItem object> pair
    private Map<String, CartItem> items;
    private String cartId;

    public ShoppingCart(String cartId) {
        this.cartId= cartId;
        this.items = new HashMap<>();
    }

    // getters and setters
    public String getCartId()                  { return this.cartId;         }
    public Collection<CartItem> getCartItems() { return this.items.values(); }
    public void   setCartId(String newCartId)  { this.cartId = newCartId;    }


    public void   addItem(CartItem item)       { this.items.put(item.getProductID(), item); }
    public void   removeItem(CartItem item)    { this.items.remove(item.getProductID());    }


    // calculates the total price of the Cart
    public double calculateTotalPriceOfCart() {
        double total = 0.0;
        for (CartItem item : this.items.values()) 
            total += item.getPrice() * item.getQuantity();
        return total;
    }


}





class Customer {
    private String customerID;
    private Map<String, ShoppingCart> shoppingCarts;

    public Customer(String customerID) {
        this.customerID = customerID;
        this.shoppingCarts = new HashMap<>();
    }

    
    //  getter and setter
    public String getCustomerId()                                 { return this.customerID;            }
    public Collection<ShoppingCart> getCustomerShoppingCart()     { return this.shoppingCarts.values();}
    public void setCustomerId(String newCID)                      { this.customerID = newCID;          }

    public void addShoppingCart(ShoppingCart cart)                { shoppingCarts.put(cart.getCartId(), cart);   }
    public ShoppingCart getShoppingCart(String cartID)            { return shoppingCarts.get(cartID);            }

}



// This is sought of like the control center
// all the object creations happen here
// but prinicples of aggregations are upheld in the sense that these objects
// are stand-alone and independent i.e we can create them from main fucntion too
class CustomerManager {
    // < CustomerID : customer object >
    private Map<String, Customer> customers;

    public CustomerManager() { customers = new HashMap<>(); }



    public Customer createCustomer(String customreID){
        Customer customer = new Customer(customreID);
        customers.put(customreID, customer);
        return customer;
    }

    public ShoppingCart createShoppingCart(String cartID){
        ShoppingCart cart = new ShoppingCart(cartID);
        return cart;
    }

    public CartItem createItem(String productID, String productName,double price, int quantity){
        CartItem item = new CartItem(productID, productName, price, quantity);
        return item;
    }

    public void addShoppingCartToCustomer(Customer customer, ShoppingCart cart) {
        customer.addShoppingCart(cart);
    }


    public double calculateTotalValue(String customerID) {
        Customer customer = customers.get(customerID);
        if (customer == null) 
            return 0.0;

        double totalValue = 0.0;
        for (ShoppingCart cart : customer.getCustomerShoppingCart() )
            totalValue += cart.calculateTotalPriceOfCart();
        
        return totalValue;
    }



    public void addItemToCart(ShoppingCart cart, CartItem item)       { cart.addItem(item);   }
    public void removeItemFromCart(ShoppingCart cart, CartItem item)  { cart.removeItem(item);}



     public void displayAllCustomerInformation(){

        for (Customer customer : customers.values()) {
            System.out.println("Customer : "+ customer.getCustomerId()+"\n");
            System.out.println("All Shopping carts :-");
            
            double sumOfAllCart = 0;
            for (ShoppingCart cart : customer.getCustomerShoppingCart()) {
                System.out.println("Shopping cart : "+cart.getCartId());
                for (CartItem item: cart.getCartItems()) {
                    System.out.println("productID: " + item.getProductID() + ", name: " + item.getName() + ", price: " + item.getPrice() + "Quantity: " + item.getQuantity());
                }
                double priceOfCart = cart.calculateTotalPriceOfCart();
                sumOfAllCart  += priceOfCart;
                System.out.println("Total cost : "+ priceOfCart);
            }

            System.out.println("\n\nTotal cost of all the carts of Customer " + customer.getCustomerId() + " :- " + sumOfAllCart+"\n\n\n");
            
        }

     }
    
}





public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ArrayList<Integer> numOfCartsPerCustomer = new ArrayList<>();


        while(n-- != 0){
            int input = sc.nextInt();
            numOfCartsPerCustomer.add(input);
        }

        
        
        int m = sc.nextInt();
        while(m-- != 0){

            String line = sc.nextLine();
            String[] words = line.split(" ");
            int sizeOfInput = words.length ;
            System.out.println(sizeOfInput);


        }


        sc.close();






        // Entire customer interface is handled via this one Class 
        // CustomerManager customerManager = new CustomerManager();

        // Customer person1 = customerManager.createCustomer("customer101");
        // Customer person2 = customerManager.createCustomer("customer102");
        // Customer person3 = customerManager.createCustomer("customer103");


        // ShoppingCart cart1a = customerManager.createShoppingCart("cart101a");
        // ShoppingCart cart1b = customerManager.createShoppingCart("cart101b");
        // ShoppingCart cart2  = customerManager.createShoppingCart("cart102");
        // ShoppingCart cart3  = customerManager.createShoppingCart("cart103");

        // customerManager.addShoppingCartToCustomer(person1, cart1a);
        // customerManager.addShoppingCartToCustomer(person1, cart1b);
        // customerManager.addShoppingCartToCustomer(person2, cart2);
        // customerManager.addShoppingCartToCustomer(person3, cart3);
        
        
        // CartItem item1 = customerManager.createItem("product001", "Product A", 10.0, 2);
        // CartItem item2 = customerManager.createItem("product002", "Product B", 5.0,  3);
        // CartItem item3 = customerManager.createItem("product003", "Product C", 8.0,  1);
        // CartItem item4 = customerManager.createItem("product004", "Product D", 100.0,2);
        
        
        // customerManager.addItemToCart(cart1a, item1);
        // customerManager.addItemToCart(cart1b, item2);
        // customerManager.addItemToCart(cart2 , item3);
        // customerManager.addItemToCart(cart3 , item4);
        

        // System.out.println("Complete customer information -------------------------------------");
        // System.out.println("-------------------------------------------------------------------");
        // customerManager.displayAllCustomerInformation();
        
        
        // System.out.println("Removing few items for testing ....\n\n");
        // customerManager.removeItemFromCart(cart2 , item3);
        // System.out.println("Removed item3 from cart2");
        // customerManager.removeItemFromCart(cart3 , item4);
        // System.out.println("Removed item4 from cart3");
        // System.out.println("Removal complete");


        // System.out.println("\n\n\n\nComplete customer information -------------------------------------");
        // System.out.println("-------------------------------------------------------------------");
        // customerManager.displayAllCustomerInformation();

    }
} 
