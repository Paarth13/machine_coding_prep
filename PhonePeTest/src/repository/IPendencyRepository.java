package repository;

import java.util.List;

public interface IPendencyRepository {
    public int getCount(List<String> heirarchy);
    void startTracking(Integer id, List<String> heirarchy);
    void endTracking(Integer id);
}