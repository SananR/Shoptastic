package me.shoptastic.app.data.model;

import android.content.Context;

import com.google.firebase.database.core.view.Change;

import java.util.ArrayList;

import me.shoptastic.app.Interface.ChangeNumberItemListener;

public class Order {
    private Context context;

    public Order(Context context){
        this.context = context;
    }

    public void insertProduct(Product product){
        ArrayList<Product> listProduct = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; i < listProduct.size(); i ++){
            if(listProduct.get(i).getId() == (product.getId())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if(!existAlready){
            listProduct.add(product);
        }
    }

    public ArrayList<Product> getListCart(){
        //Testing(demo)
        Product p1 = new Product("Pizza", "delicous", 1.0f, 1);
        Product p2 = new Product("Apple", "fresh", 1.0f, 2);
        ArrayList<Product> anOrder = new ArrayList<Product>();
        anOrder.add(p1);
        anOrder.add(p2);
        return anOrder;
    }

    public void plusNumberProduct(ArrayList<Product> listProduct, int position, ChangeNumberItemListener changeNumberItemListener){
        //tinyDB.putListObject("Object", listfood); //Adding the databse would be necessary
        listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() + 1);
        changeNumberItemListener.changed();

    }

    public void minusNumberProduct(ArrayList<Product> listProduct, int position, ChangeNumberItemListener changeNumberItemListener){
        //tinyDB.putListObject("Object", listfood); //Adding the database would be necessary
        if(listProduct.get(position).getNumberInCart() == 1){
            listProduct.remove(position);
        }
        else{
            listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() - 1);
        }
        changeNumberItemListener.changed();
    }

    public Float getSumPrice() {
        ArrayList<Product> listProduct2 = getListCart();
        Float fee = 0.0f;
        for (int i = 0; i < listProduct2.size(); i++) {
            fee = fee + (listProduct2.get(i).getPrice() * listProduct2.get(i).getNumberInCart());
        }
        return fee;
    }



    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return this.getSumPrice() == order.getSumPrice();
    }

    @Override
    public int hashCode(){
        return (int) getSumPrice().floatValue();
    }
}
