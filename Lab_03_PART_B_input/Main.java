import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;




// Don't forget to change variable names
// Add more comments ( change existing ones )


class CartItem 
{
	private int productID;
	private String name;
	private int price;
	private int quantity;


	public CartItem(int productID, String name, int price, int quantity)
	{
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	// Getters 
	public int    getID()       { return productID; }
	public String getName()     { return name;      }
	public int    getPrice()    { return price;     }
	public int    getQuantity() { return quantity;  }

    // setters
	public void   setID(int ID)             {this.productID = ID;       }
	public void   setName(String name)      {this.name = name;	        }
	public void   setPrice(int price)       {this.price = price;	    }
	public void   setQuantity(int quantity) {this.quantity = quantity;	}

    // Total Cost of that item bought
    public int calculateTotalPriceOfItem() {return price * quantity;}

}





class ShoppingCart {
	private int customerID;
    // All the items in a shopping cart are stored as a List
	private List<CartItem> items; 
    
    

	public ShoppingCart(int customerID){
		this.items = new ArrayList<>();
		this.customerID = customerID;
	}


    // getters 
	public int getCustomerID()       { return customerID; }
	public List<CartItem> getItems() { return items;      }

    // setters
	public void setCustomerID(int customerID)  { this.customerID = customerID;	}
	public void setItems(List<CartItem> items) { this.items = items;	        }


	public void addItemToShoppingCart(CartItem product)      { items.add(product);	  }
	public void removeItemFromShoppingCart(CartItem product) { items.remove(product); }
	
	public int totalPriceOfShoppingCart(){
		int sum = 0;
		for (CartItem item : items)
			sum += item.calculateTotalPriceOfItem();
		return sum;
	}
}




class Customer {
	private int customerID;
    // All the Shopping Carts of a customer is stored as a List
	private List <ShoppingCart> carts; 



	public Customer(int customerID){
		this.carts = new ArrayList<>();
		this.customerID = customerID;
	}



	// getters 
	public int getCustomerID()           {return customerID; }
	public List<ShoppingCart> getCarts() {return carts;      }

    // setters
	public void setCustomerID(int customerID)              { this.customerID = customerID;	}
	public void setShoppingCarts(List<ShoppingCart> carts) { this.carts = carts;	        }


	public void addShoppingCartToCustomer(ShoppingCart cart)  {carts.add(cart);    }
	public void removeCartFromCustomer(ShoppingCart cart)     {carts.remove(cart); }

}	
		


class CustomerManager {

	private List <Customer> customers; // managing all customers

    
	public CustomerManager() {customers = new ArrayList<>(); }
    
    public List<Customer> getCustomers() { return customers; }
	public void addShoppingCartToCustomer(Customer customer, ShoppingCart cart){
		customer.addShoppingCartToCustomer(cart);
	}


	public Customer createCustomer(int customerID){
		Customer customer = new Customer(customerID);
		customers.add(customer);
		return customer;
	}


	
	public ShoppingCart getCustomerShoppingCart(Customer customer, int cartIndex){
		if (cartIndex >=0 && cartIndex - 1 < customer.getCarts().size())
			return customer.getCarts().get(cartIndex - 1);
		
		return null;
	}

	
	// calculates the total price in each cart
	public int calculateTotalCarts(Customer customer){
		int sum = 0;
		for(ShoppingCart cart : customer.getCarts()){
			sum += cart.totalPriceOfShoppingCart();
		}
		return sum;
	}
}


/* ---------------- test case to try out ! -------------------------
2
2 3
19
1 1 add chocolate 40 3
2 1 add mango 20 3
1 1 add apple 20 4
1 2 add guava 25 2
2 2 add guava 25 4
2 1 add orange 40 7
1 total
1 1 total
1 2 total
2 total
1 1 remove apple 2
2 1 total
2 2 total
2 2 remove guava 1
2 2 total
2 3 total
2 total
1 1 total
1 total 
---------------------------------------------------------------------*/


class Main {

	public static void main(String[] args){

		CustomerManager customerManager = new CustomerManager();
        
		Scanner scanner = new Scanner(System.in);	

        
		int numOfCustomers = scanner.nextInt();
		scanner.nextLine();
		for(int i=1;i<=numOfCustomers;i++)customerManager.createCustomer(i);
		
		
        
		Customer customer;
		for(int customerID = 1; customerID <= numOfCustomers; customerID++){
			customer = customerManager.getCustomers().get(customerID-1);
			int numOfCartsForCustomer = scanner.nextInt();
            
			for(int i_ = 1; i_ <= numOfCartsForCustomer; i_++){
				ShoppingCart cart = new ShoppingCart(customerID);
				customerManager.addShoppingCartToCustomer(customer, cart);
			}
		}


		scanner.nextLine();
		int numOfOperations = scanner.nextInt();
		scanner.nextLine();


		int customerID, cartID, price, quantity;
		ArrayList<Integer> output = new ArrayList<Integer>();



        ShoppingCart cart;
		while(numOfOperations-- != 0){
			String line = scanner.nextLine();
			String[] words = line.split(" ");


            // finding total of all carts of a customer
			if( words[1].equals("total")){
				customerID = Integer.parseInt(words[0]);

				customer = customerManager.getCustomers().get(customerID-1);

				output.add(customerManager.calculateTotalCarts(customer));
			}

            
            // finding total of all items in a cart of a customer
			else if(words[2].equals("total")){
				customerID = Integer.parseInt(words[0]);
				cartID = Integer.parseInt(words[1]);

				customer = customerManager.getCustomers().get(customerID-1);
				cart     = customerManager.getCustomerShoppingCart(customer,cartID);

				output.add(cart.totalPriceOfShoppingCart());
			}

            // adding item to a cart
			else if (words[2].equals("add")){
				String name = words[3];

				customerID = Integer.parseInt(words[0]);
				cartID     = Integer.parseInt(words[1]);
				price      = Integer.parseInt(words[4]);
				quantity   = Integer.parseInt(words[5]);

				customer = customerManager.getCustomers().get(customerID-1);
				cart     = customerManager.getCustomerShoppingCart(customer,cartID);

                // this shows the aggregation nature of Design implemented
                // in the sense that CartItem can exist independent of Customer
				CartItem product = new CartItem(1,name,price,quantity);

				cart.addItemToShoppingCart(product);
			}


            // removing item from cart
			else if (words[2].equals("remove")){
				String name = words[3];

				customerID = Integer.parseInt(words[0]);
				cartID     = Integer.parseInt(words[1]);
				quantity   = Integer.parseInt(words[4]);

				customer = customerManager.getCustomers().get(customerID-1);
				cart     = customerManager.getCustomerShoppingCart(customer,cartID);

                // setting the item's quantity to 0
				for(CartItem product : cart.getItems()){
					if(product.getName().equals(name)){
						product.setQuantity(product.getQuantity() - quantity);
						break;
				}}	
			}				
		}

        scanner.close();


        for(int value : output)
			System.out.println(value);
		
	
	}
}	

