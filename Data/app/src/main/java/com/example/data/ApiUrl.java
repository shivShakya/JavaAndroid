package com.example.data;

public class ApiUrl {

    private String registerUrl = "https://android-shivshakya.vercel.app/register";
    private String productUrl =  "https://android-shivshakya.vercel.app/products";
    private String loginUrl =  "https://android-shivshakya.vercel.app/login";


   // getter & setter for register
    public String getRegisterUrl(){
        return registerUrl ;
    }
    public void setRegisterUrl(String registerUrl){
             this.registerUrl = registerUrl;
    }

    // getter & setter for product
    public String getProductUrl (){
        return productUrl;
    }
    public void setProductUrl(String productUrl){
       this.productUrl = productUrl;
    }

    //getter & setter for login
    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
