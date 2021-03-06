/**
 * Copyright (c) 2008-2011 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions
 *
 * This program is free software: you can redistribute it and/or modify it only under the terms of the GNU Affero General
 * Public License Version 3 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License Version 3
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License Version 3 along with this program.  If not, see
 * http://www.gnu.org/licenses.
 *
 * Sonatype Nexus (TM) Open Source Version is available from Sonatype, Inc. Sonatype and Sonatype Nexus are trademarks of
 * Sonatype, Inc. Apache Maven is a trademark of the Apache Foundation. M2Eclipse is a trademark of the Eclipse Foundation.
 * All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.configuration.application.source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.sonatype.nexus.configuration.source.ApplicationConfigurationSource;
import org.sonatype.nexus.configuration.source.FileConfigurationSource;
import org.sonatype.nexus.util.FileUtils;

public class FileConfigurationSourceTest
    extends AbstractApplicationConfigurationSourceTest

{
    @Override
    protected ApplicationConfigurationSource getConfigurationSource()
        throws Exception
    {
        return lookup( ApplicationConfigurationSource.class, "file" );
    }

    @Override
    protected InputStream getOriginatingConfigurationInputStream()
        throws IOException
    {
        return getClass().getResourceAsStream( "/META-INF/nexus/default-oss-nexus.xml" );
    }

    @Test
    public void testStoreConfiguration()
        throws Exception
    {
        configurationSource = getConfigurationSource();

        configurationSource.loadConfiguration();

        try
        {
            configurationSource.storeConfiguration();
        }
        catch ( UnsupportedOperationException e )
        {
            fail();
        }
    }

    @Test
    public void testIsConfigurationUpgraded()
        throws Exception
    {
        configurationSource = getConfigurationSource();

        configurationSource.loadConfiguration();

        assertEquals( false, configurationSource.isConfigurationUpgraded() );
    }

    @Test
    public void testIsConfigurationDefaulted()
        throws Exception
    {
        configurationSource = getConfigurationSource();

        configurationSource.loadConfiguration();

        assertEquals( true, configurationSource.isConfigurationDefaulted() );
    }

    @Test
    public void testIsConfigurationDefaultedShouldNot()
        throws Exception
    {
        copyDefaultConfigToPlace();

        configurationSource = getConfigurationSource();

        configurationSource.loadConfiguration();

        assertEquals( false, configurationSource.isConfigurationDefaulted() );
    }

    @Test
    public void testGetDefaultsSource()
        throws Exception
    {
        configurationSource = getConfigurationSource();

        assertFalse( configurationSource.getDefaultsSource() == null );
    }

    @Test
    public void testNEXUS2212LoadValidConfig()
        throws Exception
    {

        // copy the config into place
        File nexusConfigFile = FileUtils.getFileFromUrl( ClassLoader.getSystemClassLoader().getResource( "nexus-NEXUS-2212.xml" ).toString() );
        org.codehaus.plexus.util.FileUtils.copyFile( nexusConfigFile, new File( getWorkHomeDir(), "conf/nexus.xml") );

        configurationSource = (FileConfigurationSource) getConfigurationSource();
        configurationSource.loadConfiguration();
        assertTrue( configurationSource.getValidationResponse().isValid()) ;

    }
}
