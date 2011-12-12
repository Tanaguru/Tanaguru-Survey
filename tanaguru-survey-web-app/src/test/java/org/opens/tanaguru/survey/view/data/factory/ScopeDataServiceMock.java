/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.survey.view.data.factory;

import java.util.Collection;
import java.util.Set;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.ScopeImpl;
import org.opens.tanaguru.entity.service.reference.ScopeDataService;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class ScopeDataServiceMock implements ScopeDataService{

    @Override
    public Scope create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Scope e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Scope e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<Scope> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Scope> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Scope read(Long k) {
        return new ScopeImpl();
    }

    @Override
    public Scope saveOrUpdate(Scope e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Scope> saveOrUpdate(Set<Scope> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Scope, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Scope> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Scope update(Scope e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
