#include <vector>
#include <string>
#include <iostream>
#include <sstream>
#include <limits>

using namespace std;




class CartItem 
{
    private:
        int productID;
        string name;
        float price;
        int quantity;

    public:
        CartItem(int productID, string name, float price, int quantity) 
        {
            this->productID = productID;
            this->name = name;
            this->price = price;
            this->quantity = quantity;
        }

        // GETTERS

        int getProductId()
        {
            return this->productID;
        }

        string getName() 
        {
            return this->name;
        }

        float getPrice() 
        {
            return this->price;
        }

        int getQuantity() 
        {
            return this->quantity;
        }

        // SETTERS

        void setProductID(int productid) 
        {
            this->productID = productid;
        }

        void setName(string name) 
        {
            this->name = name;
        }

        void setPrice(float price) {
            this->price = price;
        }

        void setQuantity(int quantity) 
        {
            this->quantity = quantity;
        }
};






class ShoppingCart 
{
    private:
        int cartID;
        vector<CartItem> cartItems;

    public:
        ShoppingCart(int cartID) 
        {
            this->cartID = cartID;
        }

        int getCartID() 
        {
            return this->cartID;
        }

        void setCartID(int cartID)
        {
            this->cartID = cartID;
        }

        vector<CartItem>& getCartItems() 
        {
            return this->cartItems;
        }

    // METHODS

    void addItemToCart(CartItem item) 
    {
        cartItems.push_back(item);
    }

    void removeItemFromCart(CartItem item) 
    {
        for (int i = 0; i < cartItems.size(); i++) 
        {
            if (cartItems[i].getProductId() == item.getProductId())
            {
                cartItems.erase(cartItems.begin() + i);
                break;
            }
        }
    }

    void reduceItemQuantity(CartItem item , int quantity) 
    {
        for (int i = 0; i < cartItems.size(); i++) 
        {
            if (cartItems[i].getProductId() == item.getProductId())
            {
                cartItems[i].setQuantity(cartItems[i].getQuantity() - quantity);
                break;
            }
        }
    }

    CartItem getCartItem(string name) 
    {
        for (int i = 0; i < cartItems.size(); i++) 
        {
            if (cartItems[i].getName() == name) 
            {
                return cartItems[i];
            }
        }
        return CartItem(-1, "", -1, -1);
    }

    float totalPrice() 
    {
        float total = 0;
        for (int i = 0; i < cartItems.size(); i++) 
        {
            total += cartItems[i].getPrice() * cartItems[i].getQuantity();
        }
        return total;
    }
};






class Customer
{
    private:
        int customerID;
        vector<ShoppingCart> shoppingCarts;

    public:
        Customer(int customerID) 
        {
            this->customerID = customerID;
        }

        int getCustomerID() 
        {
            return this->customerID;
        }

        void setCustomerID(int customerID) 
        {
            this->customerID = customerID;
        }

        vector<ShoppingCart>& getShoppingCarts()
        {
            return this->shoppingCarts;
        }

        void addShoppingCart(ShoppingCart shoppingCart) 
        {
            shoppingCarts.push_back(shoppingCart);
        }
};



class CustomerManager 
{
    private:
        vector<Customer> customers;

    public:
        void addCustomer(Customer customer) 
        {
            customers.push_back(customer);
        }

        void removeCustomer(Customer customer) 
        {
            for(int i = 0; i < customers.size(); i++) 
            {
                if(customers[i].getCustomerID() == customer.getCustomerID()) 
                {
                    customers.erase(customers.begin() + i);
                    break;
                }
            }
        }

        Customer& getCustomer(int customerID) 
        {
            for (int i = 0; i < customers.size(); i++)
            {
                if (customers[i].getCustomerID() == customerID) 
                {
                    return customers[i];
                }
            }
            return customers[0];
        }

        void addShoppingCartToCustomer(int customerID, ShoppingCart shoppingCart) 
        {
            for (int i = 0; i < customers.size(); i++) 
            {
                if (customers[i].getCustomerID() == customerID) 
                {
                    customers[i].addShoppingCart(shoppingCart);
                }
            }
        }


        int retrieveShoppingCart(int customerID, int cartID){
            for (int i = 0; i < customers.size(); i++) 
                if (customers[i].getCustomerID() == customerID)
                    for (int j = 0; j < customers[i].getShoppingCarts().size(); j++) 
                        if (customers[i].getShoppingCarts()[j].getCartID() == cartID) 
                            return customers[i].getShoppingCarts()[j].totalPrice();

            return -1;
        }


        int totalValueOfCustomerCarts(int customerID) 
        {
            float total = 0;
            for (int i = 0; i < customers.size(); i++) 
            {
                if (customers[i].getCustomerID() == customerID) 
                {
                    for (int j = 0; j < customers[i].getShoppingCarts().size(); j++) 
                    {
                        total += customers[i].getShoppingCarts()[j].totalPrice();
                    }
                }
            }
            return total;
        }
};


vector<string> simple_tokenizer(string s) 
{
    vector<string> queryWords;
    stringstream ss(s);
    string word;

    while (ss >> word) 
    {
        queryWords.push_back(word);
    }

    return queryWords;
}






int main() 
{

    vector<int>result;

    int n;
    cin >> n;

    vector<int> numberOfShoppingCarts;
    for (int i = 0; i < n; i++) 
    {
        int x;
        cin >> x;
        numberOfShoppingCarts.push_back(x);
    }

    int queries;
    cin >> queries;

    CustomerManager customerManager;

    for (int i = 1; i <= n; i++) 
    {
        Customer customer(i);
        customerManager.addCustomer(customer);
    }

    for (int i = 1; i <= n; i++) 
    {
        for (int j = 1; j <= numberOfShoppingCarts[i - 1]; j++) 
        {
            ShoppingCart shoppingCart(j);
            customerManager.addShoppingCartToCustomer(i, shoppingCart);
        }
    }

    while (queries--) {
        string word;
        getline(cin >> ws, word);

        vector<string> queryWords;
        queryWords = simple_tokenizer(word);

        if (queryWords.size() == 6) 
        {
            static int num = 1;
            int customerID = stoi(queryWords[0]);
            int cartID = stoi(queryWords[1]);
            int productID = num++;
            string name = queryWords[3];
            float price = stof(queryWords[4]);
            int quantity = stoi(queryWords[5]);

            CartItem cartItem(productID, name, price, quantity);
            customerManager.getCustomer(customerID).getShoppingCarts()[cartID - 1].addItemToCart(cartItem);
        } 
        
        
        else if (queryWords.size() == 5) 
        {
            int customerID = stoi(queryWords[0]);
            int cartID = stoi(queryWords[1]);
            string name = queryWords[3];
            int quantity = stoi(queryWords[4]);

            customerManager.getCustomer(customerID).getShoppingCarts()[cartID - 1].reduceItemQuantity(customerManager.getCustomer(customerID).getShoppingCarts()[cartID - 1].getCartItem(name), quantity);
        } 
        
        else if (queryWords.size() == 2)  
        {       
           int num = customerManager.totalValueOfCustomerCarts(stoi(queryWords[0]));
            result.push_back(num);
        }
        
        else if (queryWords.size() == 3) 
        {
            int num = customerManager.retrieveShoppingCart(stoi(queryWords[0]), stoi(queryWords[1]));
            result.push_back(num);
        }
    }

    for (int i = 0; i < result.size(); i++) 
        cout << result[i] << endl;

    return 0;
}

