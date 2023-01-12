package antessio.dddmodulith.ecommerce.product;

import java.util.List;
import java.util.UUID;

 class ProductEvent {
    private  String productId;
    private  Long priceAmountUnit;
    private  String description;
    private  Long stocks;
    private  Integer rate;
    private  List<String> images;

     public ProductEvent() {
     }

     public ProductEvent(String productId, Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
         this.productId = productId;
         this.priceAmountUnit = priceAmountUnit;
         this.description = description;
         this.stocks = stocks;
         this.rate = rate;
         this.images = images;
     }

     public String getProductId() {
         return productId;
     }

     public Long getPriceAmountUnit() {
         return priceAmountUnit;
     }

     public String getDescription() {
         return description;
     }

     public Long getStocks() {
         return stocks;
     }

     public Integer getRate() {
         return rate;
     }

     public List<String> getImages() {
         return images;
     }

 }
