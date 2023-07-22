package hs.os.skaterverwaltung.boundary.web;


import hs.os.skaterverwaltung.control.ISkaterManagement;
import org.jboss.logging.Logger;

import javax.inject.Inject;

public class SkaterResource {
    private static final Logger LOG = Logger.getLogger(SkaterResource.class);

    @Inject
    ISkaterManagement management;


}
