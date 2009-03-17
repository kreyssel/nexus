/**
 * Sonatype Nexus (TM) Open Source Version.
 * Copyright (c) 2008 Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://nexus.sonatype.org/dev/attributions.html
 * This program is licensed to you under Version 3 only of the GNU General Public License as published by the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License Version 3 for more details.
 * You should have received a copy of the GNU General Public License Version 3 along with this program.
 * If not, see http://www.gnu.org/licenses/.
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc.
 * "Sonatype" and "Sonatype Nexus" are trademarks of Sonatype, Inc.
 */
package org.sonatype.nexus.configuration.application;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.sonatype.nexus.configuration.ConfigurationException;
import org.sonatype.nexus.configuration.modello.CMirror;
import org.sonatype.nexus.configuration.modello.CPathMappingItem;
import org.sonatype.nexus.configuration.modello.CRemoteConnectionSettings;
import org.sonatype.nexus.configuration.modello.CRemoteHttpProxySettings;
import org.sonatype.nexus.configuration.modello.CRemoteNexusInstance;
import org.sonatype.nexus.configuration.modello.CRepository;
import org.sonatype.nexus.configuration.modello.CRepositoryTarget;
import org.sonatype.nexus.configuration.modello.CRouting;
import org.sonatype.nexus.configuration.modello.CSmtpConfiguration;
import org.sonatype.nexus.proxy.NoSuchRepositoryException;
import org.sonatype.nexus.proxy.registry.ContentClass;
import org.sonatype.nexus.tasks.descriptors.ScheduledTaskDescriptor;

public interface MutableConfiguration
{
    // ----------------------------------------------------------------------------------------------------------
    // Repositories
    // ----------------------------------------------------------------------------------------------------------

    boolean isSecurityEnabled();

    void setSecurityEnabled( boolean enabled )
        throws IOException;

    boolean isAnonymousAccessEnabled();

    void setAnonymousAccessEnabled( boolean enabled )
        throws IOException;

    String getAnonymousUsername();

    void setAnonymousUsername( String val )
        throws IOException;

    String getAnonymousPassword();

    void setAnonymousPassword( String val )
        throws IOException;

    List<String> getRealms();

    void setRealms( List<String> realms )
        throws IOException;

    // ----------------------------------------------------------------------------
    // ContentClasses
    // ----------------------------------------------------------------------------

    Collection<ContentClass> listRepositoryContentClasses();

    // ----------------------------------------------------------------------------
    // Scheduled Tasks
    // ----------------------------------------------------------------------------

    List<ScheduledTaskDescriptor> listScheduledTaskDescriptors();

    ScheduledTaskDescriptor getScheduledTaskDescriptor( String id );

    // ----------------------------------------------------------------------------------------------------------
    // REST API
    // ----------------------------------------------------------------------------------------------------------

    String getBaseUrl();

    void setBaseUrl( String baseUrl )
        throws IOException;

    boolean isForceBaseUrl();

    void setForceBaseUrl( boolean force )
        throws IOException;

    // ------------------------------------------------------------------
    // CRUD-like ops on config sections

    // Globals are mandatory: RU

    // CRemoteConnectionSettings are mandatory: RU

    CRemoteConnectionSettings readGlobalRemoteConnectionSettings();

    void updateGlobalRemoteConnectionSettings( CRemoteConnectionSettings settings )
        throws ConfigurationException,
            IOException;

    // CRemoteHttpProxySettings are optional: CRUD

    void createGlobalRemoteHttpProxySettings( CRemoteHttpProxySettings settings )
        throws ConfigurationException,
            IOException;

    CRemoteHttpProxySettings readGlobalRemoteHttpProxySettings();

    void updateGlobalRemoteHttpProxySettings( CRemoteHttpProxySettings settings )
        throws ConfigurationException,
            IOException;

    void deleteGlobalRemoteHttpProxySettings()
        throws IOException;

    // CRouting are mandatory: RU

    CRouting readRouting();

    void updateRouting( CRouting settings )
        throws ConfigurationException,
            IOException;

    // CRepository: CRUD

    Collection<CRepository> listRepositories();

    void createRepository( CRepository settings )
        throws ConfigurationException,
            IOException;

    CRepository readRepository( String id )
        throws NoSuchRepositoryException;

    void updateRepository( CRepository settings )
        throws NoSuchRepositoryException,
            ConfigurationException,
            IOException;

    void deleteRepository( String id )
        throws NoSuchRepositoryException,
            IOException,
            ConfigurationException;

    // CGroupsSettingPathMapping: CRUD

    Collection<CPathMappingItem> listGroupsSettingPathMapping();

    void createGroupsSettingPathMapping( CPathMappingItem settings )
        throws NoSuchRepositoryException,
            ConfigurationException,
            IOException;

    CPathMappingItem readGroupsSettingPathMapping( String id )
        throws IOException;

    void updateGroupsSettingPathMapping( CPathMappingItem settings )
        throws NoSuchRepositoryException,
            ConfigurationException,
            IOException;

    void deleteGroupsSettingPathMapping( String id )
        throws IOException;

    // CRepositoryTarget: CRUD

    Collection<CRepositoryTarget> listRepositoryTargets();

    void createRepositoryTarget( CRepositoryTarget settings )
        throws ConfigurationException,
            IOException;

    CRepositoryTarget readRepositoryTarget( String id );

    void updateRepositoryTarget( CRepositoryTarget settings )
        throws ConfigurationException,
            IOException;

    void deleteRepositoryTarget( String id )
        throws IOException;

    // CRemoteNexusInstance

    Collection<CRemoteNexusInstance> listRemoteNexusInstances();

    CRemoteNexusInstance readRemoteNexusInstance( String alias )
        throws IOException;

    void createRemoteNexusInstance( CRemoteNexusInstance settings )
        throws IOException;

    void deleteRemoteNexusInstance( String alias )
        throws IOException;

    // Smtp settings
    CSmtpConfiguration readSmtpConfiguration();

    void updateSmtpConfiguration( CSmtpConfiguration settings )
        throws ConfigurationException,
            IOException;

    // Mirrors
    void setMirrors( String repositoryId, List<CMirror> mirrors )
        throws NoSuchRepositoryException,
            ConfigurationException,
            IOException;

    Collection<CMirror> listMirrors( String repositoryId )
        throws NoSuchRepositoryException;
}
