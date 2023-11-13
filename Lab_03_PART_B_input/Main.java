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
    public int calculateTotal() {return price * quantity;}

}





class ShoppingCart {
	private int customerID;
    // declaring a list of items in the cart
	private List<CartItem> items; 
      	
	public ShoppingCart(int customerID){
		this.customerID = customerID;
		this.items = new ArrayList<>();
	}


    // getters 
	public int getCustomerID()       { return customerID; }
	public List<CartItem> getItems() { return items;      }

    // setters
	public void setCustomerID(int customerID)  {this.customerID = customerID;	}
	public void setItems(List<CartItem> items) {this.items = items;	            }


	public void addItem(CartItem product){items.add(product);	}
	public void removeItem(CartItem product){items.remove(product);	}
	
	public int totalPrice(){
		int finalPrice = 0;
		for (CartItem item : items)
			finalPrice += item.calculateTotal();
		return finalPrice;
	}
	
}




class Customer {
	private int customerID;
	private List <ShoppingCart> carts; // List of shoppingCarts for a customer

	public Customer(int customerID){
		this.customerID = customerID;
		this.carts = new ArrayList<>();
	}

	// getters 
	public int getCustomerID()           {return customerID; }
	public List<ShoppingCart> getCarts() {return carts;      }

    // setters
	public void setCustomerID(int customerID)              { this.customerID = customerID;	}
	public void setShoppingCarts(List<ShoppingCart> carts) { this.carts = carts;	        }

    	
	public void addShoppingCart(ShoppingCart cart)  {carts.add(cart);    }
	public void removeCart(ShoppingCart cart)       {carts.remove(cart); }

}	
		


class CustomerManager {

	private List <Customer> customers; // managing all customers

    
	public CustomerManager() {customers = new ArrayList<>(); }
    
    public List<Customer> getCustomers(){
        return customers;

    }
	public Customer createCustomer(int customerID){
		Customer customer = new Customer(customerID);
		customers.add(customer);
		return customer;
	}


	public void addShoppingCart(Customer customer, ShoppingCart cart){
		customer.addShoppingCart(cart);
	}
	
	public ShoppingCart getCustomerShoppingCart(Customer customer, int cartIndex){
		if (cartIndex >=0 && cartIndex - 1 < customer.getCarts().size()){
			return customer.getCarts().get(cartIndex - 1);
		}
		return null;
	}

	
	// calculates the total price in each cart
	public int calculateTotalCarts(Customer customer){
		int total = 0;
		for(ShoppingCart cart : customer.getCarts()){
			total += cart.totalPrice();
		}
		return total;
	}
}


class Main {
	public static void main(String[] args){
		CustomerManager customerManager = new CustomerManager();
		Scanner scanner = new Scanner(System.in);	
		int n,m,k;
		n = scanner.nextInt();
		scanner.nextLine();
		
		for(int i=1;i<=n;i++){
			customerManager.createCustomer(i);
		}
		
		Customer customer;

		for(int i=1;i<=n;i++){
			customer = customerManager.getCustomers().get(i-1);
			k = scanner.nextInt();
			for(int j=1;j<=k;j++){
				ShoppingCart cart = new ShoppingCart(i);
				customerManager.addShoppingCart(customer, cart);
			}
		}

		scanner.nextLine();
		m = scanner.nextInt();
		scanner.nextLine();

		int customerID, cartID, price, quantity;
		ArrayList<Integer> output = new ArrayList<Integer>();
			
		ShoppingCart cart;

		for(int i=0;i<m;i++){
			String operation = scanner.nextLine();
			String[] parts = operation.split(" ");
			if( parts[1].equals("total")){
				customerID = Integer.parseInt(parts[0]);
				customer = customerManager.getCustomers().get(customerID-1);
				output.add(customerManager.calculateTotalCarts(customer));
			}

			else if(parts[2].equals("total")){
				customerID = Integer.parseInt(parts[0]);
				cartID = Integer.parseInt(parts[1]);
				customer = customerManager.getCustomers().get(customerID-1);
				cart = customerManager.getCustomerShoppingCart(customer,cartID);
				output.add(cart.totalPrice());
			}

			else if (parts[2].equals("add")){
				String name = parts[3];
				price = Integer.parseInt(parts[4]);
				quantity = Integer.parseInt(parts[5]);
				customerID = Integer.parseInt(parts[0]);
				cartID = Integer.parseInt(parts[1]);
				customer = customerManager.getCustomers().get(customerID-1);
				cart = customerManager.getCustomerShoppingCart(customer,cartID);
				CartItem product = new CartItem(1,name,price,quantity);
				cart.addItem(product);
			}


			else if (parts[2].equals("remove")){
				String name = parts[3];
				quantity = Integer.parseInt(parts[4]);
				customerID = Integer.parseInt(parts[0]);
				cartID = Integer.parseInt(parts[1]);
				customer = customerManager.getCustomers().get(customerID-1);
				cart = customerManager.getCustomerShoppingCart(customer,cartID);
				for(CartItem product : cart.getItems()){
					if(product.getName().equals(name)){
						product.setQuantity(product.getQuantity() - quantity);
						break;
					}
				}	
			}				
		}

        scanner.close();

        
		for(int value : output){
			System.out.println(value);
		}
	
	}
}	

