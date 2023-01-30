package antessio.dddmodulith.ecommerce.product;

import java.util.Map;

public class UpdateStocksCommand {
    private Map<String, Integer> productIdQuantity;

    public UpdateStocksCommand(Map<String, Integer> productIdQuantity) {
        this.productIdQuantity = productIdQuantity;
    }

    public Map<String, Integer> getProductIdQuantity() {
        return productIdQuantity;
    }

}
