package com.ecommerce.orders.infraestructure.out.jpa.adapter;

import com.ecommerce.orders.domain.spi.IStockPersistencePort;
import com.ecommerce.orders.infraestructure.out.jpa.entity.ProductStockEntity;
import com.ecommerce.orders.infraestructure.out.jpa.repository.IProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockJpaAdapter implements IStockPersistencePort {

    private final IProductStockRepository stockRepository;

    /**
     * Descuenta 'qty' del stock del producto. Lanza IllegalStateException si no alcanza.
     * Pequeño retry para colisiones optimistas.
     */
    @Override
    @Transactional
    public void decrease(Long productId, int qty) {
        int maxRetries = 3;
        int attempt = 0;
        while (true) {
            try {
                ProductStockEntity s = stockRepository.findById(productId)
                        .orElseThrow(() -> new IllegalArgumentException("Producto sin stock configurado: " + productId));

                if (s.getAvailableQty() < qty) {
                    throw new IllegalStateException("Stock insuficiente para productId=" + productId);
                }

                s.setAvailableQty(s.getAvailableQty() - qty);
                // @Version se incrementa solo al flush/commit
                stockRepository.saveAndFlush(s);
                return;
            } catch (ObjectOptimisticLockingFailureException ex) {
                if (++attempt > maxRetries) {
                    throw new IllegalStateException("Conflicto de concurrencia en stock para productId=" + productId);
                }
                // pequeño backoff opcional
                try { Thread.sleep(30L * attempt); } catch (InterruptedException ignored) {}
            }
        }
    }
}
