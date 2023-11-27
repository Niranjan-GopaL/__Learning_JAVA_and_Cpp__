#include <vector>
#include <iostream>
#include <string>
#include <sstream>

using namespace std;


# define ll long long int



class Cart_item{

private:
    int price;
    int quantity;
    string name;


public:
    Cart_item(string name, int price, int quantity){
        this->name     = name;
        this->price    = price;
        this->quantity = quantity;
    }

    // getters and setters
    string& get_name()  {return this->name;    }
    int& get_Price()    {return this->price;   }
    int& get_Quantity() {return this->quantity;}

    void set_Name(string new_name)      { this->name = new_name;        }
    void set_Price(int new_price)       { this->price = new_price;      }
    void set_Quantity(int new_quantity) { this->quantity = new_quantity;}

};


class Shopping_cart{

private:
    int cart_ID;
    vector<Cart_item> list_of_items;

public:

    // default constructor
    Shopping_cart(){this->cart_ID = -100000;}
    Shopping_cart(int cart_ID){this->cart_ID = cart_ID;}


    int& get_cart_ID()                     { return this->cart_ID; }
    vector<Cart_item>& get_list_of_items() { return this->list_of_items;  }

    void get_cart_ID(int new_cart_ID){ 
        this->cart_ID = new_cart_ID; 
    }

    void get_list_of_items(vector<Cart_item>& new_list_of_items) { 
        this->list_of_items = new_list_of_items;  
    }



    void add_item(string name, int price, int quantity) {

        vector<Cart_item>& list_of_items__ = this->get_list_of_items();

        for (ll i = 0; i < list_of_items__.size(); i++){
            Cart_item& item = list_of_items__[i];
            if (item.get_name() == name){
                int& quantity_in_obj = item.get_Quantity();
                quantity_in_obj += quantity;
                return;
            }}

        Cart_item new_item(name, price, quantity);
        list_of_items__.push_back(new_item);
    }



    void remove_item(string name, int quantity){

        vector<Cart_item>& list_of_items = this->get_list_of_items();

        for (ll i = 0; i < list_of_items.size(); i++){
            Cart_item &item = list_of_items[i];

            if (item.get_name() == name){
                int &new_price = item.get_Quantity();
                new_price -= quantity;
                return;
    }}}



    int price_of_cart(){

        int amount = 0;
        vector<Cart_item>& list_of_items = this->get_list_of_items();

        for (Cart_item& item : list_of_items)
            amount += item.get_Price() * item.get_Quantity();
        
        return amount;
    }
};




class Customer{

private:
    int CustomerID;
    vector<Shopping_cart> list_of_carts;

public:

    Customer(int CustomerID) { this->CustomerID = CustomerID; }


    vector<Shopping_cart>& get_list_of_shopping_carts(){
        return this->list_of_carts;
    }    
    
    void set_list_of_shopping_carts(vector<Shopping_cart>& new_list){
        this->list_of_carts = new_list;
    }


    int& getCustomerID() { return this->CustomerID; }
    void setCustomerID(int new_customerID) { this->CustomerID = new_customerID; }

};




class Customer_manager{

private:
    vector<Customer> list_of_Customers;

public:

    Customer_manager(){}

    vector<Customer> &getListofCustomers(){
        return this->list_of_Customers;
    }

    void addCustomer(int CustomerID){
        vector<Customer>& list_of_customers = this->list_of_Customers;
        Customer new_customer(CustomerID);
        list_of_customers.push_back(new_customer);
    }




    Shopping_cart &get_shopping_cart(int CustomerID, int cart_ID){

        for (Customer &customer_in : this->list_of_Customers){

            if (customer_in.getCustomerID() == CustomerID){

                vector<Shopping_cart>& carts = customer_in.get_list_of_shopping_carts();

                for (Shopping_cart& that_cart : carts)
                    if (that_cart.get_cart_ID() == cart_ID)
                        return that_cart;
                    
                
                // If the cart_ID was not found, create a new one and return it.
                carts.emplace_back(Shopping_cart(cart_ID));
                return carts.back();

        }}

        // If the CustomerID was not found, create a new customer with an empty cart and return it.
        this->list_of_Customers.emplace_back(Customer(CustomerID));
        return this->list_of_Customers.back().get_list_of_shopping_carts().back();
    }



    void addCart(int CustomerID, int cart_ID){

        for (Customer& customer : this->list_of_Customers){
            if (customer.getCustomerID() == CustomerID){

                vector<Shopping_cart>& carts = customer.get_list_of_shopping_carts();
                Shopping_cart new_cart = Shopping_cart(cart_ID);
                carts.push_back(new_cart);
                break;
        }}
    }



    int price_of_all_carts(int CustomerID){

        int total = 0;
        for (Customer& customer : this->list_of_Customers){
            if (customer.getCustomerID() == CustomerID){
                vector<Shopping_cart>& carts = customer.get_list_of_shopping_carts();

                for (Shopping_cart& cart : carts)
                    total += cart.price_of_cart();
        }}

        return total;
    }

};




int main(){

    Customer_manager customer_manager;
    
    int num_customers, num_carts,num_operations ;
    vector<int> output;
    vector<int> list_of_carts;


    cin >> num_customers;

    for (int idx = 0; idx < num_customers; idx++){
        customer_manager.addCustomer(idx + 1);
        cin >> num_carts;
        list_of_carts.push_back(num_carts);
    }

    int count = 1;
    for (int x : list_of_carts){
        for (int i = 0; i < x; i++)
            customer_manager.addCart(count, i);

        count ++;
    }


    cin >> num_operations;
    cin.ignore();


    for (int i = 0; i < num_operations; i++){



        // Converting String to tokens based on delimiter
        string input;getline(cin, input);
        istringstream ss(input);
        string token;

        vector<string> tokens;       
        while (getline(ss, token, ' '))
            tokens.push_back(token);



        if (tokens[1] == "total"){


            int customer_ID = stoi(tokens[0]);
            int amount = customer_manager.price_of_all_carts(customer_ID);
            output.push_back(amount);


        }else if (tokens[2] == "add"){


            int customer_ID = stoi(tokens[0]);
            int cart_ID     = stoi(tokens[1]);
            int price       = stoi(tokens[4]);
            int quantity    = stoi(tokens[5]);
            string name     = tokens[3];

            Shopping_cart& cart = customer_manager.get_shopping_cart(customer_ID, cart_ID);
            cart.add_item(name, price, quantity);


        }else if (tokens[2] == "remove"){


            int customer_ID = stoi(tokens[0]);
            int cart_ID     = stoi(tokens[1]);
            int quantity    = stoi(tokens[4]);
            string name     = tokens[3];

            Shopping_cart& cart = customer_manager.get_shopping_cart(customer_ID, cart_ID);
            cart.remove_item(name, quantity);


        }else if (tokens[2] == "total"){


            int customer_ID = stoi(tokens[0]);
            int cart_ID     = stoi(tokens[1]);

            Shopping_cart &cart = customer_manager.get_shopping_cart(customer_ID, cart_ID);
            int amount = cart.price_of_cart();
            output.push_back(amount);
        }
    }


    for (ll i = 0; i < output.size(); i++)
        cout << output[i] << endl;


    return 0;
}



