/**---------------------------------------------------------------------*
 * filename     : UserValidator.java
 * date         : Apr 25, 2013
 * Author       : akhilesh
 *
 * Changes      : See javadoc object description
 *----------------------------------------------------------------------*
 */
package com.indic.zwapper.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
*     Apr 25, 2013 | akhilesh   | Original
* </PRE>
*
* @since       JDK1.4.2_07
*
*/
/*-----------------------------------------------------------------------*/

public class UserValidator implements Validator
    {

    public UserValidator()
	{
	}

    @Override
    public boolean supports( Class<?> clazz )
	{
	return User.class.isAssignableFrom(clazz);
	}

    @Override
    public void validate( Object target, Errors errors )
	{
	ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						  "userId",
						  "required.userName",
						  "Field name is required.");

	ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						  "password",
						  "required.password",
						  "Field name is required.");

	}

    }
