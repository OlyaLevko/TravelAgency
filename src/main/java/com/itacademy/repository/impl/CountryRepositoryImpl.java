package com.itacademy.repository.impl;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.repository.CountryRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CountryRepositoryImpl implements CountryRepository {

    private SessionFactory sessionFactory;
    private RepositoryUtilityClass utilityClass;

    @Autowired
    public CountryRepositoryImpl(SessionFactory sessionFactory, RepositoryUtilityClass utilityClass) {
        this.sessionFactory = sessionFactory;
        this.utilityClass = utilityClass;
    }

    @Override
    public Country getByCountryName(String name){
        return utilityClass.getByNameOperationWithinPersistentContext(
                session -> {
                    Query query=session.createQuery("from Country where name = :name");
                    query.setParameter("name", name);
                    return (Country) query.uniqueResult();
                }
        );
    }

    @Override
    public Country save(Country country) {
        return utilityClass
                .saveOperationWithinPersistentContext(
                        session->{session.persist(country);
                                  return country;}
        );
    }

    @Override
    public void delete(Long id) {
        utilityClass.deleteOperationWithinPersistentContext(
                        session->{
                            Country country=session.get(Country.class,id);
                            session.delete(country); }
                );
    }

    @Override
    public Country update(Country country) {
        return utilityClass
                .updateOperationWithinPersistentContext(
                        session->{
                            Country countryFromDb=session.get(Country.class,country.getId());
                            if(countryFromDb!=null){
                                countryFromDb.setName(country.getName());
                            }
                            return countryFromDb;}
                );
    }

    @Override
    public Country getById(Long id) {
        return utilityClass.getByIdOperationWithinPersistentContext(
                session ->
                    session.get(Country.class,id)
        );
    }

    @Override
    public List<Country> getAll() {
        return utilityClass.getAllOperationWithinPersistentContext(
                session -> {
                    Query query=session.createQuery("select country from Country country");
                    return query.list();
                }
        );
    }

    @Override
    public List<Hotel> getAllHotelsInCountry(String countryName) {
        return utilityClass.getAllOperationWithinPersistentContext(
                session -> {
                    Query query=session.createQuery(
                            "select hotel from Hotel as hotel " +
                                    "join hotel.country c where c.name= :name");
                    query.setParameter("name", countryName);
                    return query.list();
                }
        );
    }
}
