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
package org.sonatype.nexus.plugins.capabilities.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.component.annotations.Component;
import org.sonatype.nexus.formfields.FormField;
import org.sonatype.nexus.formfields.RepoOrGroupComboFormField;
import org.sonatype.nexus.formfields.StringTextFormField;
import org.sonatype.nexus.plugins.capabilities.api.descriptor.CapabilityDescriptor;

@Component( role = CapabilityDescriptor.class, hint = TouchTestCapability.ID )
public class TouchTestCapabilityDescriptor
    implements CapabilityDescriptor
{
    public static final String FIELD_REPO_OR_GROUP_ID = "repoOrGroupId";

    public static final String FIELD_MSG_ID = "message";

    private final RepoOrGroupComboFormField repoField = new RepoOrGroupComboFormField( FIELD_REPO_OR_GROUP_ID, FormField.MANDATORY );

    private final StringTextFormField msgField = new StringTextFormField( FIELD_MSG_ID, "Message", "Message help text",
                                                                          FormField.MANDATORY );

    public String id()
    {
        return TouchTestCapability.ID;
    }

    public String name()
    {
        return "Touch Test Capability";
    }

    public List<FormField> formFields()
    {
        List<FormField> fields = new ArrayList<FormField>();
        fields.add( repoField );
        fields.add( msgField );
        return fields;
    }

    public boolean isExposed()
    {
        return true;
    }

    @Override
    public String describe( final Map<String, String> properties )
    {
        return "test";
    }

}
