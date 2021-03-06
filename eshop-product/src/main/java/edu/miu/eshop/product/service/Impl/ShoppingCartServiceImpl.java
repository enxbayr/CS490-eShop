package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.dto.ProductDto;
import edu.miu.eshop.product.repository.ShoppingCartRepository;
import edu.miu.eshop.product.entity.CartItem;
import edu.miu.eshop.product.entity.ShoppingCart;
import edu.miu.eshop.product.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository ;

    @Override
    public ShoppingCart createNewCart(String userName) {
        ShoppingCart cart = new ShoppingCart(userName);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart findCartForUser(String userName) {
        return shoppingCartRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public ShoppingCart findCartItem(String productid) {
        return shoppingCartRepository.findByCartItems_ProductId(productid);
    }

    @Override
    public void addCartItem(ProductDto product,  ShoppingCart cart, int quantity) {
        List<CartItem>  cartItems =  cart.getCartItems();
        boolean exists = cartItems.stream().anyMatch(i->i.getProductId().equals(product.getProductId()));
        if(exists) {
            cart.getCartItems().stream().filter(i->i.getProductId().equals(product.getProductId()))
                    .forEach(i->{
                        i.setQuantity(quantity);  });
        }else
            cart.addItem(product.getProductId(), quantity, product.getPrice(), product.getVendorId());
        shoppingCartRepository.save(cart);
    }

    @Override
    public void setProductQuantity(ShoppingCart cart, String productId, int updatedQuantity) {
        System.out.println(cart);
        cart.getCartItems().stream().filter(i->i.getProductId().equals(productId))
                                    .forEach(i->i.setQuantity(updatedQuantity));
        shoppingCartRepository.save(cart);
    }

    @Override
    public void removeItem(ShoppingCart cart, String productId) {
        cart.getCartItems().removeIf(i->i.getProductId().equals(productId));
        shoppingCartRepository.save(cart);
    }

    @Override
    public List<CartItem> getItems(String cartId) {
        return shoppingCartRepository.findById(cartId).get().getCartItems();
    }
}
