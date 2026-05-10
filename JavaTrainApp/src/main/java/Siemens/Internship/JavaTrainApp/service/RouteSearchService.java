package Siemens.Internship.JavaTrainApp.service;

import Siemens.Internship.JavaTrainApp.model.OprireEntity;
import Siemens.Internship.JavaTrainApp.repository.OprireRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteSearchService {
    private final OprireRepository oprireRepo;

    public RouteSearchService(OprireRepository oprireRepo) {
        this.oprireRepo = oprireRepo;
    }

    public List<String> findRoutes(int idGaraPlecare, int idGaraSosire) {
        List<String> routesFound = new ArrayList<>();
        List<OprireEntity> opririPlecare = oprireRepo.findByGaraIdGara(idGaraPlecare);
        List<OprireEntity> opririSosire = oprireRepo.findByGaraIdGara(idGaraSosire);

        for (OprireEntity oPlecare : opririPlecare) {
            for (OprireEntity oSosire : opririSosire) {
                if (oPlecare.getTren().getNumarTren() == oSosire.getTren().getNumarTren()) {
                    if (oPlecare.getOraPlecare().isBefore(oSosire.getOraSosire())) {
                        routesFound.add("Ruta DIRECTĂ: Tren " + oPlecare.getTren().getNumarTren() +
                                " | Plecare: " + oPlecare.getOraPlecare() + " -> Sosire: " + oSosire.getOraSosire());
                    }
                }
            }
        }

        for (OprireEntity o1 : opririPlecare) {
            List<OprireEntity> traseuTren1 = oprireRepo.findByTrenNumarTrenOrderByOraSosireAsc(o1.getTren().getNumarTren());
            for (OprireEntity schimb : traseuTren1) {
                if (schimb.getOraSosire() != null && schimb.getOraSosire().isAfter(o1.getOraPlecare())) {
                    List<OprireEntity> conexiuni = oprireRepo.findByGaraIdGara(schimb.getGara().getIdGara());
                    for (OprireEntity o2 : conexiuni) {
                        if (o2.getTren().getNumarTren() != o1.getTren().getNumarTren() && o2.getOraPlecare().isAfter(schimb.getOraSosire())) {
                            for (OprireEntity dest : opririSosire) {
                                if (dest.getTren().getNumarTren() == o2.getTren().getNumarTren() && dest.getOraSosire().isAfter(o2.getOraPlecare())) {
                                    routesFound.add("Ruta CU SCHIMBARE în " + schimb.getGara().getNumeGara() + ": Tren " + o1.getTren().getNumarTren() +
                                            " (Ajunge la " + schimb.getOraSosire() + ") -> Tren " + o2.getTren().getNumarTren() + " (Pleacă la " + o2.getOraPlecare() + ")");
                                }
                            }
                        }
                    }
                }
            }
        }

        if (routesFound.isEmpty()) {
            routesFound.add("Nicio legătură posibilă între aceste stații.");
        }
        return routesFound;
    }
}