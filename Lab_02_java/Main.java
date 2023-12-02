import java.util.Scanner;



/* Sir's input. You need to cut and paste this in terminal after running the running the program

============ copy from next line ============
3
Laptop 
799.99
Smartphone 

Headphones
200
=============================================
 */



class Product {

    private static int nextProductId = 1;
    int productId;
    String productName;
    double price;
    
    
    public Product(){
        productId = 1;
        productName = "NameNotGiven";
        price = 0.0;
    }
    
    public Product(String givenProductName, double givenPrice){
        this.productId += nextProductId ;
        nextProductId ++;
        this.productName = givenProductName;
        this.price = givenPrice;
    }


    public Product(String givenProductName){
        this.productName = givenProductName;
    }


    public void displayProduct(){
        System.out.println("Product Details:");
        System.out.println("Product ID   :"+this.productId);
        System.out.println("Product Name :"+this.productName);
        System.out.println("Price        :$"+this.price);
        System.out.println();
    }



}




public class Main{
        
        public static void main(String[] args) {
            Scanner sc  = new Scanner(System.in); 
            
            String numberOfProduct_string = sc.nextLine();
            int numberOfProduct = Integer.parseInt(numberOfProduct_string);
            
            
            Product productArray[] = new Product[numberOfProduct];


            for (int index = 0; index < numberOfProduct; index++){
                String name = sc .nextLine();

                String price_string = sc.nextLine();
                double price;
                if (price_string.isEmpty()) {
                    price = 0.0;
                }else{
                    price = Double.parseDouble(price_string);
                }


                Product product = new Product(name,price);
                productArray[index] = product;
            }
            

            for (int i = 0; i < productArray.length; i++) 
                productArray[i].displayProduct();
            
            sc.close();
        }
}











