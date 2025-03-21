package tn.esprit.tpfoyer.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.*;
import tn.esprit.tpfoyer.repositories.IUniversiteRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteServices {
    IUniversiteRepository universiteRepository;

    @Override
    public List<Universite> retrieveAllUniversities() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        if (universiteRepository.existsById(u.getIdUniversite())) {
            return universiteRepository.save(u);
        }
        return null;
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {

        return universiteRepository.findById(idUniversite).orElse(null);
    }
    @Override
public Universite assignerFoyerAUniversite(Long idUniversite, Long idFoyer) {
    Universite universite = universiteRepository.findById(idUniversite).orElse(null);
    Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);

    if (universite != null && foyer != null) {
        universite.setFoyer(foyer);
        universiteRepository.save(universite);
    }
    return universite;
}

@Override
public Universite supprimerFoyerDeUniversite(Long idUniversite) {
    Universite universite = universiteRepository.findById(idUniversite).orElse(null);

    if (universite != null) {
        universite.setFoyer(null);
        universiteRepository.save(universite);
    }
    return universite;
}

}