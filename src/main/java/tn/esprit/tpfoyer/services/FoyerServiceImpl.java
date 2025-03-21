package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.repositories.IChambreReposirtory;
import tn.esprit.tpfoyer.repositories.IFoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerServices {

    IFoyerRepository foyerRepository;
    IChambreReposirtory chambreReposirtory;
    @Override
    public Foyer findById(long id) {
        return foyerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Foyer> findAll() {
        return (List<Foyer>) foyerRepository.findAll();
    }

    @Override
    public Foyer save(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void delete(Long id) {
        foyerRepository.deleteById(id);
    }

    @Override
    public Foyer getNomCapacite(String nom, Long capacite) {
        return foyerRepository.findByNomFoyerAndCapaciteFoyer(nom, capacite);
    }

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return List.of();
    }

    @Override
    public Foyer addFoyer(Foyer f)
    {
        return foyerRepository.save(f);
    }


    @Override
    public Foyer updateFoyer(Foyer f) {
        if (foyerRepository.existsById(f.getIdFoyer())) {
            return foyerRepository.save(f);
        }
        return null;
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public void removeFoyer(long idFoyer) {

        foyerRepository.deleteById(idFoyer);
    }
    @Override
public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
    Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
    Universite universite = universiteRepository.findByNom(nomUniversite);

    if (foyer != null && universite != null) {
        universite.setFoyer(foyer);
        universiteRepository.save(universite);
    }
    return universite;
}

@Override
public Universite desaffecterFoyerAUniversite(long idUniversite) {
    Universite universite = universiteRepository.findById(idUniversite).orElse(null);

    if (universite != null) {
        universite.setFoyer(null);
        universiteRepository.save(universite);
    }
    return universite;
}

@Override
public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
    Universite universite = universiteRepository.findById(idUniversite).orElse(null);

    if (universite != null) {
        foyerRepository.save(foyer);
        universite.setFoyer(foyer);
        universiteRepository.save(universite);
    }
    return foyer;
}


}