package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.repositories.IReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationServices{

    IReservationRepository reservationRepository;

    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        if (reservationRepository.existsById(Long.valueOf(res.getIdReservation()))) {
            return reservationRepository.save(res);
        }
        return null;
    }

    @Override
    public Reservation retrieveReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }
    @Override
public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
    Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);
    Bloc bloc = blocRepository.findById(idBloc).orElse(null);

    if (etudiant != null && bloc != null) {
        Chambre chambre = chambreRepository.findAvailableChambreInBloc(idBloc);

        if (chambre != null && chambre.getReservations().size() < chambre.getCapaciteMax()) {
            Reservation reservation = new Reservation();
            reservation.setNumReservation(chambre.getNumero() + "-" + chambre.getBloc().getNom() + "-" + LocalDate.now().getYear());
            reservation.setChambre(chambre);
            reservation.setEtudiant(etudiant);
            reservation.setEstValide(true);
            
            reservationRepository.save(reservation);
            return reservation;
        }
    }
    return null;
}

}