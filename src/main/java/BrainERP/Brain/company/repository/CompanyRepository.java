package BrainERP.Brain.company.repository;

import BrainERP.Brain.company.model.CompanyModel;
import BrainERP.Brain.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<UserModel, Long> {
    Optional<CompanyModel>findByEmail(String email);
    Optional<CompanyModel>findByEmailAndActivateTrue(String email);
    List<CompanyModel>findAllByActivateTrue();
    Optional<CompanyModel>findByIdAndActivateTrue(Long id);
}
