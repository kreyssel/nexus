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
package org.sonatype.nexus.integrationtests.nexus1071;

import static org.sonatype.nexus.integrationtests.ITGroups.SECURITY;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.maven.index.artifact.Gav;
import org.sonatype.nexus.integrationtests.AbstractPrivilegeTest;
import org.sonatype.nexus.integrationtests.TestContainer;
import org.sonatype.nexus.test.utils.FileTestingUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Juven Xu
 */
public class Nexus1071AnonAccessIT
    extends AbstractPrivilegeTest
{
	
    @BeforeClass(alwaysRun = true)
    public void setSecureTest(){
        TestContainer.getInstance().getTestContext().setSecureTest( true );
    }

    @Test(groups = SECURITY)
    public void downloadArtifactFromPublicGroup()
        throws Exception
    {
        Gav gav =
            new Gav( this.getTestId(), "release-jar", "1", null, "jar", 0, new Date().getTime(), "Release Jar", 
                     false, null, false, null );

        File artifact = this.downloadArtifactFromGroup( "public", gav, "./target/downloaded-jars" );

        assertTrue( artifact.exists() );

        File originalFile =
            this.getTestResourceAsFile( "projects/" + gav.getArtifactId() + "/" + gav.getArtifactId() + "."
                + gav.getExtension() );

        Assert.assertTrue( FileTestingUtils.compareFileSHA1s( originalFile, artifact ) );

    }

    @Test(groups = SECURITY)
    public void downloadArtifactFromInternalRepo()
        throws Exception
    {
        if ( true )
        {
            printKnownErrorButDoNotFail( getClass(), "downloadArtifactFromInternalRepo" );
            return;
        }

        Gav gav =
            new Gav( this.getTestId(), "release-jar-internal", "1", null, "jar", 0, new Date().getTime(),
                     "Release Jar Internal", false, null, false, null );
        try
        {
            downloadArtifactFromRepository( "Internal", gav, "./target/downloaded-jars" );

            Assert.fail( "Should throw 401 error" );
        }
        catch ( IOException e )
        {
            Assert.assertTrue( e.getMessage().contains( "401" ) );
        }

    }
}
