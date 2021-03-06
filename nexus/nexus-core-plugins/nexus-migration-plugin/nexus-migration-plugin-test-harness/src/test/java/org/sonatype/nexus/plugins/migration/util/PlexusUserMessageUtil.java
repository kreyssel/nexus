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
package org.sonatype.nexus.plugins.migration.util;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.nexus.integrationtests.RequestFacade;
import org.sonatype.plexus.rest.representation.XStreamRepresentation;
import org.sonatype.security.rest.model.PlexusUserListResourceResponse;
import org.sonatype.security.rest.model.PlexusUserResource;

public class PlexusUserMessageUtil
{
    protected Logger log = LoggerFactory.getLogger( getClass() );

    public List<PlexusUserResource> getList()
        throws IOException
    {
        Response response = RequestFacade.doGetRequest( "service/local/plexus_users/allConfigured" );
        String responseText = response.getEntity().getText();
        log.debug( "responseText: \n" + responseText );

        XStreamRepresentation representation = new XStreamRepresentation(
            XStreamFactory.getXmlXStream(),
            responseText,
            MediaType.APPLICATION_XML );

        // make sure we have a success
        assertThat( "Status: " + response.getStatus() + "\n" + responseText, response.getStatus().isSuccess() );

        PlexusUserListResourceResponse resourceResponse = (PlexusUserListResourceResponse) representation
            .getPayload( new PlexusUserListResourceResponse() );

        return resourceResponse.getData();
    }

}
