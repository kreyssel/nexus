/**
 * Copyright (c) 2008-2011 Sonatype, Inc. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.sonatype.nexus.plugins.migration.nexus1444;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

import org.restlet.data.Status;
import org.sonatype.nexus.plugins.migration.AbstractMigrationPrivilegeTest;
import org.testng.annotations.Test;

public class Nexus1444MigrationWithPrivilegeIT
    extends AbstractMigrationPrivilegeTest
{

    @Test
    public void doMigrationWithPermission()
        throws Exception
    {
        addPrivilege( TEST_USER_NAME, ARTIFACTORY_MIGRATOR );

        Status status = doMigration();

        assertThat( "Migration is succesful", status.isSuccess() );
    }

    @Override
    protected File getBackupFile()
    {
        return getTestFile( "artifactory125.zip" );
    }

}
