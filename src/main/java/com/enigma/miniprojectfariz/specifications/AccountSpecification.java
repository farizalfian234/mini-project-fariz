package com.enigma.miniprojectfariz.specifications;

import com.enigma.miniprojectfariz.dto.AccountDTO;
import com.enigma.miniprojectfariz.entities.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccountSpecification {
    public static Specification<Account> getSpecification(AccountDTO accountDTO) {
        return new Specification<Account>() {
            List<Predicate> predicates =new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (accountDTO.getNumber()!=null) {
                    Predicate accountNumberPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("number")),
                            "%" + accountDTO.getNumber().toLowerCase(Locale.ROOT)+"%");
                    predicates.add(accountNumberPredicate);
                }

                if (accountDTO.getName()!=null) {
                    Predicate accountNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + accountDTO.getName().toLowerCase(Locale.ROOT)+"%");
                    predicates.add(accountNamePredicate);
                }

                if (accountDTO.getIsDeleted()!=null) {
                    Predicate accountIsDeletedPredicate = criteriaBuilder.equal(root.get("isDeleted"),
                            accountDTO.getIsDeleted());
                    predicates.add(accountIsDeletedPredicate);
                }

                Predicate[] arrayPredicate =predicates.toArray(new Predicate[0]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
