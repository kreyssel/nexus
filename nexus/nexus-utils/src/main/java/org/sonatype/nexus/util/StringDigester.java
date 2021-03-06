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

/**
 * A util class to calculate various digests on Strings. Usaful for some simple password management.
 * 
 * @author cstamas
 * @deprecated Use DigesterUtils instead!
 */
public class StringDigester
{
    @Deprecated
    public static String LINE_SEPERATOR = System.getProperty( "line.separator" );
    
    /**
     * Calculates a SHA1 digest for a string.
     * 
     * @param content
     * @return
     */
    @Deprecated
    public static String getSha1Digest( String content )
    {
        return DigesterUtils.getSha1Digest( content );
    }

    /**
     * Calculates MD5 digest for a string.
     * 
     * @param content
     * @return
     */
    @Deprecated
    public static String getMd5Digest( String content )
    {
        return DigesterUtils.getMd5Digest( content );
    }
}
