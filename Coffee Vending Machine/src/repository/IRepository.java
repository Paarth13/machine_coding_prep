package repository;

import models.Coffee;

public interface IRepository {
    public boolean checkForItems(Coffee coffee);
    public void updateItems(Coffee coffee);
}
