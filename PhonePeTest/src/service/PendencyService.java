package service;

import exceptions.HeirarchyListCannotBeEmpty;
import exceptions.InvalidKeyEntered;
import model.TrackingObject;
import repository.IPendencyRepository;

import java.util.List;

public class PendencyService implements IPendencySystemService{
    IPendencyRepository pendencyRepository;

    public PendencyService(IPendencyRepository pendencyRepository) {
        this.pendencyRepository = pendencyRepository;
    }

    @Override
    public int getCount(List<String> heirarchy) {
        System.out.println(pendencyRepository.getCount(heirarchy));
        return 0;
    }

    @Override
    public void startTracking(TrackingObject trackingObject) {
        try {
            if (trackingObject.getHeirarchy().isEmpty()) {
                throw new HeirarchyListCannotBeEmpty("Heirarchy List cannot be empty");
            }
            pendencyRepository.startTracking(trackingObject.getId(), trackingObject.getHeirarchy());
        }
        catch (HeirarchyListCannotBeEmpty e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void endTracking(int id) {
        try {
            pendencyRepository.endTracking(id);
        }
        catch (InvalidKeyEntered e) {
            System.out.println(e.getMessage());
        }
    }
}
