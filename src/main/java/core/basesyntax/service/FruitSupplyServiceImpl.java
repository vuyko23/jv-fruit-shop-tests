package core.basesyntax.service;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.service.interfaces.FruitOperationService;
import java.util.Optional;

public class FruitSupplyServiceImpl implements FruitOperationService {
    private FruitDao fruitDao;

    public FruitSupplyServiceImpl(FruitDao fruitDao) {
        this.fruitDao = fruitDao;
    }

    @Override
    public void apply(TransactionDto dto) {
        Optional<Integer> currentQuantity = fruitDao.get(dto.getFruit());
        fruitDao.put(dto.getFruit(), currentQuantity.orElse(0)
                + dto.getQuantity());
    }
}