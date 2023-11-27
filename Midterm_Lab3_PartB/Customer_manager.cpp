#include <iostream>
#include <vector>
#include <sstream>
#include <limits>
#include <string>

using namespace std;

#define ll long long int


class Cart_item {

private:
    int    product_id;
    string name;
    float  price;
    int    quantity;


public:

    Cart_item(int product_id, string name, float price, int quantity) {
        this->product_id = product_id;
        this->name       = name;
        this->price      = price;
        this->quantity   = quantity;
    }

    // getters and setters
    int& get_product_id() { return this->product_id; }
    string& get_name()    { return this->name;       }
    float& get_price()    { return this->price;      }
    int& get_quantity()   { return this->quantity;   }

    void set_product_id(int product_id) { this->product_id = product_id; }
    void set_name(string name)          { this->name       = name;       }
    void set_price(float price)         { this->price      = price;      }
    void set_quantity(int quantity)     { this->quantity   = quantity;   }

};




class Shopping_cart {

private:
    vector<Cart_item> cart_items;
    int               cart_id;


public:

    Shopping_cart(int cart_id) { this->cart_id = cart_id; }


    // getters and setters
    int& get_cart_id() { return this->cart_id; }
    vector<Cart_item>& get_cart_items() { return this->cart_items; }
    void set_cart_id(int new_cart_id) { this->cart_id = new_cart_id; }


    void add_item_to_cart(Cart_item item) { cart_items.push_back(item); }


    // Implemented both ways of removal as discussed in class 
    //   -> removing from vecotr
    //   -> making the quantity = 0



    // searches for item via productID and deletes it from the cart_items vector
    void remove_item_from_cart(Cart_item item) {

        for (ll i = 0; i < cart_items.size(); i++) {

            if (cart_items[i].get_product_id() == item.get_product_id()) {
                cart_items.erase(cart_items.begin() + i);
                break;
        }}
    }


    // can reduce the quantity by whatever amount 
    void reduce_item_quantity(Cart_item item, int decrement_quantity) {

        for (ll i = 0; i < cart_items.size(); i++) {

            if (cart_items[i].get_product_id() == item.get_product_id()) {

                if (cart_items[i].get_quantity() >= decrement_quantity)
                    cart_items[i].set_quantity(cart_items[i].get_quantity() - decrement_quantity);
                else
                    cout << "ERROR" ;
                
                break;
        }}
    }


    // based on the item name we get the cart_item
    Cart_item get_cart_item(string name) {
        for (ll i = 0; i < cart_items.size(); i++) 
            if (cart_items[i].get_name() == name) 
                return cart_items[i];
            
        return Cart_item(-1, "", -1, -1);
    }



    // calculating total price
    float total_price() {
        float total = 0;
        for (ll i = 0; i < cart_items.size(); i++) 
            total += cart_items[i].get_price() *cart_items[i].get_quantity();
        
        return total;
    }
};




class Customer {

private:
    vector<Shopping_cart> shopping_carts;
    int                   customer_id;


public:

    Customer(int customer_id) { this->customer_id = customer_id; }


    vector<Shopping_cart>& get_shopping_carts() { return this->shopping_carts; }
    int& get_customer_id()                      { return this->customer_id;    }

    void set_customer_id(int new_customer_id)                 { this->customer_id = new_customer_id; }
    void set_shopping_carts(vector<Shopping_cart>& new_carts) { this->shopping_carts = new_carts; }

    void add_shopping_cart(Shopping_cart shopping_cart) { shopping_carts.push_back(shopping_cart); }
};




class Customer_manager {

private:
    vector<Customer> customers;


public:

    Customer_manager() {}


    void add_customer(Customer customer)    { customers.push_back(customer); }

    void remove_customer(Customer customer) {
        for (ll i = 0; i < customers.size(); i++) {
            if (customers[i].get_customer_id() == customer.get_customer_id()) {
                customers.erase(customers.begin() + i);
                break;
        }}
    }

    // get customer object 
    Customer& get_customer(int customer_id) {
        for (ll i = 0; i < customers.size(); i++) {
            if (customers[i].get_customer_id() == customer_id) {
                return customers[i];
            }
        }
        return customers[0];
    }


