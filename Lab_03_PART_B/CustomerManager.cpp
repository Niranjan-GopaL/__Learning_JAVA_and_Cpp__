#include <iostream>
#include<vector>
#include<map>
#include<string>
using namespace std;


class CartItem {
private:
    string productID;
    string name;
    double price;
    int quantity;


public:
    CartItem(){ cout << "DEFAULT CONSTRUCTOR TRIGGERED _ CART ITEM\n"; }

    CartItem(string productID, string name, double price, int quantity){
        this -> productID = productID;
        this -> name = name;
        this -> price = price;
        this -> quantity = quantity; 
    }


    // Getters and setters
    string getProductID()   { return productID; }
    string getName()        { return name;      }
    double getPrice()       { return price;     }
    int getQuantity()       { return quantity;  }


    void setProductID(string newPID)  { productID = newPID;     }
    void setName(string newName)      { name = newName;         }
    void setPrice(double newPrice)    { price= newPrice;        }
    void setQuantity(int newQuantity) { quantity = newQuantity; }
};




class ShoppingCart {
private:
    map<string, CartItem*> items;
    string cartId;

public:
    ShoppingCart()                    { cout << "DEFAULT CONSTRUCTOR TRIGGERED SHOPPING CART\n";}
    ShoppingCart(string cartId)       { this->cartId = cartId; items =  map<string, CartItem*>();}


    // getters and setters
    string getCartId()                 { return cartId;        }
    void   setCartId(string newCartId) { cartId = newCartId;   }

    vector<CartItem> getCartItems()
    {
        vector<CartItem> cartItems;
        for (auto item :items) cartItems.push_back(*item.second);
        return cartItems;
    }


    void addItem(CartItem* item)       { items[item->getProductID()] = item; }
    void removeItem(string productID) { items.erase(productID);            }

    double calculateTotalPriceOfCart()  {
        double total = 0.0;
        for (auto item : items)  total += item.second->getPrice() * item.second->getQuantity();
        return total;
    }

};



class Customer {
private:
    string customerID;
    map<string, ShoppingCart*> shoppingCarts;

public:
    Customer( string customerID)           { this -> customerID = customerID;shoppingCarts = map<string, ShoppingCart*>(); }
    Customer()                             { cout << "DEFAULT CONSTRUCTOR TRIGGERED CUSTOMER\n"; }

    // getters and setters
    string getCustomerId()                 { return customerID;               }
    void   setCustomerId(string new_cid)   { customerID = new_cid;            }

    vector<ShoppingCart> getCustomerShoppingCarts()  
    {
        vector<ShoppingCart> customerCarts;
        for ( auto cart : shoppingCarts) customerCarts.push_back(*cart.second);
        return customerCarts;
    }

    void addShoppingCart( ShoppingCart *cart) { shoppingCarts[cart->getCartId()] = cart;
    cout << "ME" << endl;
     }

    ShoppingCart getShoppingCart( string cartID)  {
        auto it = shoppingCarts.find(cartID);
        if (it != shoppingCarts.end()) {
            return *it->second;
        }
        return ShoppingCart();
    }
};



// This is sought of like the control center
// all the object creations happen here
// but prinicples of aggregations are upheld in the sense that these objects
// are stand-alone and independent i.e we can create them from main fucntion too
class CustomerManager {
private:
    // < CustomerID : customer object >
    map<string, Customer*> customers;

public:

    CustomerManager() {cout << "DEFAULT CONSTRUCTOR TRIGGERED _ MANAGER\n";}


    Customer createCustomer( string customerID) {
        Customer customer(customerID);
        customers[customerID] = &customer;

        cout << "\ncreated new entry\n";
        for(auto customer : customers)
            cout << "Customer in map: " << customer.second->getCustomerId() << "\n";

        cout << "----------\n\n";
        return customer;
    }

    ShoppingCart createShoppingCart( string cartID) { return ShoppingCart(cartID);  }

    CartItem     createItem( string productID,  string productName, double price, int quantity) {
        return CartItem(productID, productName, price, quantity);
    }


    double calculateTotalValue( string customerID)  {

        auto it = customers.find(customerID);

        if (it != customers.end()) {
            double totalValue = 0.0;

            for ( auto cart : it->second->getCustomerShoppingCarts()) {
                totalValue += cart.calculateTotalPriceOfCart();
            }
            return totalValue;
        }
        return 0.0;
    }


