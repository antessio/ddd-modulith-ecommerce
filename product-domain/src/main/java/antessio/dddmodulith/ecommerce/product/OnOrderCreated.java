package antessio.dddmodulith.ecommerce.product;

public interface OnOrderCreated {

    void updateStocks(UpdateStocksCommand updateStocksCommand);

}