    // add a cart to the vector shopping_carts of customer object
    void add_shopping_cart_to_customer(int customer_id, Shopping_cart shopping_cart) {
        for (ll i = 0; i < customers.size(); i++) {
            if (customers[i].get_customer_id() == customer_id) {
                customers[i].add_shopping_cart(shopping_cart);
        }}
    }

    
    // get shopping_cart object
    int get_shopping_cart(int customer_id, int cart_id) {
        for (ll i = 0; i < customers.size(); i++) 
            if (customers[i].get_customer_id() == customer_id) 
                for (int j = 0; j < customers[i].get_shopping_carts().size(); j++) 
                    if (customers[i].get_shopping_carts()[j].get_cart_id() == cart_id) 
                        return customers[i].get_shopping_carts()[j].total_price();
        return -1;
    }


    // total calue of a customer's cart is calculated
    float total_value_of_customer_carts(int customer_id) {
        float total = 0;
        for (ll i = 0; i < customers.size(); i++) 
            if (customers[i].get_customer_id() == customer_id) 
                for (ll j = 0; j < customers[i].get_shopping_carts().size(); j++) 
                    total += customers[i].get_shopping_carts()[j].total_price();

        return total;
    }
};




vector<string> split(string line) {
    
    vector<string> list;
    string token;
    stringstream stream_object(line);

    while (stream_object >> token) 
        list.push_back(token);
    

    return list;
}


/*
    HOW CODE USES COMPOSITION
     -> Customer owns Shopping_cart instances, demonstrating a composition relationship where the 
        existence of shopping carts is tied to that of customers.


    HOW CODE USES AGGREGATION
     -> Customer_manager aggregates Customer objects, showcasing an aggregation relationship where 
        customers can exist independently of the manager.
*/




int main() {
    
    vector<int> result;

    int num_carts_per_customer, num_customers, num_operations;
    static int product_ID = 1;


    cin >> num_customers;   
    vector<int> number_of_shopping_carts;
    for (ll i = 0; i < num_customers; i++) {
        cin >> num_carts_per_customer;
        number_of_shopping_carts.push_back(num_carts_per_customer);
    }



    Customer_manager customer_manager;

    cin >> num_operations;
    for (ll customer_ID = 1; customer_ID <= num_customers; customer_ID++) {
        Customer customer(customer_ID);
        customer_manager.add_customer(customer);
    }


    for (ll customer_ID = 1; customer_ID <= num_customers; customer_ID++) {
        for (int cart_ID = 1; cart_ID <= number_of_shopping_carts[customer_ID - 1]; cart_ID++) {
            Shopping_cart shopping_cart(cart_ID);
            customer_manager.add_shopping_cart_to_customer(customer_ID, shopping_cart);
    }}



    while (num_operations--) {

        string line;
        getline(cin >> ws, line);

        vector<string> token;
        token = split(line);


        // adding an item
        if (token.size() == 6) {

            int customer_id = stoi(token[0]);
            int cart_id     = stoi(token[1]);
            float price     = stof(token[4]);
            int quantity    = stoi(token[5]);
            string name     = token[3];

            int product_id = product_ID++;

            Cart_item cart_item(product_id, name, price, quantity);
            customer_manager.get_customer(customer_id).get_shopping_carts()[cart_id - 1].add_item_to_cart(cart_item);


        // removing an item ( making the quantity = 0 )
        } else if (token.size() == 5) {

            int customer_id = stoi(token[0]);
            int cart_id     = stoi(token[1]);
            int quantity    = stoi(token[4]);
            string name     = token[3];

            customer_manager.get_customer(customer_id).get_shopping_carts()[cart_id - 1].reduce_item_quantity(customer_manager.get_customer(customer_id).get_shopping_carts()[cart_id - 1].get_cart_item(name), quantity);



        // calculating total cost of all the carts of a customer
        } else if (token.size() == 2) {

            int num = customer_manager.total_value_of_customer_carts(stoi(token[0]));
            result.push_back(num);



        // calculation total cost of a cart
        } else if (token.size() == 3) {

            int num = customer_manager.get_shopping_cart(stoi(token[0]), stoi(token[1]));
            result.push_back(num);


        }}


    for (ll i = 0; i < result.size(); i++) 
        cout << result[i] << endl;
    

    return 0;




}








