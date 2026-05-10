package Siemens.Internship.JavaTrainApp.service;

import Siemens.Internship.JavaTrainApp.model.BiletEntity;
import Siemens.Internship.JavaTrainApp.model.TrenEntity;
import Siemens.Internship.JavaTrainApp.repository.BiletRepository;
import Siemens.Internship.JavaTrainApp.repository.GaraRepository;
import Siemens.Internship.JavaTrainApp.repository.TrenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketingService {
    private final BiletRepository biletRepo;
    private final TrenRepository trenRepo;
    private final GaraRepository garaRepo;
    private final EmailService emailService;

    public TicketingService(BiletRepository biletRepo, TrenRepository trenRepo, GaraRepository garaRepo, EmailService emailService) {
        this.biletRepo = biletRepo;
        this.trenRepo = trenRepo;
        this.garaRepo = garaRepo;
        this.emailService = emailService;
    }

    public String bookTicket(int nrTren, int idPlecare, int idDestinatie, String email, LocalDateTime data) {
        TrenEntity tren = trenRepo.findById(nrTren).orElseThrow(() -> new RuntimeException("Tren inexistent"));

        LocalDateTime startOfDay = data.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = data.toLocalDate().atTime(23, 59, 59);
        int bileteVandute = biletRepo.countByTrenNumarTrenAndDataCalatorieiBetween(nrTren, startOfDay, endOfDay);

        if (bileteVandute >= tren.getCapacitate()) {
            return "Eroare: Trenul este fully booked (Overbooking prevenit).";
        }

        BiletEntity bilet = new BiletEntity();
        bilet.setTren(tren);
        bilet.setGaraPlecare(garaRepo.findById(idPlecare).get());
        bilet.setGaraDestinatie(garaRepo.findById(idDestinatie).get());
        bilet.setEmailClient(email);
        bilet.setDataCalatoriei(data);
        bilet.setLoc(bileteVandute + 1);
        bilet.setVagon(1);
        biletRepo.save(bilet);

        emailService.sendEmail(email, "Confirmare Rezervare", "Biletul tau la trenul " + nrTren + " a fost confirmat!");
        return "Bilet rezervat cu succes! Locul: " + bilet.getLoc();
    }

    public void reportDelay(int numarTren, int minuteIntarziere) {
        List<BiletEntity> bilete = biletRepo.findByTrenNumarTren(numarTren);
        for (BiletEntity bilet : bilete) {
            if (bilet.getDataCalatoriei().isAfter(LocalDateTime.now()) && bilet.getEmailClient() != null) {
                emailService.sendEmail(bilet.getEmailClient(), "Notificare Întârziere Tren",
                        "Atenție! Trenul " + numarTren + " are o întârziere estimată de " + minuteIntarziere + " minute.");
            }
        }
    }
}