/**---------------------------------------------------------------------*
 * filename     : UserDao.java
 * date         : May 20, 2013
 * Author       : akhilesh
 *
 * Changes      : See javadoc object description
 *----------------------------------------------------------------------*
 */
package com.indic.zwapper.dao;

import com.indic.zwapper.entity.User;


/*-----------------------------------------------------------------------*//**
*
* @author      akhilesh
* @version     %R%
*
* <BR><B>Revision History:</B><BR>
* <PRE>
*     Date        | User  | Description
*     ------------------------------------------------------------
*     May 20, 2013 | akhilesh   | Original
* </PRE>
*
* @since       JDK1.4.2_07
*
*/
/*-----------------------------------------------------------------------*/

public interface UserDao extends GenericDAO<User, Long> 
    {
    User findByIdAndPassword(String id,String password);
    User findById(String id);
    }
