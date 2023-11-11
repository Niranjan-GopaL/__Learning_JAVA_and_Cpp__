#include <iostream>
#include <map>
#include <vector>

using namespace std;


class CartItem {
private:
    string productID;
    string name;
    double price;
    int quantity;

public:

    CartItem() {}
    CartItem(string productID, string name, double price, int quantity)
        : productID(productID), name(name), price(price), quantity(quantity) {}

    // getters and setters
    string getProductID() { return productID; }
    string getName() { return name; }
    double getPrice() { return price; }
    int getQuantity() { return quantity; }
};


class ShoppingCart {
private:
    map<string, CartItem> items;
    string cartId;

public:
    ShoppingCart() {}
    ShoppingCart(string cartId) : cartId(cartId) {}

    // getters and setters
    string getCartId() { return cartId; }

    vector<CartItem> getCartItems() {
        vector<CartItem> cartItems;
        for (auto& item : items) {
            cartItems.push_back(item.second);
        }
        return cartItems;
    }

    void addItem(CartItem item) { items[item.getProductID()] = item; }
    void removeItem(CartItem item) { items.erase(item.getProductID()); }

    double calculateTotalPriceOfCart() {
        double total = 0.0;
        for (auto& item : items) {
            total += item.second.getPrice() * item.second.getQuantity();
        }
        return total;
    }
};


class Customer {
private:
    string customerID;
    map<string, ShoppingCart> shoppingCarts;

public:
    Customer() {}
    Customer(string customerID) : customerID(customerID) {}

    // getters and setters
    string getCustomerId() { return customerID; }

    vector<ShoppingCart> getCustomerShoppingCart() {
        vector<ShoppingCart> customerCarts;
        for (auto& cart : shoppingCarts) {
            customerCarts.push_back(cart.second);
        }
        return customerCarts;
    }

    void addShoppingCart(ShoppingCart cart) {
        shoppingCarts[cart.getCartId()] = cart;
    }

    ShoppingCart getShoppingCart(string cartID) {
        auto it = shoppingCarts.find(cartID);
        if (it != shoppingCarts.end()) {
            return it->second;
        }
        return ShoppingCart(cartID);
    }
};

class CustomerManager {
private:
    map<string, Customer> customers;

public:


    Customer createCustomer(string customerID) {
        Customer customer(customerID);
        customers[customerID] = customer;
        return customer;
    }

    ShoppingCart createShoppingCart(string cartID) {
        return ShoppingCart(cartID);
    }

    CartItem createItem(string productID, string productName, double price, int quantity) {
        return CartItem(productID, productName, price, quantity);
    }

    void addShoppingCartToCustomer(Customer& customer, ShoppingCart cart) {
        customer.addShoppingCart(cart);
    }

    double calculateTotalValue(string customerID) {
        auto it = customers.find(customerID);
        if (it != customers.end()) {
            double totalValue = 0.0;
            for (ShoppingCart& cart : it->second.getCustomerShoppingCart()) {
                totalValue += cart.calculateTotalPriceOfCart();
            }
            return totalValue;
        }
        return 0.0;
    }

    void addItemToCart(ShoppingCart& cart, CartItem item) {
        cart.addItem(item);
    }

    void removeItemFromCart(ShoppingCart& cart, CartItem item) {
        cart.removeItem(item);
    }

    void displayAllCustomerInformation() {
        for (auto& customer : customers) {
            cout << "Customer : " << customer.second.getCustomerId() << endl;
            cout << "All Shopping carts :-" << endl;

            double sumOfAllCart = 0.0;

            for (ShoppingCart& cart : customer.second.getCustomerShoppingCart()) {
                cout << "Shopping cart : " << cart.getCartId() << endl;
                for (CartItem& item : cart.getCartItems()) {
                    cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                              << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
                }
                double priceOfCart = cart.calculateTotalPriceOfCart();
                sumOfAllCart += priceOfCart;
                cout << "Total cost : " << priceOfCart << endl;
            }

            cout << "\nTotal cost of all the carts of Customer " << customer.second.getCustomerId() << " :- " << sumOfAllCart << "\n\n\n";
        }
    }
};

int main() {
    // Entire customer interface is handled via this one Class
    CustomerManager customerManager;

    Customer person1 = customerManager.createCustomer("customer101");
    Customer person2 = customerManager.createCustomer("customer102");
    Customer person3 = customerManager.createCustomer("customer103");

    ShoppingCart cart1a = customerManager.createShoppingCart("cart101a");
    ShoppingCart cart1b = customerManager.createShoppingCart("cart101b");
    ShoppingCart cart2 = customerManager.createShoppingCart("cart102");
    ShoppingCart cart3 = customerManager.createShoppingCart("cart103");

    customerManager.addShoppingCartToCustomer(person1, cart1a);
    customerManager.addShoppingCartToCustomer(person1, cart1b);
    customerManager.addShoppingCartToCustomer(person2, cart2);
    customerManager.addShoppingCartToCustomer(person3, cart3);

    CartItem item1 = customerManager.createItem("product001", "Product A", 10.0, 2);
    CartItem item2 = customerManager.createItem("product002", "Product B", 5.0, 3);
    CartItem item3 = customerManager.createItem("product003", "Product C", 8.0, 1);
    CartItem item4 = customerManager.createItem("product004", "Product D", 100.0, 2);

    customerManager.addItemToCart(cart1a, item1);

    for(auto item : cart1a.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }

    customerManager.addItemToCart(cart1b, item2);
    for(auto item : cart1b.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }

    customerManager.addItemToCart(cart2, item3);
    for(auto item : cart2.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }

    customerManager.addItemToCart(cart3, item4);
    for(auto item : cart3.getCartItems()){
        cout << "productID: " << item.getProductID() << ", name: " << item.getName()
                    << ", price: " << item.getPrice() << " Quantity: " << item.getQuantity() << endl;
    }


    cout << "Complete customer information -------------------------------------" << endl;
    cout << "-------------------------------------------------------------------" << endl;
    customerManager.displayAllCustomerInformation();

    cout << "Removing few items for testing ...." << endl << endl;
    customerManager.removeItemFromCart(cart2, item3);
    cout << "Removed item3 from cart2" << endl;
    customerManager.removeItemFromCart(cart3, item4);
    cout << "Removed item4 from cart3" << endl;
    cout << "Removal complete" << endl;

    cout << "\n\n\n\nComplete customer information -------------------------------------" << endl;
    cout << "-------------------------------------------------------------------" << endl;
    customerManager.displayAllCustomerInformation();

    return 0;
}
