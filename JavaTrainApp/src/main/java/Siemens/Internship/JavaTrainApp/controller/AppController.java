package Siemens.Internship.JavaTrainApp.controller;

import Siemens.Internship.JavaTrainApp.service.RouteSearchService;
import Siemens.Internship.JavaTrainApp.service.TicketingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private final TicketingService ticketingService;
    private final RouteSearchService routeSearchService;

    public AppController(TicketingService ticketingService, RouteSearchService routeSearchService) {
        this.ticketingService = ticketingService;
        this.routeSearchService = routeSearchService;
    }

    @PostMapping("/book")
    public String bookTicket(@RequestParam int nrTren, @RequestParam int idPlecare,
                             @RequestParam int idDestinatie, @RequestParam String email) {
        return ticketingService.bookTicket(nrTren, idPlecare, idDestinatie, email, LocalDateTime.now().plusDays(1));
    }

    @GetMapping("/routes")
    public List<String> getRoutes(@RequestParam int idPlecare, @RequestParam int idSosire) {
        return routeSearchService.findRoutes(idPlecare, idSosire);
    }

    @PostMapping("/admin/delay")
    public String reportDelay(@RequestParam int nrTren, @RequestParam int delayMinutes) {
        ticketingService.reportDelay(nrTren, delayMinutes);
        return "Clienții trenului " + nrTren + " au fost notificați de întârzierea de " + delayMinutes + " minute.";
    }
}