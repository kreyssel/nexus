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
package org.sonatype.nexus.util;

public class SystemPropertiesHelper
{
    public final static int getInteger( final String key, final int defaultValue )
    {
        final String value = System.getProperty( key );

        if ( value == null || value.trim().length() == 0 )
        {
            return defaultValue;
        }

        try
        {
            return Integer.valueOf( value );
        }
        catch ( NumberFormatException e )
        {
            return defaultValue;
        }
    }

    public final static long getLong( final String key, final long defaultValue )
    {
        final String value = System.getProperty( key );

        if ( value == null || value.trim().length() == 0 )
        {
            return defaultValue;
        }

        try
        {
            return Long.valueOf( value );
        }
        catch ( NumberFormatException e )
        {
            return defaultValue;
        }
    }

    public final static boolean getBoolean( final String key, final boolean defaultValue )
    {
        final String value = System.getProperty( key );

        if ( value == null || value.trim().length() == 0 )
        {
            return defaultValue;
        }

        return Boolean.valueOf( value );
    }

    public final static String getString( final String key, final String defaultValue )
    {
        return System.getProperty( key, defaultValue );
    }
}