    // ERROR FOUND !!
    // The shopping cart is not being added to the shopping carts map of customer object
    void addShoppingCartToCustomer(Customer *customer_, ShoppingCart* cart){ 

        customer_->addShoppingCart(cart); 
        
        //cout << customer_.getCustomerId() << endl ;
        cout << "ALL THE SHOPPING CART OF THE CUSTOMER :-\n";
        for ( ShoppingCart c : customer_->getCustomerShoppingCarts()) {

            cout << "SHOPPING CART" << c.getCartId() << "\n";

            for ( CartItem item : c.getCartItems()) {
                cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << "\n";
        }}

    }
    
    void addItemToCart(ShoppingCart *cart, CartItem item)          { cart->addItem(&item);         }
    void removeItemFromCart(ShoppingCart *cart,  string productID) { cart->removeItem(productID); }



    void displayAllCustomerInformation() {

        // customer is a map object
        for ( auto customer : customers) {
            cout << "\nCustomer : " << customer.second->getCustomerId() << "\n\n";
            cout << "All Shopping carts :-\n\n";

            double sumOfAllCart = 0.0;
            //cout << *customer.second.getShoppingCart() << endl;
            for ( ShoppingCart  cart : customer.second->getCustomerShoppingCarts()) {
                cout << "Shopping cart : " << cart.getCartId() << "\n";

                for ( CartItem item : cart.getCartItems()) 
                    cout << "productID: " << item.getProductID() << ", name: " << item.getName() << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << "\n";
                
                double priceOfCart = cart.calculateTotalPriceOfCart();
                sumOfAllCart += priceOfCart;
                cout << "Total cost : " << priceOfCart << "\n\n";
            }

            cout << "\nTotal cost of all the carts of Customer " << customer.second->getCustomerId() << " :- " << sumOfAllCart << "\n\n\n\n";
        }
    }
};



int main() {
    CustomerManager customerManager;

    Customer person1 = customerManager.createCustomer("customer101");
    Customer person2 = customerManager.createCustomer("customer102");
    Customer person3 = customerManager.createCustomer("customer103");

    ShoppingCart cart1a = customerManager.createShoppingCart("cart101a");
    ShoppingCart cart1b = customerManager.createShoppingCart("cart101b");
    ShoppingCart cart2  = customerManager.createShoppingCart("cart102");
    ShoppingCart cart3  = customerManager.createShoppingCart("cart103");

    CartItem item1 = customerManager.createItem("product001", "Product A", 10.0, 2);
    CartItem item2 = customerManager.createItem("product002", "Product B", 5.0, 3);
    CartItem item3 = customerManager.createItem("product003", "Product C", 8.0, 1);
    CartItem item4 = customerManager.createItem("product004", "Product D", 100.0, 2);

    customerManager.addItemToCart(&cart1a, item1);

    for(auto item : cart1a.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }


    customerManager.addItemToCart(&cart1b, item2);

    for(auto item : cart1b.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }


    customerManager.addItemToCart(&cart2, item3);

    for(auto item : cart2.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }


    customerManager.addItemToCart(&cart3, item4);

    for(auto item : cart3.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }



    customerManager.addShoppingCartToCustomer(&person1, &cart1a);
    customerManager.addShoppingCartToCustomer(&person1, &cart1b);
    customerManager.addShoppingCartToCustomer(&person2, &cart2);
    customerManager.addShoppingCartToCustomer(&person3, &cart3);

    cout << "\n\n\n\n\n\n<<--------------- Complete customer information ---------------------->>\n\n\n";
    cout << "----------------------------------------------------------------------\n";
    customerManager.displayAllCustomerInformation();

    cout << "\n\n\n";

    cout << "Removing few items for testing ....\n\n";
    customerManager.removeItemFromCart(&cart1b, item2.getProductID());
    cout << "Removed item3 from cart2\n";
    customerManager.removeItemFromCart(&cart3, item4.getProductID());
    cout << "Removed item4 from cart3\n";
    cout << "Removal complete\n";

    cout << "\n\n\n\n\n\n<<--------------- Complete customer information ---------------------->>\n\n\n";
    cout << "----------------------------------------------------------------------\n";
    customerManager.displayAllCustomerInformation();
}
