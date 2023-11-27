#include <bits/stdc++.h>
using namespace std;




class CartItem{

private:
    string name;
    int price;
    int quantity;

public:

    CartItem(string name, int price, int quantity){
        this->name = name;
        this->price = price;
        this->quantity = quantity;
    }

    string &getName(){
        return this->name;
    }

    int &getPrice()
    {
        return this->price;
    }

    int &getQuantity()
    {
        return this->quantity;
    }
};

class ShoppingCart
{
private:
    int ShoppingCartID;
    vector<CartItem> listofCartItems;

public:
    ShoppingCart()
    {
        this->ShoppingCartID = -100000;
    }

    ShoppingCart(int ShoppingCartID)
    {
        this->ShoppingCartID = ShoppingCartID;
    }

    int &getShoppingCartID()
    {
        return this->ShoppingCartID;
    }

    vector<CartItem> &getListOfCartItems()
    {
        return this->listofCartItems;
    }

    void addItem(string name, int price, int quantity)
    {
        vector<CartItem> &listofCartItems = this->getListOfCartItems();
        for (long unsigned int i = 0; i < listofCartItems.size(); i++)
        {
            CartItem &c = listofCartItems[i];
            if (c.getName() == name)
            {
                int &q = c.getQuantity();
                q += quantity;
                return;
            }
        }
        CartItem newItem(name, price, quantity);
        listofCartItems.push_back(newItem);
    }

    void removeItem(string name, int quantity)
    {
        vector<CartItem> &listofCartItems = this->getListOfCartItems();
        for (long unsigned int i = 0; i < listofCartItems.size(); i++)
        {
            CartItem &c = listofCartItems[i];
            if (c.getName() == name)
            {
                int &q = c.getQuantity();
                q -= quantity;
                return;
            }
        }
    }

    int priceOfCart()
    {
        int amount = 0;
        vector<CartItem> &listofCartItems = this->getListOfCartItems();
        for (CartItem &c : listofCartItems)
        {
            amount += c.getPrice() * c.getQuantity();
        }
        return amount;
    }
};

class Customer
{
private:
    int CustomerID;
    vector<ShoppingCart> listofShoppingCarts;

public:
    Customer(int CustomerID)
    {
        this->CustomerID = CustomerID;
    }

    int &getCustomerID()
    {
        return this->CustomerID;
    }

    vector<ShoppingCart> &getListofShoppingCarts()
    {
        return this->listofShoppingCarts;
    }
};

class CustomerManager
{
private:
    vector<Customer> listofCustomers;

public:
    CustomerManager()
    {
    }

    vector<Customer> &getListofCustomers()
    {
        return this->listofCustomers;
    }

    void addCustomer(int CustomerID)
    {
        vector<Customer> &LC = this->getListofCustomers();
        Customer C1(CustomerID);
        LC.push_back(C1);
    }

    ShoppingCart &RetrieveShoppingCart(int CustomerID, int ShoppingCartID)
{
    vector<Customer> &LC = this->getListofCustomers();

    for (Customer &c : LC)
    {
        if (c.getCustomerID() == CustomerID)
        {
            vector<ShoppingCart> &SC = c.getListofShoppingCarts();

            for (ShoppingCart &sc : SC)
            {
                if (sc.getShoppingCartID() == ShoppingCartID)
                {
                    return sc;
                }
            }

            // If the ShoppingCartID was not found, create a new one and return it.
            SC.emplace_back(ShoppingCart(ShoppingCartID));
            return SC.back();
        }
    }

    // If the CustomerID was not found, create a new customer with an empty cart and return it.
    LC.emplace_back(Customer(CustomerID));
    return LC.back().getListofShoppingCarts().back();
}


    void addCart(int CustomerID, int ShoppingCartID)
    {
        vector<Customer> &LC = this->getListofCustomers();
        for (Customer &c : LC)
        {
            if (c.getCustomerID() == CustomerID)
            {
                vector<ShoppingCart> &sc = c.getListofShoppingCarts();
                ShoppingCart newShoppingCart = ShoppingCart(ShoppingCartID);
                sc.push_back(newShoppingCart);
                break;
            }
        }
    }

    int priceOfAllCarts(int CustomerID)
    {
        //cout<<"Entering price of All carts"<<endl;
        int amount = 0;
        vector<Customer> &LC = this->getListofCustomers();
        for (Customer &c : LC)
        {
            //cout<<c.getCustomerID()<<endl;
            if (c.getCustomerID() == CustomerID)
            {
                vector<ShoppingCart> &SC = c.getListofShoppingCarts();
                for (ShoppingCart &sc : SC)
                {
                    amount += sc.priceOfCart();
                }
            }
        }
        return amount;
    }
};

int main()
{
    CustomerManager CM;
    int n;
    cin >> n;

    vector<int> OP;

    vector<int> listofShoppingCarts;

    for (int i = 0; i < n; i++)
    {
        CM.addCustomer(i + 1);
        int x;
        cin >> x;
        listofShoppingCarts.push_back(x);
    }

    int count = 1;
    for (int x : listofShoppingCarts)
    {
        for (int i = 0; i < x; i++)
        {
            CM.addCart(count, i);
        }
        count += 1;
    }

    int m;
    cin >> m;
    cin.ignore();

    for (int i = 0; i < m; i++)
    {
        string input;
        getline(cin, input);

        string operation;

        // Converting String to tokens based on delimiter

        istringstream ss(input);
        string token;
        char delimiter = ' ';
        vector<string> tokens;
        while (getline(ss, token, delimiter))
        {
            tokens.push_back(token);
        }

        // for(int i=0;i<tokens.size();i++)
        // {
        //     cout<<tokens[i]<<endl;
        // }

        if (tokens[1] == "total")
        {
            //cout<<"Entering total";
            int CustomerID = stoi(tokens[0]);
            int amount = CM.priceOfAllCarts(CustomerID);
            //cout<<amount<<endl;
            OP.push_back(amount);
        }

        else if (tokens[2] == "add")
        {
            int CustomerID = stoi(tokens[0]);
            int CartID = stoi(tokens[1]);
            string Name = tokens[3];
            int price = stoi(tokens[4]);
            int quantity = stoi(tokens[5]);

            ShoppingCart &SC = CM.RetrieveShoppingCart(CustomerID, CartID);
            SC.addItem(Name, price, quantity);
        }

        else if (tokens[2] == "remove")
        {
            int CustomerID = stoi(tokens[0]);
            int CartID = stoi(tokens[1]);
            string Name = tokens[3];
            int quantity = stoi(tokens[4]);

            ShoppingCart &SC = CM.RetrieveShoppingCart(CustomerID, CartID);
            SC.removeItem(Name, quantity);
        }


        else if (tokens[2] == "total")
        {

            int CustomerID = stoi(tokens[0]);
            int CartID = stoi(tokens[1]);
            ShoppingCart &SC = CM.RetrieveShoppingCart(CustomerID, CartID);
            int amount = SC.priceOfCart();
            //cout<<amount<<endl;
            OP.push_back(amount);
        }
    }

    for (long unsigned int i = 0; i < OP.size(); i++)
    {
        cout << OP[i] << endl;
    }

    return 0;
}
